package com.example.user.lesson_android_development.main.shop;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.data.storage.ProductsRepository;
import com.example.user.lesson_android_development.util.SingleLiveEvent;

import java.util.List;


public class ShopViewModel extends AndroidViewModel {

    private static final String TAG = ShopViewModel.class.getSimpleName();

    private final ProductsRepository mProductsRepository;

    public final ObservableList<Product> mProducts = new ObservableArrayList<>();
    public final ObservableList<Product> mBestSellingItems = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);


    private final SingleLiveEvent<Product> mOpenShopEvent = new SingleLiveEvent<>();


    public ShopViewModel(@NonNull Application application,
                         ProductsRepository productsRepository) {
        super(application);

        mProductsRepository = productsRepository;
    }

    /**
     * product
     */
    public void startProduct(long tadId) {
       if (mProducts.isEmpty()) {
            getProducts();
        } else {
           getFilterItem(tadId);
       }
    }

    private void getProducts() {
        mProductsRepository.getProductsContent(new ProductsRepository.GetProductsCallback() {
            @Override
            public void onSuccess(List<Product> products) {
                mProducts.clear();
                mProducts.addAll(products);
                mError.set(products.isEmpty());
            }

            @Override
            public void onError() {
            }
        });
    }

    /**
     * bestSelling
     */
    public void startBestSelling() {
        if (mBestSellingItems.isEmpty()) {
            getBestSelling();
        }
    }

    private void getBestSelling() {
        mProductsRepository.getMostSoldItem(new ProductsRepository.GetMostSoldItem() {
            @Override
            public void onSuccess(List<Product> products) {
                mBestSellingItems.clear();
                mBestSellingItems.addAll(products);
                mError.set(mBestSellingItems.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }


    public void getFilterItem(long tagId) {
        mProductsRepository.getFilteredProducts(tagId, new ProductsRepository.GetFilterCallback() {
            @Override
            public void onSuccess(List<Product> products) {
                mProducts.clear();
                mProducts.addAll(products);
                mError.set(products.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }
    public SingleLiveEvent<Product> getOpenShopEvent() {
        return mOpenShopEvent;
    }
}

