package com.example.moviehunter.Room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_movies")
public class FavouriteMovie {
    @PrimaryKey
    private int id;
    private String title;
    private String posterPath;
    private double voteAverage;
    private String overview;

    public FavouriteMovie() {
    }

    public FavouriteMovie(int id, String title, String posterPath, double voteAverage, String overview) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
