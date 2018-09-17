package com.example.user.lesson_android_development.data.storage.remote.content;


import android.content.Context;

import com.example.user.lesson_android_development.util.MockJson;
import com.example.user.lesson_android_development.data.storage.remote.response.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ProductsRemoteDataSource {

    private static final String TAG = ProductsRemoteDataSource.class.getSimpleName();

    private static ProductsRemoteDataSource sInstance;
    private final Context mContext;

    public ProductsRemoteDataSource(Context context) {

        mContext = context;
    }

    public static ProductsRemoteDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ProductsRemoteDataSource(context);
        }
        return sInstance;
    }

    public void getProducts(final GetProductsCallback callback) {

        /**
         * cod for MockJson
         */
        BaseResponse baseResponse =
                new Gson().fromJson(
                        MockJson.getJsonFromAsset(
                                mContext, "mock.txt"
                        ),
                        new TypeToken<BaseResponse>() {
                        }.getType()
                );

        callback.onSuccess(
                baseResponse
        );

   }

    public interface GetProductsCallback {
        void onSuccess(BaseResponse baseResponse);

        void onError();
    }
}
