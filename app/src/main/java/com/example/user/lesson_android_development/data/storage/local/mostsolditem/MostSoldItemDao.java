package com.example.user.lesson_android_development.data.storage.local.mostsolditem;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.user.lesson_android_development.data.MostSoldItem;

import java.util.List;

@Dao
public interface MostSoldItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<MostSoldItem> mostSoldItem);

    @Query("DELETE FROM most_sold_item_table")
    void clear();
}
