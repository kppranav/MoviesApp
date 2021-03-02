package com.example.testapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.testapp.R;
import com.example.testapp.data.models.Result;
import com.example.testapp.ui.adapters.MovieListAdapter;
import com.example.testapp.ui.adapters.PosterAdapter;
import com.example.testapp.ui.viewModels.MoviesViewModel;

public class MainActivity extends AppCompatActivity {

    MoviesViewModel moviesViewModel;

    final String API_KEY = "9c0523bff54071c4fb4b716a950231b9";

    MovieListAdapter listAdapter;
    PosterAdapter newVideosAdapter, bestRatedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RecyclerViewFor New Videos List - Horizontal
        RecyclerView recyclerView = findViewById(R.id.newReleaseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new MovieListAdapter();
        recyclerView.setAdapter(listAdapter);

        RecyclerView newVideosList = findViewById(R.id.newVideos);
        newVideosList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));
        newVideosAdapter = new PosterAdapter();
        newVideosList.setAdapter(newVideosAdapter);

        RecyclerView bestRated = findViewById(R.id.bestRated);
        bestRated.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,
                false));
        bestRatedAdapter = new PosterAdapter();
        bestRated.setAdapter(bestRatedAdapter);



        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        /*moviesViewModel.getMovieListFromUrl(API_KEY, "en-US", "1", "IN|US",
                "2|3");*/


        // Fetch New Release List
        moviesViewModel.getMovieListFromDB().observe(this, list -> {
            Log.d("TAG", "Size : " + list.size());
            if (list.size() == 0) {
                moviesViewModel.getMovieListFromUrl(API_KEY, "en-US", "1", "IN|US",
                        "2|3").observe(this, movieList -> {
                            for (Result result: movieList) {
                                moviesViewModel.addResultToDb(result);
                            }
                });

            } else {
                listAdapter.setMoviesList(list);
            }

        });

        // Fetch new Videos List
        moviesViewModel.getNewVideosListFromDB().observe(this, newVideos ->{
            Log.d("TAG", "New : " + newVideos.size());
            newVideosAdapter.setMovieList(newVideos);
        });

        //Fetch best rated video list
        moviesViewModel.getBestRatedList().observe(this, bestRatedList -> {
            Log.d("TAG", "Best : " + bestRatedList.size());
            bestRatedAdapter.setMovieList(bestRatedList);
        });


    }
}