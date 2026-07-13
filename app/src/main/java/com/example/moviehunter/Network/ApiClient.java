package com.example.moviehunter.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// This is how my app should communicate with the movie database
// handles requests and responses(converts json to Java objects)
public class ApiClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private static Retrofit retrofit;

    // Checks whether a Retrofit instance has already been created
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    }
