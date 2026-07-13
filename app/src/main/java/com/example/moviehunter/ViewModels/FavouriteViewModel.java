package com.example.moviehunter.ViewModels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.moviehunter.Room.AppDatabase;
import com.example.moviehunter.Room.FavouriteMovie;

import java.util.List;

public class FavouriteViewModel extends AndroidViewModel {

    private final AppDatabase database;
    private final MutableLiveData<List<FavouriteMovie>> favouriteMovies = new MutableLiveData<>();

    public FavouriteViewModel(@NonNull Application application) {
        super(application);

        // Initialize Room
        database = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "movie_database"
        ).allowMainThreadQueries().build();
    }

    // Exposes the list of favourites as LiveData so the UI can observe it
    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {
        return favouriteMovies;
    }

    // Fetches the latest data from the database and updates the LiveData
    public void loadFavourites() {
        List<FavouriteMovie> list = database.favouriteMovieDao().getAllMovies();
        favouriteMovies.setValue(list);
    }

    // Deletes a movie and then reloads the list to update the UI
    public void removeMovie(int movieId) {
        database.favouriteMovieDao().deleteMovie(movieId);
        loadFavourites();
    }
}