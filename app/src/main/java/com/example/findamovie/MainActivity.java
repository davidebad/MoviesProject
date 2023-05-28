package com.example.findamovie;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import com.example.findamovie.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EditText searchEditText;
    private Button searchButton;
    private ListView moviesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        moviesListView = findViewById(R.id.moviesListView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = searchEditText.getText().toString();
                searchMovies(movie);
            }
        });
    }

    private void searchMovies(String query) {
        ApiClient.MovieApi movieApi = ApiClient.getMovieApi();
        Call<SearchResponse> call = movieApi.searchMovies(ApiClient.API_KEY, query);

        call.enqueue(new Callback<SearchResponse>() { //asynchronous call to the API
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SearchResponse searchResponse = response.body();

                    Gson gson = new Gson();

                    List<Movie> movies = searchResponse.getMovies();
                    for (Movie movie : movies) {
                        System.out.println("Title: " + movie.getTitle());
                        System.out.println("Release Date: " + movie.getDate());
                        System.out.println("Language: " + movie.getLanguage());
                        System.out.println("Description: " + movie.getDescription());
                        System.out.println("Vote: " + movie.getVote());
                    }
                    showMovies(movies);
                }
                else {
                    System.out.println("Error in the response");
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                //Handling the error
                String errorMessage = "Error during calling the API: " + t.getMessage();
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showMovies(List<Movie> movies) {
        List<String> movieTitles = new ArrayList<>();
        for (Movie m : movies) {
            movieTitles.add(m.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, movieTitles);
        moviesListView.setAdapter(adapter);

        moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movies.get(position);
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);

                System.out.println("Title: " + selectedMovie.getTitle());
                System.out.println("Date: " + selectedMovie.getDate());
                System.out.println("Language: " + selectedMovie.getLanguage());
                System.out.println("Description: " + selectedMovie.getDescription());

                intent.putExtra("title", selectedMovie.getTitle());
                intent.putExtra("date", selectedMovie.getDate());
                intent.putExtra("language", selectedMovie.getLanguage());
                intent.putExtra("description", selectedMovie.getDescription());
                intent.putExtra("vote", selectedMovie.getVote());

                startActivity(intent);
            }
        });
    }
}