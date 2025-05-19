package com.example.trialactivities.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

import com.example.trialactivities.entities.User;
import com.example.trialactivities.room.UserDao;
import com.example.trialactivities.utilities.Converters;

@Database(entities = {User.class}, version = 4)
@TypeConverters(Converters.class)
public abstract class UserDB extends RoomDatabase {

    private static UserDB instance;

    public static synchronized UserDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDB.class, "user_database")
                    .fallbackToDestructiveMigration() // ⚠️ Destroys and recreates DB if schema changes!
                    .build();
        }
        return instance;
    }

    public abstract UserDao userDao();
}