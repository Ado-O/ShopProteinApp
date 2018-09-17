package com.example.user.lesson_android_development.data.storage.local.productdescription;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.data.ProductImage;

import java.util.List;

@Dao
public interface ProductDescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ProductDescription> productDescriptions);

    @Query("SELECT * FROM productdescription_table WHERE product_id=:productId")
    List<ProductDescription> getProductDescription(long productId);

    @Query("DELETE FROM productdescription_table")
    void clear();

}
