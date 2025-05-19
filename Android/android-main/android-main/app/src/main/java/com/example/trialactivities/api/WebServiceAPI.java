package com.example.trialactivities.api;

import android.net.Uri;

import com.example.trialactivities.entities.Category;
import com.example.trialactivities.entities.Movie;
import com.example.trialactivities.entities.User;
import com.example.trialactivities.utilities.SignInResponse;
import com.example.trialactivities.utilities.LoginRequest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface WebServiceAPI {
    //Functions for Category
    @GET("categories")
    Call<List<Category>> getCategories(@Header("Authorization") String token);

    @POST("categories")
    Call<Void> createCategory(@Header("Authorization") String token, @Body Category category);

    @DELETE("categories/{id}")
    Call<Void> deleteCategory (@Header("Authorization") String token, @Path("id") int id);

    @PATCH("categories/{id}")
    Call<Void> updateCategory (@Header("Authorization") String token, @Path("id") int id,  @Body Category category);

    @GET("categories/{id}")
    Call<Category> getCategory (@Header("Authorization") String token, @Path("id") int id);

    //Functions for Movie
    @GET("movies")
    Call<List<Movie>> getMoviesForUser (@Header("Authorization") String authHeader);

    @POST("movies")
    Call<Void> createMovie (@Header("Authorization") String authHeader,
                            @Part MultipartBody.Part file,
                            @Part MultipartBody.Part thumbnail,
                            @Part("name") RequestBody name,
                            @Part("categories") RequestBody categories);

    @GET("movies/{id}")
    Call<Movie> getMovieByMovieId (@Header("Authorization") String authHeader, @Path("id") int id);

    @PUT("movies/{id}")
    Call<Void> updateMovieByMovieId (@Header("Authorization") String authHeader, @Path("id") int id,
                                     @Part MultipartBody.Part file,
                                     @Part MultipartBody.Part thumbnail,
                                     @Part("name") RequestBody name,
                                     @Part("categories") RequestBody categories);

    @DELETE("movies/{id}")
    Call<Void> deleteMovieByMovieId (@Header("Authorization") String authHeader, @Path("id") int id);

    //the recommend

    @GET("movies/{id}/recommend/")
    Call<List<Movie>> getRecommendedMovies (@Path("id") int id, @Header("Authorization") String authHeader);

    @POST("movies/{id}/recommend/")
    Call<Void> updateUserWatchedMovie (@Path("id") int id, @Header("Authorization") String authHeader);

    @GET("/search/{query}/")
    Call<List<Movie>>searchMovies(@Header("Authorization") String authHeader, @Path("query") String regex);

    //Functions for users
//    @PUT("users/{id}")
//    Call<Void> updateUser(@Path("id") int id,
//                          @Part MultipartBody.Part profilePicture,
//                          @Part("username") RequestBody username,
//                          @Part("password") RequestBody password,
//                          @Part("nameForDisplay") RequestBody nameForDisplay
//    );

//    @GET("users/{id}")
//    Call<User> getUser(@Path("id") int id);
    @Multipart
    @POST("users/register")
    Call<Void> signUp( @Part MultipartBody.Part profilePicture,
                       @Part("username") RequestBody username,
                       @Part("password") RequestBody password,
                       @Part("nameForDisplay") RequestBody nameForDisplay);

    @POST("tokens")
    Call<SignInResponse> signIn(@Body LoginRequest loginRequest);
}

