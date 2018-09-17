package com.example.user.lesson_android_development.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class MockJson {
    public static String getJsonFromAsset(Context context, String path) {
    String json;
    try {
        InputStream is = context.getAssets().open(path);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }        return json;
}}
