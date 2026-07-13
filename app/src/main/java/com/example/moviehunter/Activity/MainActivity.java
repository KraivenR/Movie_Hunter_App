package com.example.moviehunter.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviehunter.Adapter.FilmListAdapter;
import com.example.moviehunter.R;
import com.example.moviehunter.ViewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterNewMovies, adapterUpcoming;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpcoming;
    private ProgressBar loading1, loading2;
    private EditText searchEditText;
    private ImageView favButton;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize the ViewModel using ViewModelProvider
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initView();
        loadPopularMovies();
        loadUpcomingMovies();


        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchEditText.getText().toString().trim();

            if (!query.isEmpty()) {
                searchMovies(query);
            }
            return true;
        });

    }

    private void loadPopularMovies() {
        loading1.setVisibility(View.VISIBLE);
        mainViewModel.getPopularMovies().observe(this, items -> {
            loading1.setVisibility(View.GONE);

            if (items != null) {
                adapterNewMovies = new FilmListAdapter(items);
                recyclerViewNewMovies.setAdapter(adapterNewMovies);
            }
        });

    }

    private void loadUpcomingMovies() {

        loading2.setVisibility(View.VISIBLE);

        mainViewModel.getUpcomingMovies().observe(this, items -> {
            loading2.setVisibility(View.GONE);

            if (items != null) {
                adapterUpcoming = new FilmListAdapter(items);
                recyclerViewUpcoming.setAdapter(adapterUpcoming);
            }
        });
    }

    private void initView() {
        recyclerViewNewMovies = findViewById(R.id.view1);
        recyclerViewUpcoming = findViewById(R.id.view2);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewUpcoming.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);

        searchEditText = findViewById(R.id.searchEditText);
        favButton = findViewById(R.id.favButton);

        favButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavouriteActivity.class);
            startActivity(intent);
        });
    }

    private void searchMovies(String query) {
        loading1.setVisibility(View.VISIBLE);

        mainViewModel.searchMovies(query).observe(this, items -> {
            loading1.setVisibility(View.GONE);
            if (items != null) {
                adapterNewMovies = new FilmListAdapter(items);
                recyclerViewNewMovies.setAdapter(adapterNewMovies);
            }
        });
    }

}
