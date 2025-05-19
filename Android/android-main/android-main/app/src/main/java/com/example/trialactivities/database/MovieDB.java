package com.example.trialactivities.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.trialactivities.entities.Movie;
import com.example.trialactivities.room.MovieDao;
import com.example.trialactivities.utilities.Converters;

@Database(entities = {Movie.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class MovieDB extends RoomDatabase{
    private static volatile com.example.trialactivities.database.MovieDB INSTANCE;
    public static com.example.trialactivities.database.MovieDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.trialactivities.database.MovieDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    com.example.trialactivities.database.MovieDB.class, "local_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();
}
