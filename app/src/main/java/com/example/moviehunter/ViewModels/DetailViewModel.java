package com.example.moviehunter.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.moviehunter.Domain.FilmItem;
import com.example.moviehunter.Repository.MovieRepository;
import com.example.moviehunter.Room.AppDatabase;
import com.example.moviehunter.Room.FavouriteMovie;

public class DetailViewModel extends AndroidViewModel {

    private final MovieRepository movieRepository;
    private final AppDatabase database;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();
        database = Room.databaseBuilder(
                application.getApplicationContext(),
                AppDatabase.class,
                "movie_database"
        ).allowMainThreadQueries().build();
    }

    public LiveData<FilmItem> getMovieDetails(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }

    public void addToFavourites(FavouriteMovie movie) {
        database.favouriteMovieDao().insert(movie);
    }
}
