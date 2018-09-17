package com.example.user.lesson_android_development.data.storage.local.product;

import com.example.user.lesson_android_development.data.MostSoldItem;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.data.storage.ProductsRepository;
import com.example.user.lesson_android_development.data.storage.convertor.RemoteToLocal;
import com.example.user.lesson_android_development.data.storage.local.mostsolditem.MostSoldItemDao;
import com.example.user.lesson_android_development.data.storage.local.productImage.ProductImageDao;
import com.example.user.lesson_android_development.data.storage.local.productdescription.ProductDescriptionDao;
import com.example.user.lesson_android_development.data.storage.local.tag.TagDao;
import com.example.user.lesson_android_development.data.storage.remote.response.BaseResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.ProductResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.SupplementsResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.TagsResponse;
import com.example.user.lesson_android_development.util.AppExecutors;

import java.util.List;

public class ProductLocalDataSource {

    private static final String TAG = ProductLocalDataSource.class.getSimpleName();

    private static ProductLocalDataSource sInstance = null;

    private final ProductDao mProductDao;
    private final AppExecutors mAppExecutors;
    private final ProductImageDao mProductImageDao;
    private final ProductDescriptionDao mProductDescriptionDao;
    private final TagDao mTagDao;
    private final MostSoldItemDao mMostSoldItemDao;


    private ProductLocalDataSource(ProductDao productDao,
                                   AppExecutors appExecutors,
                                   ProductImageDao productImageDao,
                                   ProductDescriptionDao productDescriptionDao,
                                   TagDao tagDao,
                                   MostSoldItemDao mostSoldItemDao) {
        mProductDao = productDao;
        mAppExecutors = appExecutors;
        mProductImageDao = productImageDao;
        mProductDescriptionDao = productDescriptionDao;
        mTagDao = tagDao;
        mMostSoldItemDao = mostSoldItemDao;
    }

    public static ProductLocalDataSource getInstance(ProductDao productDao,
                                                     AppExecutors appExecutors,
                                                     ProductImageDao productImageDao,
                                                     ProductDescriptionDao productDescriptionDao,
                                                     TagDao tagDao,
                                                     MostSoldItemDao mostSoldItemDao) {
        if (sInstance == null) {
            sInstance = new ProductLocalDataSource(productDao, appExecutors, productImageDao, productDescriptionDao, tagDao, mostSoldItemDao);
        }
        return sInstance;
    }

    /**
     * send this respons in supplementsRepository
     *
     * @param callback - callback from supplementsRepository
     */
    public void getProduct(BaseResponse baseResponse,
                           final ProductsRepository.GetProductsCallback callback) {


        mAppExecutors.diskIO().execute(() -> {

            List<SupplementsResponse> supplements = baseResponse.getSuplements();
            List<ProductResponse> productsResponses = baseResponse.getProducts();
            List<TagsResponse> tagsResponses = baseResponse.getTagsResponses();

            /**
             * product
             */
            if (productsResponses != null && !productsResponses.isEmpty()) {
                mProductDao.clear();
                mProductImageDao.clear();
                mProductDescriptionDao.clear();
                mMostSoldItemDao.clear();

                mProductDao.insert(
                        RemoteToLocal.productsConvertor(productsResponses));

                mTagDao.insert(
                        RemoteToLocal.tagConverter(tagsResponses)
                );

                //??
                mMostSoldItemDao.insert(RemoteToLocal.mostSoldItemsConverter(
                        baseResponse
                ));

                for (ProductResponse p : productsResponses) {

                    //image
                    mProductImageDao.insert(
                            RemoteToLocal.productImageConverter(
                                    p,
                                    supplements
                            ));

                    //description
                    mProductDescriptionDao.insert(
                            RemoteToLocal.productDescriptionConverter(
                                    p,
                                    supplements
                            ));

                    //tags
                    mProductDao.clearTags(p.getId());
                    mProductDao.insertProductTags(RemoteToLocal.productTagConverter(
                            p.getId(),
                            p.getTags()
                    ));
                }
            }

            final List<Product> products = mProductDao.getProduct();

            for (Product p : products) {
                //image
                p.setProductImages(mProductImageDao.getProductImage(p.getId()));
                //description
                p.setProductDescriptions(mProductDescriptionDao.getProductDescription(p.getId()));
                //tags
                p.setTags(mProductDao.getProductTags(p.getId()));
            }

            mAppExecutors.mainThread().execute(() -> callback.onSuccess(products));
        });
    }

    public void getMostSoldItem(ProductsRepository.GetMostSoldItem callback){
        mAppExecutors.diskIO().execute(() -> {

        List<Product>products = mProductDao.getMostSoldItems();

        for (Product p : products) {
                //image
                p.setProductImages(mProductImageDao.getProductImage(p.getId()));
                //description
                p.setProductDescriptions(mProductDescriptionDao.getProductDescription(p.getId()));
                //tags
                p.setTags(mProductDao.getProductTags(p.getId()));
            }
            mAppExecutors.mainThread().execute(() -> callback.onSuccess(products));
        });
    }

    public void getFilteredProduct(long tagId, ProductsRepository.GetFilterCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            final List<Product> products = mProductDao.getFilteredProducts(tagId);

            for (Product p : products) {
                //image
                p.setProductImages(mProductImageDao.getProductImage(p.getId()));
                //description
                p.setProductDescriptions(mProductDescriptionDao.getProductDescription(p.getId()));
                //tags
                p.setTags(mProductDao.getProductTags(p.getId()));
            }
            mAppExecutors.mainThread().execute(() -> callback.onSuccess(products));
        });
    }

    public void getAllTags(ProductsRepository.GetAllTagsCallback callback) {
        mAppExecutors.diskIO().execute(() -> {
            List<Tag> allTags = mTagDao.getTags();

            mAppExecutors.mainThread().execute(() -> {
                if (allTags != null && allTags.size() > 0) {
                    callback.onSuccess(allTags);
                } else {
                    callback.onError();
                }
            });

        });

    }


}


