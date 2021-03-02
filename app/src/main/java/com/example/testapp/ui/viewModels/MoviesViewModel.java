package com.example.testapp.ui.viewModels;

import android.app.Application;

import com.example.testapp.data.models.Result;
import com.example.testapp.data.repository.MovieRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MoviesViewModel extends AndroidViewModel {
    MovieRepository repository;

    MutableLiveData<List<Result>> listMutableLiveData;
    public MoviesViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
    }

    public MutableLiveData<List<Result>> getMovieListFromUrl(String apiKey, String language, String page, String region,
                                                             String withReleaseType) {
        MutableLiveData<List<Result>> moviesListFromUrl = repository.getMoviesListFromUrl(apiKey,
                language, page, region, withReleaseType);
        return moviesListFromUrl;
    }

    public androidx.lifecycle.LiveData<List<Result>> getMovieListFromDB(){
        return repository.getMovieListFromDB();
    }

    public void addResultToDb(Result result) {
        repository.addResultToDb(result);
    }

    public LiveData<List<Result>> getNewVideosListFromDB() {
        return repository.getNewVideosListFromDB();
    }

    public LiveData<List<Result>> getBestRatedList() {
        return repository.getBestRatedList();
    }
}
