package com.example.moviehunter.Activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviehunter.Adapter.FavouriteListAdapter;
import com.example.moviehunter.R;
import com.example.moviehunter.ViewModels.FavouriteViewModel;

public class FavouriteActivity extends AppCompatActivity {

    private RecyclerView favouriteRecyclerView;
    private FavouriteListAdapter favouriteListAdapter;
    private FavouriteViewModel favouriteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_room);

        initView();
        
        // Initialize the ViewModel
        favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);

        // Observe the LiveData from the ViewModel
        favouriteViewModel.getFavouriteMovies().observe(this, favouriteMovies -> {
            // Update the UI whenever the data changes
            favouriteListAdapter = new FavouriteListAdapter(favouriteMovies);
            favouriteRecyclerView.setAdapter(favouriteListAdapter);
        });

        // Trigger the initial load of data
        favouriteViewModel.loadFavourites();
    }

    private void initView() {
        ImageView backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(v -> finish());

        favouriteRecyclerView = findViewById(R.id.view1);
        favouriteRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
