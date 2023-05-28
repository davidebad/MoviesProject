package com.example.findamovie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ApiClient {
    public static final String API_KEY = "7edd962dc4e324d6afde2d1fb880ced6";
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MovieApi getMovieApi() {
        return getRetrofitInstance().create(MovieApi.class);
    }

    public interface MovieApi {
        @GET("search/movie")
        Call<SearchResponse> searchMovies(
                @Query("api_key") String apiKey,
                @Query("query") String query
        );
    }
}

