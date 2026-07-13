package com.example.moviehunter.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviehunter.Activity.DetailActivity;
import com.example.moviehunter.Domain.ListFilm;
import com.example.moviehunter.Network.ApiClient;
import com.example.moviehunter.R;
import com.example.moviehunter.Room.FavouriteMovie;

import java.util.List;
public class FavouriteListAdapter extends RecyclerView.Adapter<FavouriteListAdapter.ViewHolder> {

    private final List<FavouriteMovie> favouriteMovies;
    public FavouriteListAdapter(List<FavouriteMovie> favouriteMovies) {
        this.favouriteMovies = favouriteMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_favourite_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteListAdapter.ViewHolder holder, int position) {

        FavouriteMovie movie = favouriteMovies.get(position);


        holder.titleTxt.setText(movie.getTitle());
        holder.scoreTxt.setText(String.valueOf(movie.getVoteAverage()));

        Glide.with(holder.itemView.getContext())
                .load(ApiClient.IMAGE_BASE_URL + movie.getPosterPath())
                .into(holder.pic);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    holder.itemView.getContext(),
                    DetailActivity.class
            );

            intent.putExtra("id", movie.getId());

            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favouriteMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView titleTxt;
        TextView scoreTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pic = itemView.findViewById(R.id.pic);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            scoreTxt = itemView.findViewById(R.id.scoreTxt);
        }
    }
}
