package com.example.moviehunter.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviehunter.Domain.FilmItem;
import com.example.moviehunter.Network.ApiClient;
import com.example.moviehunter.Network.ApiService;
import com.example.moviehunter.Domain.ListFilm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private final ApiService apiService;

    // TODO: Replace with your actual TMDB API Key
    // You can get one for free at https://www.themoviedb.org/settings/api
    private static final String API_KEY = "YOUR_API_KEY_HERE";

    public MovieRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<FilmItem> getMovieDetails(int movieId) {
        MutableLiveData<FilmItem> movieDetails = new MutableLiveData<>();
        apiService.getMovieDetails(movieId, API_KEY).enqueue(new Callback<FilmItem>() {
            @Override
            public void onResponse(Call<FilmItem> call, Response<FilmItem> response) {
                if (response.isSuccessful()) {
                    movieDetails.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FilmItem> call, Throwable t) {
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
