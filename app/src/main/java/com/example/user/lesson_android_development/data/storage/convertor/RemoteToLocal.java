package com.example.user.lesson_android_development.data.storage.convertor;

import com.example.user.lesson_android_development.data.MostSoldItem;
import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.data.ProductImage;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.ProductTag;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.data.storage.remote.response.BaseResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.ProductResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.SupplementsResponse;
import com.example.user.lesson_android_development.data.storage.remote.response.TagsResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoteToLocal {
    public static final String TAG = RemoteToLocal.class.getSimpleName();

    /**
     * Main product
     */
    public static List<Product> productsConvertor(List<ProductResponse> productsResponses) {

        List<Product> products = new ArrayList<>();

        for (ProductResponse p : productsResponses) {


            products.add(
                    new Product(
                            p.getId(),
                            p.getTitle(),
                            p.getDes(),
                            p.getPrice(),
                            p.getDiscounte(),
                            p.getPictures().get(0)
                    )

            );
        }
        return products;
    }

    /**
     * Product image
     */
    public static List<ProductImage> productImageConverter(
            ProductResponse productsResponse,
            List<SupplementsResponse> supplementsResponses) {

        List<ProductImage> productImages = new ArrayList<>();

        if (productsResponse.getPictures() != null && productsResponse.getPictures().size() > 0) {
            productImages.add(new ProductImage(productsResponse.getId(), productsResponse.getPictures().get(0)));
        }

        if (productsResponse.getSuplements().size() > 1) {
            for (long sId : productsResponse.getSuplements()) {
                for (SupplementsResponse s : supplementsResponses) {
                    if (sId == s.getId()) {
                        for (String imageUrl : s.getPictures()) {
                            productImages.add(new ProductImage(productsResponse.getId(), imageUrl));
                        }
                        break;
                    }
                }
            }

        }
        return productImages;
    }

    /**
     * ProductDescription
     */
    public static List<ProductDescription> productDescriptionConverter(
            ProductResponse productsResponse,
            List<SupplementsResponse> supplementsResponses) {
        List<ProductDescription> productDescriptions = new ArrayList<>();


      //  if (productsResponse.getSuplements().size() > 1) {
            for (long sId : productsResponse.getSuplements()) { //get ever id from productResponse
                for (SupplementsResponse s : supplementsResponses) { //get eventing from supplementResponse
                    if (sId == s.getId()) { //compare id which we get from productsResponse.getSuplements() to supplementResponse
                        productDescriptions.add(new ProductDescription(productsResponse.getId(), s.getTitle(), s.getDes()));
                        break;
                    }
                }
            }
        //}
        return productDescriptions;
    }

    /**
     * Tag
     */
    public static List<Tag> tagConverter(List<TagsResponse> tagResponses) {
        List<Tag> tags = new ArrayList<>();

        for (TagsResponse t : tagResponses) {
            tags.add(
                    new Tag(
                            t.getId(),
                            t.getName()
                    )
            );
        }
        return tags;
    }

    /**
     * ProductTag
     */
    public static List<ProductTag> productTagConverter(long productId, List<Integer> tags) {
        List<ProductTag> productTags = new ArrayList<>();

        for (long tagId : tags) {
            productTags.add(
                    new ProductTag(
                            productId,
                            tagId
                    )
            );
        }

        return productTags;
    }


    public static List<MostSoldItem> mostSoldItemsConverter(
            BaseResponse baseResponse) {

        List<MostSoldItem> mostSoldItems = new ArrayList<>();

        for (int id : baseResponse.getMostSoldItem()) {
            mostSoldItems.add(new MostSoldItem(id));

        }
        return mostSoldItems;

    }
}
