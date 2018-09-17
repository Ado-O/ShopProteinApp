package com.example.user.lesson_android_development.data.storage.local.tag;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.lesson_android_development.data.Tag;

import java.util.List;

@Dao
public interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Tag> tags);

    @Query("SELECT * FROM tag_table")
    List<Tag> getTags();

}
