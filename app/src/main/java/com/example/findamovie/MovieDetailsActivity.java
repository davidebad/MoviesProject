package com.example.findamovie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.findamovie.databinding.ActivityMainBinding;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView dateTextView;
    private TextView languageTextView;
    private TextView descriptionTextView;
    private TextView voteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        titleTextView = findViewById(R.id.titleTextView);
        dateTextView = findViewById(R.id.dateTextView);
        languageTextView = findViewById(R.id.languageTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        voteTextView = findViewById(R.id.voteTextView);

        Intent intent = getIntent();

        // Show the movie details
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String date = intent.getStringExtra("date");
            String language = intent.getStringExtra("language");
            String description = intent.getStringExtra("description");
            String vote = intent.getStringExtra("vote");

            titleTextView.setText(titleTextView.getText()+title);
            dateTextView.setText(dateTextView.getText()+date);
            languageTextView.setText(languageTextView.getText()+language);
            descriptionTextView.setText(descriptionTextView.getText()+description);
            voteTextView.setText(voteTextView.getText()+vote);
        }
    }
}
