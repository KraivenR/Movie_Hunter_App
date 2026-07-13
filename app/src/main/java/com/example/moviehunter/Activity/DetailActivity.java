package com.example.moviehunter.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviehunter.Network.ApiClient;
import com.example.moviehunter.R;
import com.example.moviehunter.Room.FavouriteMovie;
import com.example.moviehunter.ViewModels.DetailViewModel;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailActivity extends AppCompatActivity {

    private ProgressBar detailLoading;
    private TextView movieNameTxt, movieRateTxt, movieTimeTxt, releaseDateTxt, movieSummaryInfo, movieGenreInfo;
    private int idFilm;
    private ShapeableImageView pic1;
    private RecyclerView recyclerView;
    private ImageView pic2, backImg;
    private ImageView addFavImg;

    private NestedScrollView scrollView;
    private FavouriteMovie currentMovie;
    private DetailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        idFilm = getIntent().getIntExtra("id", 0);

        // Initialize the ViewModel
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        initView();
        loadMovieDetails();

    }

    private void initView() {
        movieNameTxt = findViewById(R.id.movieNameTxt);
        movieRateTxt = findViewById(R.id.movieRateTxt);
        movieTimeTxt = findViewById(R.id.movieTimeTxt);
        releaseDateTxt = findViewById(R.id.releaseDateTxt);
        movieSummaryInfo = findViewById(R.id.movieSummaryInfo);
        movieGenreInfo = findViewById(R.id.movieGenreInfo);
        scrollView = findViewById(R.id.scrollView3);
        detailLoading = findViewById(R.id.detailLoading);
        pic1 = findViewById(R.id.posterNormalImg);
        pic2 = findViewById(R.id.posterBigImg);
        backImg = findViewById(R.id.backImg);
        addFavImg = findViewById(R.id.addFavImg);
        recyclerView = findViewById(R.id.imageRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());

        addFavImg.setOnClickListener(v -> {
            if (currentMovie != null) {
                // Use the ViewModel to handle the database operation
                viewModel.addToFavourites(currentMovie);
                Toast.makeText(this, "Movie added to favourites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please wait for movie details to load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMovieDetails() {
        detailLoading.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        // Use the ViewModel to get movie details
        viewModel.getMovieDetails(idFilm).observe(this, filmItem -> {
            detailLoading.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            if (filmItem == null) {
                movieNameTxt.setText("Error loading details");
                return;
            }

            if (filmItem.getPosterPath() != null) {
                Glide.with(this)
                        .load(ApiClient.IMAGE_BASE_URL + filmItem.getPosterPath())
                        .into(pic1);
            }

            if (filmItem.getBackdropPath() != null) {
                Glide.with(this)
                        .load(ApiClient.IMAGE_BASE_URL + filmItem.getBackdropPath())
                        .into(pic2);
            }

            movieNameTxt.setText(filmItem.getTitle());
            movieRateTxt.setText(String.valueOf(filmItem.getVoteAverage()));
            movieTimeTxt.setText(filmItem.getRuntime() + " min");
            releaseDateTxt.setText(filmItem.getReleaseDate());
            movieSummaryInfo.setText(filmItem.getOverview());

            if (filmItem.getGenres() != null && !filmItem.getGenres().isEmpty()) {
                movieGenreInfo.setText(filmItem.getGenres().get(0).getName());
            }

            // Create the object to be saved as a favourite
            currentMovie = new FavouriteMovie(
                    filmItem.getId(),
                    filmItem.getTitle(),
                    filmItem.getPosterPath(),
                    filmItem.getVoteAverage(),
                    filmItem.getOverview()
            );

            // initialize  horizontal recyclerView
            recyclerView.setAdapter(null);
        });
    }
}
