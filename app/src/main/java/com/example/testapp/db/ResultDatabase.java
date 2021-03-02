package com.example.testapp.db;

import android.content.Context;

import com.example.testapp.data.models.Result;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class ResultDatabase extends RoomDatabase {
    public abstract ResultDao resultDao();

    private static volatile ResultDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static ResultDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ResultDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ResultDatabase.class, "result_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
