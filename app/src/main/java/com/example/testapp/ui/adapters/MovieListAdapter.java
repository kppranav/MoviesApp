package com.example.testapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.data.models.Movies;
import com.example.testapp.data.models.Result;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    List<Result> moviesList = new ArrayList<>();
    private final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w780";

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_normal_item,
                parent,
                false);
        return new MovieListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        Result result = moviesList.get(position);

        holder.movieTitleTextView.setText(result.getTitle());
        holder.movieYearTextView.setText(result.getReleaseDate());
        Glide.with(holder.itemView)
                .load(IMAGE_BASE_URL + result.getBackdropPath())
                .placeholder(R.drawable.image_place_holder)
                .into(holder.movieThumbImageView);

    }

    public void setMoviesList(List<Result> moviesList) {
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        ImageView movieThumbImageView;
        TextView movieTitleTextView;
        TextView movieYearTextView;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            movieThumbImageView = itemView.findViewById(R.id.thumbImageView);
            movieTitleTextView = itemView.findViewById(R.id.movieTitle);
            movieYearTextView = itemView.findViewById(R.id.movieYear);
        }
    }

}
