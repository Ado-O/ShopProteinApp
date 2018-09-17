package com.example.user.lesson_android_development.data.storage.local.cartitem;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.lesson_android_development.data.CartItem;
import com.example.user.lesson_android_development.data.ProductCartItem;
import java.util.List;

@Dao
public interface CartItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem cartItem);

    @Query("SELECT products_table.*, cart_item_table.quantity " +
            "FROM cart_item_table INNER JOIN products_table " +
            "ON cart_item_table.product_id = products_table._id")
    List<ProductCartItem> getProductCartItems();

    @Query("DELETE FROM cart_item_table WHERE product_id=:productId")
    void clearCartItem(long productId);

}
