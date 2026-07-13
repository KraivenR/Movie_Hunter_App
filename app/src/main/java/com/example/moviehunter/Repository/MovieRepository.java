package com.example.moviehunter.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviehunter.Domain.FilmItem;
import com.example.moviehunter.Network.ApiClient;
import com.example.moviehunter.Network.ApiService;
import com.example.moviehunter.Domain.ListFilm;
import com.example.moviehunter.Domain.FilmItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//Fetch movie data from TMDB and provide it to the rest of the app.
public class MovieRepository {
    //This is the tool the repository uses to request movie data
    private final ApiService apiService;
    //Retrofit store and use the API key to access the movie database
    private static final String API_KEY = "f67059b8d5e75b5738c264fcf958e2ff";

    public MovieRepository() {
        //Connect the repository to the movie API
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    // Fetch details
    public LiveData<FilmItem> getMovieDetails(int movieId) {
        // Create a MutableLiveData to hold the movie details
        MutableLiveData<FilmItem> movieDetails = new MutableLiveData<>();
        // Use the Retrofit API to fetch the movie details
        apiService.getMovieDetails(movieId, API_KEY).enqueue(new Callback<FilmItem>() {
            @Override
            public void onResponse(Call<FilmItem> call, Response<FilmItem> response) {
                // If the request succeeds, store the movie details
                if (response.isSuccessful()) {
                    movieDetails.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FilmItem> call, Throwable t) {
                // If the request fails, handle the error
                // No movie data is received, so movie details cannot be displayed.
            }
        });

        return movieDetails;

    }
    public LiveData<ListFilm> getPopularMovies() {
        MutableLiveData<ListFilm> popularMovies = new MutableLiveData<>();

        apiService.getPopularMovies(API_KEY).enqueue(new Callback<ListFilm>() {
            @Override
            public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
                if (response.isSuccessful()) {
                    popularMovies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListFilm> call, Throwable t) {
            }
        });

        return popularMovies;

        }
        public LiveData<ListFilm> getUpcomingMovies() {
        MutableLiveData<ListFilm> upcomingMovies = new MutableLiveData<>();

        apiService.getUpcomingMovies(API_KEY).enqueue(new Callback<ListFilm>() {
            @Override
            public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
                if (response.isSuccessful()) {
                    upcomingMovies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListFilm> call, Throwable t) {
            }
        });

        return upcomingMovies;
    }

    public LiveData<ListFilm> searchMovies(String query) {
        MutableLiveData<ListFilm> searchResults = new MutableLiveData<>();

        apiService.searchMovies(API_KEY, query).enqueue(new Callback<ListFilm>() {
            @Override
            public void onResponse(Call<ListFilm> call, Response<ListFilm> response) {
                if (response.isSuccessful()) {
                    searchResults.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ListFilm> call, Throwable t) {
            }
        });

        return searchResults;
    }
}
