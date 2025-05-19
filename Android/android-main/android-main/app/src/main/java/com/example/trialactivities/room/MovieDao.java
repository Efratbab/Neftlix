package com.example.trialactivities.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trialactivities.entities.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie")
    List<Movie> index();

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie get(int id);

    @Query("DELETE FROM movie")
    void clear();

    @Insert
    void insert(List<Movie> movies);

    @Update
    void update(Movie... movies);

    @Delete
    void delete(Movie... movies);

    //
    @Query("DELETE FROM movie WHERE id = :movieId")
    void deleteById(int movieId);
}

