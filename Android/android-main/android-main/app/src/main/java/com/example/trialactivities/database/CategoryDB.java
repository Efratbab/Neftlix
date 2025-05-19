package com.example.trialactivities.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

import com.example.trialactivities.entities.Category;
import com.example.trialactivities.room.CategoryDao;
import com.example.trialactivities.utilities.Converters;

@Database(entities = {Category.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class CategoryDB extends RoomDatabase {
    private static volatile CategoryDB INSTANCE;
    public static CategoryDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CategoryDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CategoryDB.class, "local_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CategoryDao categoryDao();

}
