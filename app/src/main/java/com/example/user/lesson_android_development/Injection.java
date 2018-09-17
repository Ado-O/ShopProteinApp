package com.example.user.lesson_android_development;

import android.content.Context;

import com.example.user.lesson_android_development.data.storage.ProductsRepository;
import com.example.user.lesson_android_development.data.storage.local.AppDatabase;
import com.example.user.lesson_android_development.data.storage.local.cartitem.CartItemLocalDataSource;
import com.example.user.lesson_android_development.data.storage.local.product.ProductLocalDataSource;
import com.example.user.lesson_android_development.data.storage.remote.content.ProductsRemoteDataSource;
import com.example.user.lesson_android_development.util.AppExecutors;

public class Injection {

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    public static ProductsRemoteDataSource provideProductsRemoteDataSource(Context context){
        return ProductsRemoteDataSource.getInstance(context);
    }

    public static ProductLocalDataSource provideProductsLocalDataSource(Context context){
        return ProductLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getProductsDao(),
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getProductImageDao(),
                provideAppDatabase(context.getApplicationContext()).getProductDescriptionDao(),
                provideAppDatabase(context.getApplicationContext()).getTagDao(),
                provideAppDatabase(context.getApplicationContext()).getMostSoldItemDao()
        );
    }

    public static CartItemLocalDataSource provideCartItemLocalDataSource(Context context){
        return CartItemLocalDataSource.getInstance(
                provideAppExecutors(),
                provideAppDatabase(context.getApplicationContext()).getCartItemDao(),
                provideAppDatabase(context.getApplicationContext()).getProductsDao()
        );
    }

    public static ProductsRepository provideProductsRepository(Context context){
        return ProductsRepository.getInstance(
                provideProductsRemoteDataSource(context),
                provideProductsLocalDataSource(context),
                provideCartItemLocalDataSource(context)
        );
    }


}
