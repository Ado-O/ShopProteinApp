package com.example.user.lesson_android_development.main.filter;

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

public class FilterViewModel extends AndroidViewModel {

    private ProductsRepository mProductsRepository;

    public final ObservableList<Tag> mTags = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    private final SingleLiveEvent<Tag> mOpenShopEvent = new SingleLiveEvent<>();


    public FilterViewModel(@NonNull Application application,
                           ProductsRepository productsRepository) {
        super(application);
        mProductsRepository = productsRepository;
    }

    /**
     * get all tag
     */
    public void startFilter() {
        if (mTags.isEmpty()) {
            getFilter();
        }
    }

    private void getFilter() {
        mProductsRepository.getAllTags(new ProductsRepository.GetAllTagsCallback() {
            @Override
            public void onSuccess(List<Tag> tags) {
                mTags.clear();
                mTags.addAll(tags);
                mError.set(tags.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }



    public SingleLiveEvent<Tag> getOpenShopEvent() {
        return mOpenShopEvent;
    }
}
