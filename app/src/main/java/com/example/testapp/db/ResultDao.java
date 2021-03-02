package com.example.testapp.db;

import com.example.testapp.data.models.Result;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Result result);

    @Query("SELECT * FROM results_table")
    LiveData<List<Result>> getAllResults();

    @Query("SELECT * FROM results_table WHERE voteCount > 5")
    LiveData<List<Result>> getBestRatedResults();

    @Query("SELECT * FROM results_table WHERE backdropPath IS NOT NULL")
    LiveData<List<Result>> getNewVideos();

}
