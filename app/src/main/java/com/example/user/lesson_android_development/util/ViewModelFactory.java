package com.example.user.lesson_android_development.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;

import com.example.user.lesson_android_development.Injection;
import com.example.user.lesson_android_development.data.storage.ProductsRepository;
import com.example.user.lesson_android_development.main.filter.FilterViewModel;
import com.example.user.lesson_android_development.main.shop.ShopViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final ProductsRepository mProductsRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            application,
                            Injection.provideProductsRepository(application)
                    );
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, ProductsRepository productsRepository) {
        mApplication = application;

        mProductsRepository = productsRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ShopViewModel.class)) {
            return (T) new ShopViewModel(mApplication, mProductsRepository);
        }else if (modelClass.isAssignableFrom(FilterViewModel.class)) {
            return (T) new FilterViewModel(mApplication, mProductsRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    public static <T extends AndroidViewModel> T obtainViewModel(FragmentActivity activity, Class<T> modelClass) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        T viewModel =
                ViewModelProviders.of(activity, factory).get(modelClass);

        return viewModel;
    }
}