package com.example.user.lesson_android_development.data.storage;

import android.widget.Toast;

import com.example.user.lesson_android_development.data.CartItem;
import com.example.user.lesson_android_development.data.MostSoldItem;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.ProductCartItem;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.data.storage.local.cartitem.CartItemLocalDataSource;
import com.example.user.lesson_android_development.data.storage.local.product.ProductLocalDataSource;
import com.example.user.lesson_android_development.data.storage.remote.content.ProductsRemoteDataSource;
import com.example.user.lesson_android_development.data.storage.remote.response.BaseResponse;

import java.util.List;

public class ProductsRepository {

    public static final String TAG = ProductsRepository.class.getSimpleName();

    private static ProductsRepository sContentRepository = null;

    private final ProductsRemoteDataSource mRemoteDataSource;
    private final ProductLocalDataSource mLocalDataSource;
    private final CartItemLocalDataSource mCartItemLocalDataSource;

    public ProductsRepository(ProductsRemoteDataSource remoteDataSource,
                              ProductLocalDataSource localDataSource,
                              CartItemLocalDataSource cartItemLocalDataSource) {
        mRemoteDataSource = remoteDataSource;
        mLocalDataSource = localDataSource;
        mCartItemLocalDataSource = cartItemLocalDataSource;
    }

    public static ProductsRepository getInstance(
            ProductsRemoteDataSource remoteDataSource,
            ProductLocalDataSource localDataSource,
            CartItemLocalDataSource cartItemLocalDataSource) {

        if (sContentRepository == null) {
            sContentRepository = new ProductsRepository(remoteDataSource, localDataSource, cartItemLocalDataSource);
        }
        return sContentRepository;
    }

    public void getProductsContent(final GetProductsCallback callback) {

        mRemoteDataSource.getProducts(new ProductsRemoteDataSource.GetProductsCallback() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                mLocalDataSource.getProduct(
                        baseResponse
                        , callback);
            }

            @Override
            public void onError() {

            }
        });
    }

    /*
   get all tags
    */
    public void getAllTags(GetAllTagsCallback callback) {

        mLocalDataSource.getAllTags(callback);

    }

    /*
    filter
     */
    public void getFilteredProducts(long tagId, GetFilterCallback callback) {
        mLocalDataSource.getFilteredProduct(tagId, callback);
    }

    /*
    most sold
     */
    public void getMostSoldItem(GetMostSoldItem callback){
        mLocalDataSource.getMostSoldItem(callback);
    }

    /*
    get cartItem
     */
    public void getCardItem(GetCardItemCallback callback) {
        mCartItemLocalDataSource.getCartItems(callback);

    }

    public void insertCardItem(long id, int quantity) {
        mCartItemLocalDataSource.addCartItem(id, quantity);
    }

    public void clearCardItem(long id) {
        mCartItemLocalDataSource.clearCartItem(id);
    }

    /**
     * geting data from productsLocalDataSource
     */
    public interface GetProductsCallback {
        void onSuccess(List<Product> products);

        void onError();
    }

    /**
     * mostSoldItem
     */
    public interface GetMostSoldItem{
        void onSuccess(List<Product>products);

        void onError();
    }

    /**
     * filter
     */
    public interface GetFilterCallback{
        void onSuccess(List<Product>products);

        void onError();
    }


    /**
     * geting data from tag
     */
    public interface GetAllTagsCallback {
        void onSuccess(List<Tag> tags);

        void onError();
    }

    /**
     * geting cartItem Data
     */
    public interface GetCardItemCallback {
        void onSuccess(List<ProductCartItem> productCartItems);

        void onError();
    }
}
