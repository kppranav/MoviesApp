package com.example.testapp.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.data.models.Result;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    List<Result> movieList = new ArrayList<>();

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_banner,
                parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {

        Result result = movieList.get(position);
        holder.titleTextView.setText(result.getTitle());
        Glide.with(holder.itemView)
                .load(IMAGE_BASE_URL + result.getPosterPath())
                .placeholder(R.drawable.image_place_holder)
                .into(holder.posterImage);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Result> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder {

        ImageView posterImage;
        TextView titleTextView;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImage = itemView.findViewById(R.id.bannerImage);
            titleTextView = itemView.findViewById(R.id.titleText);
        }
    }
}
