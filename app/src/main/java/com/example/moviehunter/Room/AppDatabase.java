package com.example.moviehunter.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {FavouriteMovie.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavouriteMovieDao favouriteMovieDao();

}