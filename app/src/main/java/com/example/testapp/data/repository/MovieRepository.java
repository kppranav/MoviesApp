package com.example.testapp.data.repository;

import android.app.Application;
import android.util.Log;

import com.example.testapp.data.api.MovieService;
import com.example.testapp.data.models.Movies;
import com.example.testapp.data.models.Result;
import com.example.testapp.db.ResultDao;
import com.example.testapp.db.ResultDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {
    private static final String BASE_URL = "https://api.themoviedb.org/";

    MovieService apiService;

    ResultDao resultDao;

    MutableLiveData<List<Result>> mutableLiveData;

    public MovieRepository(Application application) {

        mutableLiveData = new MutableLiveData<>();

        // Rest API service
        apiService = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build().
                create(MovieService.class);

        //Room - Database
        ResultDatabase resultDatabase = ResultDatabase.getDatabase(application);
        resultDao = resultDatabase.resultDao();

    }

    public MutableLiveData<List<Result>> getMoviesListFromUrl(String apiKey, String language, String page, String region,
                                                              String withReleaseType){

        apiService.getMoviesList(apiKey,language, page, region, withReleaseType).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                Log.d("TAG", "success : " + response.body().getResults().size());
                mutableLiveData.postValue(response.body().getResults());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("TAG", "Failed : " + t.getMessage());
                mutableLiveData.postValue(null);
            }
        });

        return mutableLiveData;

    }

    public LiveData<List<Result>> getMovieListFromDB() {
        return resultDao.getAllResults();
    }

    public void addResultToDb(Result result) {
        ResultDatabase.databaseWriteExecutor.execute(() ->{
            resultDao.insert(result);
        });
    }

    public LiveData<List<Result>> getNewVideosListFromDB() {
        return resultDao.getNewVideos();
    }

    public LiveData<List<Result>> getBestRatedList() {
        return resultDao.getBestRatedResults();
    }
}
