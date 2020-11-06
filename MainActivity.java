package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

class Movie {
    String Title;
    String Year;
    String Released;
    String Runtime;
    String Genre;
    String Director;
    String Country;
}

class Movies {
    ArrayList<Movie> movies;
}


public class MainActivity extends AppCompatActivity {

    TextView title,year,released,runtime,genre,director,country;
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = loadMovies();
        genre = findViewById(R.id.genreB);
        title = findViewById(R.id.titleB);
        year = findViewById(R.id.yearB);
        runtime = findViewById(R.id.runtimeB);
        released = findViewById(R.id.releasedB);
        director = findViewById(R.id.directorB);
        country = findViewById(R.id.countryB);

    }


    public ArrayList<Movie> loadMovies() {
        InputStream stream = null;
        try {
            stream = getAssets().open("movies2.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(stream);

        Gson gson = new Gson();
        Movies ms = gson.fromJson(reader, Movies.class);
        return ms.movies;
    }



    public void onClick(View v) {
        if (movies.size() != 0) {
            int randomId = (int) (Math.random() * movies.size());

            genre.setText(movies.get(randomId).Genre);
            title.setText(movies.get(randomId).Title);
            released.setText(movies.get(randomId).Released);
            year.setText(movies.get(randomId).Year);
            runtime.setText(movies.get(randomId).Runtime);
            director.setText(movies.get(randomId).Director);
            country.setText(movies.get(randomId).Country);

            movies.remove(randomId);
        } else {
            genre.setText("Фильмы кончились(");
            title.setText("");
            released.setText("");
            year.setText("");
            runtime.setText("");

            director.setText("");
            country.setText("");
        }
    }
}
