package com.example.android.frankhaolunlipopularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class MovieInformation extends AppCompatActivity {

    // Declaring Java Nodes
    private static final String TAG_MOVIE_INFO = "results";
    private static final String TAG_VOTE_COUNT = "vote_count";
    private static final String TAG_MOVIE_ID = "id";
    private static final String TAG_VIDEO = "video";
    private static final String TAG_VOTE_AVERAGE = "vote_average";
    private static final String TAG_TITLE = "title";
    private static final String TAG_POPULARITY = "popularity";
    private static final String TAG_POSTER_PATH = "poster_path";
    private static final String TAG_ORIGINAL_LANGUAGE = "original_language";
    private static final String TAG_ORIGINAL_TITLE = "original_title";
    private static final String TAG_GENRE_IDS = "genre_ids";
    private static final String TAG_BACKDROP_PATH = "backdrop_path";
    private static final String TAG_ADULT = "adult";
    private static final String TAG_OVERVIEW = "overview";
    private static final String TAG_RELEASE_DATE = "release_date";

    String title = "";
    String rating = "Rating: ";
    String popularity = "Popularity: ";
    String originalLanguage = "Original Language: ";
    String releaseDate = "Release Date: ";
    String overview = "";
    String posterPath = "Poster Path: ";

    TextView titleTextView;
    TextView ratingTextView;
    TextView popularityTextView;
    TextView originalLanguageTextView;
    TextView releaseDateTextView;
    TextView overviewTextView;
    ImageView posterImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);

        // Get Views
        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("theMovieClicked");
        title = hashMap.get(TAG_TITLE);
        rating += hashMap.get(TAG_VOTE_AVERAGE);
        popularity += hashMap.get(TAG_POPULARITY);
        originalLanguage += hashMap.get(TAG_ORIGINAL_LANGUAGE);
        releaseDate += hashMap.get(TAG_RELEASE_DATE);
        overview += hashMap.get(TAG_OVERVIEW);

        // Set up Views
        titleTextView = (TextView) findViewById(R.id.movie_title);
        ratingTextView = (TextView) findViewById(R.id.rating);
        popularityTextView = (TextView) findViewById(R.id.popularity);
        originalLanguageTextView = (TextView) findViewById(R.id.original_language);
        releaseDateTextView = (TextView) findViewById(R.id.release_date);
        overviewTextView = (TextView) findViewById(R.id.oveview);
        posterImageView = (ImageView) findViewById(R.id.poster_image);


        // Set the Text
        titleTextView.setText(title);
        ratingTextView.setText(rating);
        popularityTextView.setText(popularity);
        originalLanguageTextView.setText(originalLanguage);
        releaseDateTextView.setText(releaseDate);
        overviewTextView.setText(overview);

        // Set poster image
        posterPath = hashMap.get(TAG_POSTER_PATH);
        String imageUrlString = NetworkUtils.buildImageUrlString(posterPath);
        Picasso.with(this).load(imageUrlString).into(posterImageView);

    }





}
