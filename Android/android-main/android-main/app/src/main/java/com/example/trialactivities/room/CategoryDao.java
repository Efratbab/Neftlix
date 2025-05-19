package com.example.trialactivities.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trialactivities.entities.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM category")
    List<Category> index();

    @Query("SELECT * FROM category WHERE id = :id")
    Category get(int id);

    @Query("DELETE FROM category")
    void clear();

    @Insert
    void insert(List<Category> categories);

    @Update
    void update(Category... categories);

    //Delete a category object
    @Delete
    void delete(Category... categories);

    //
    @Query("DELETE FROM category WHERE id = :categoryId")
    void deleteById(int categoryId);
}
