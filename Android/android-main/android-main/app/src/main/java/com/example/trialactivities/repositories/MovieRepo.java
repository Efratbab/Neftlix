package com.example.trialactivities.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.trialactivities.NetflixApplication;
import com.example.trialactivities.api.MovieAPI;
import com.example.trialactivities.database.MovieDB;
import com.example.trialactivities.entities.Movie;
import com.example.trialactivities.room.MovieDao;

import java.util.LinkedList;
import java.util.List;

public class MovieRepo {
    private MovieDao movieDao;
    private MovieListData movieListData;
    private MovieAPI api;
    public MovieRepo() {
        Log.d("MovieRepo", "Initializing MovieRepo");
        MovieDB db = MovieDB.getInstance(NetflixApplication.getAppContext());
        movieDao = db.movieDao();
        movieListData = new MovieListData();
        api= new MovieAPI(/*movieListData, movieDao*/);
    }

    class MovieListData extends MutableLiveData<List<Movie>> {

        public MovieListData() {
            super();
            setValue(new LinkedList<Movie>());
        }

        @Override
        protected void onActive() {
            super.onActive();
            Log.d("MovieRepo", "Fetching data from LocalDB...");
            new Thread(() -> {
                movieListData.postValue(movieDao.index());
                //for logs
                List<Movie> movies = movieDao.index();
                Log.d("MovieRepo", "Movies from DB: " + movies);
                //for logs
            }).start();
        }
    }
    public LiveData<List<Movie>> getAll() {
        return movieListData;
    }

    public void add(final Movie movie) {
        api.createMovie(movie);
    }

//    public void delete(final Movie movie) {
//        api.deleteMovieByMovieId(movie.getId());
//    }

//    public void reload() {
//        api.getMoviesForUser();
//    }
//
//    /**
//     * Get a specific movie by ID.
//     */
    public void getMovie(int movieID) {
        api.getMovieByMovieId(movieID);
    }
//
//    /**
//     * Update a movie.
//     */
//    public void update(Movie movie) {
//        api.updateMovieByMovieId(movie);
//    }
}
