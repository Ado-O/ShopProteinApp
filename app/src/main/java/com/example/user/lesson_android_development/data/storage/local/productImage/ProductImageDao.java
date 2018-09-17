package com.example.user.lesson_android_development.data.storage.local.productImage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.lesson_android_development.data.ProductImage;

import java.util.List;

@Dao
public interface ProductImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ProductImage> productImages);

    @Query("SELECT * FROM productimage_table WHERE product_id=:productId")
    List<ProductImage> getProductImage(long productId);

    @Query("DELETE FROM productimage_table")
    void clear();

}
