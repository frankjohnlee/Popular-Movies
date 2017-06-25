package com.example.android.frankhaolunlipopularmovies.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Frank2 on 6/16/2017.
 */

public class JsonParser {
    boolean debugMode = true;

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


    public ArrayList<HashMap<String, String>> ParseJson(String inputString) {

        if (inputString != null && inputString != "") {
            try {
                // Hashmap for ListView
                ArrayList<HashMap<String, String>> movieList =
                        new ArrayList<HashMap<String, String>>();

                JSONObject jsonObj = new JSONObject(inputString);

                // Get JSON Array node
                JSONArray movies = jsonObj.getJSONArray(TAG_MOVIE_INFO);

                // Loop through every student
                for (int i = 0; i < movies.length(); i++) {
                    JSONObject c = movies.getJSONObject(i);

                    // Get the values for this specific thing
                    String voteCount = c.getString(TAG_VOTE_COUNT);
                    String movieId = c.getString(TAG_MOVIE_ID);
                    String video = c.getString(TAG_VIDEO);
                    String title = c.getString(TAG_TITLE);
                    String vote_average = c.getString(TAG_VOTE_AVERAGE);
                    String popularity = c.getString(TAG_POPULARITY);
                    String posterPath = c.getString(TAG_POSTER_PATH);
                    String originalLanguage = c.getString(TAG_ORIGINAL_LANGUAGE);
                    String originalTitle = c.getString(TAG_ORIGINAL_TITLE);
                    String genreIds = c.getString(TAG_GENRE_IDS);
                    String backdropPath = c.getString(TAG_BACKDROP_PATH);
                    String adult = c.getString(TAG_ADULT);
                    String overview = c.getString(TAG_OVERVIEW);
                    String releaseDate = c.getString(TAG_RELEASE_DATE);

                    // Temp hashmap for a single movie
                    HashMap<String, String> movie = new HashMap<String, String>();

                    // Adding every child node to Hashmap, key -> value
                    movie.put(TAG_VOTE_COUNT, voteCount);
                    movie.put(TAG_MOVIE_ID, movieId);
                    movie.put(TAG_VIDEO, video);
                    movie.put(TAG_TITLE, title);
                    movie.put(TAG_VOTE_AVERAGE, vote_average);
                    movie.put(TAG_POPULARITY, popularity);
                    movie.put(TAG_POSTER_PATH, posterPath);
                    movie.put(TAG_ORIGINAL_LANGUAGE, originalLanguage);
                    movie.put(TAG_ORIGINAL_TITLE, originalTitle);
                    movie.put(TAG_GENRE_IDS, genreIds);
                    movie.put(TAG_BACKDROP_PATH, backdropPath);
                    movie.put(TAG_ADULT, adult);
                    movie.put(TAG_OVERVIEW, overview);
                    movie.put(TAG_RELEASE_DATE, releaseDate);

                    // Adding object to movieList
                    movieList.add(movie);
                }
                return movieList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        else {
            return null;
        }
    }
};


