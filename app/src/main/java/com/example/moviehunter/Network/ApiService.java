package com.example.moviehunter.Network;

import com.example.moviehunter.Domain.ListFilm;
import com.example.moviehunter.Domain.FilmItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

        @GET("movie/popular")
        Call<ListFilm> getPopularMovies(@Query("api_key") String apiKey);

        @GET("movie/upcoming")
        Call<ListFilm> getUpcomingMovies(@Query("api_key") String apiKey);

        @GET("search/movie")
        Call<ListFilm> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);

        @GET("movie/{movie_id}")
        Call<FilmItem> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}
