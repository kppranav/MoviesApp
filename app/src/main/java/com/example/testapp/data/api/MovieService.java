package com.example.testapp.data.api;

import com.example.testapp.data.models.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("3/movie/upcoming")
    Call<Movies> getMoviesList(@Query("api_key") String api_key,
                                     @Query("language") String language,
                                     @Query("page") String page,
                                     @Query("region") String region,
                                     @Query("with_release_type") String releaseType);

}
