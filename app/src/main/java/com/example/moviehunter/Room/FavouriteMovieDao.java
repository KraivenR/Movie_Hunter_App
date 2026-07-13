package com.example.moviehunter.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouriteMovieDao {

    // Add movie to favourites
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavouriteMovie movie);

    // Get all favourite movies
    @Query("SELECT * FROM favourite_movies")
    List<FavouriteMovie> getAllMovies();

    // Delete a favourite movie
    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    void deleteMovie(int movieId);
}