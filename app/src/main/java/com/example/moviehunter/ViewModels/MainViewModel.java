package com.example.moviehunter.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.moviehunter.Domain.ListFilm;
import com.example.moviehunter.Repository.MovieRepository;

public class MainViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    private LiveData<ListFilm> popularMovies;
    private LiveData<ListFilm> upcomingMovies;

    public MainViewModel() {
        movieRepository = new MovieRepository();
    }
    public LiveData<ListFilm> getPopularMovies() {
        if (popularMovies == null) {
            popularMovies = movieRepository.getPopularMovies();
        }
        return popularMovies;
    }
    public LiveData<ListFilm> getUpcomingMovies() {
        if (upcomingMovies == null) {
            upcomingMovies = movieRepository.getUpcomingMovies();
        }
        return upcomingMovies;
    }

    public LiveData<ListFilm> searchMovies(String query) {
        return movieRepository.searchMovies(query);
    }
}
