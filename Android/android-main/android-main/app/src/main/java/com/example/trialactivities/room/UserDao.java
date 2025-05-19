package com.example.trialactivities.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.trialactivities.entities.User;

import java.util.List;
@Dao
public interface UserDao {
    @Query("SELECT DISTINCT * FROM user")
    List<User> index();

    @Query("SELECT * FROM user WHERE id = :id")
    User get(int id);

    @Query("DELETE FROM user")
    void clear();

    @Query("SELECT COUNT(*) FROM user WHERE username = :username")
    int countByUsername(String username);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<User> users);

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    User getUserByUsername(String username);

    @Update
    void update(User... users);

    @Query("DELETE FROM user")
    void clearAllUsers();
}
