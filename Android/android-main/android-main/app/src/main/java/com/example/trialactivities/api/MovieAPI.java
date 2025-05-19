package com.example.trialactivities.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.trialactivities.entities.Movie;
import com.example.trialactivities.room.MovieDao;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieAPI {
    //private MutableLiveData<List<Movie>> movieListData;
    //private MovieDao movieDao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public MovieAPI(/*MutableLiveData<List<Movie>> movieListData, MovieDao movieDao*/) {
        //this.movieListData = movieListData;
        //this.movieDao = movieDao;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.177.114:3000/api/")  // Use your PC's IP instead of 10.0.2.2
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void getMoviesForUser(String token) {
        Call<List<Movie>> call = webServiceAPI.getMoviesForUser(token);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movie = response.body();
//                new Thread(() -> {
//                    movieDao.clear();  // Clears old data
//                    movieDao.insert(response.body());  // Inserts new movies into the database
//                    movieListData.postValue(movieDao.index());  // Updates LiveData with new data
//                }).start();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("MovieAPI", "API call failed", t);
            }
        });
    }

    public void createMovie(Movie movie){
        Call<Void> call = webServiceAPI.createMovie(movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                new Thread(() -> {
//                    movieDao.insert((List<Movie>) movie);  // Inserts the new movie without clearing existing data
//                    movieListData.postValue(movieDao.index());  // Updates LiveData with new data
//                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void getMovieByMovieId(int movieID){
        Call<Movie> call = webServiceAPI.getMovieByMovieId(movieID);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
//                    new Thread(() -> {
//                        movieDao.insert((List<Movie>) movie);  // Save the movie in the local DB
//                        movieListData.postValue(movieDao.index());  //Update LiveData
//                    }).start();
 //               }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            }
        });
    }

    public void deleteMovieByMovieId(int movieID){
        //Delete from server
        Call<Void> call = webServiceAPI.deleteMovieByMovieId(movieID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Delete from
//                new Thread(() -> {
//                    movieDao.deleteById(movieID);
//                    movieListData.postValue(movieDao.index()); // UI updates immediately
//                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }

    public void updateMovieByMovieId(int movieID, Movie movie){
        //Update on server
        Call<Void> call = webServiceAPI.updateMovieByMovieId(movieID, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //Update on movieDB
//                new Thread(() -> {
//                    movieDao.update(movie);
//                    movieListData.postValue(movieDao.index()); // UI updates immediately
//                }).start();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });
    }

    //TODO- remove the // and fix. also, token needed
//    public void getRecommendedMovies() {
//        Call<List<Movie>> call = webServiceAPI.getRecommendedMovies(????????);
//        call.enqueue(new Callback<List<Movie>>() {
//            @Override
//            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
//                List<Movie> movie = response.body();
//                new Thread(() -> {
//                    movieDao.clear();  // Clears old data
//                    movieDao.insert(response.body());  // Inserts new movies into the database
//                    movieListData.postValue(movieDao.index());  // Updates LiveData with new data
//                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<List<Movie>> call, Throwable t) {
//                Log.e("MovieAPI", "API call failed", t);
//            }
//        });
//    }


    //TODO- remove the // and fix. token needed
//    public void updateUserWatchedMovie(int movieID, String token){
//        //Update on server
//        Call<Void> call = webServiceAPI.updateUserWatchedMovie(movieID, token);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                //Update on movieDB
////                new Thread(() -> {
////                    movieDao.update(movie);
////                    movieListData.postValue(movieDao.index()); // UI updates immediately
////                }).start();
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {}
//        });
//    }

    public void searchMovies(String regex) {
        Call<List<Movie>> call = webServiceAPI.searchMovies(regex);
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                List<Movie> movie = response.body();
//                new Thread(() -> {
//                    movieDao.clear();  // Clears old data
//                    movieDao.insert(response.body());  // Inserts new movies into the database
//                    movieListData.postValue(movieDao.index());  // Updates LiveData with new data
//                }).start();
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("MovieAPI", "API call failed", t);
            }
        });
    }
}
