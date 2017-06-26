package com.example.android.frankhaolunlipopularmovies.utilities;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Frank2 on 6/26/2017.
 */

public class PrintHelper {

    public void print(String name, String input) {
        if (debugMode) {
            Log.wtf(name, input);
        }
    }
    public void printStringArray(String name, String[] input) {
        if (debugMode) {
            Log.d(name, input[0]);
        }
    }
    public void printMovieObject(HashMap movie){
        // Declaring Java Nodes
        String TAG_MOVIE_INFO = "results";
        String TAG_VOTE_COUNT = "vote_count";
        String TAG_MOVIE_ID = "movie_id";
        String TAG_VIDEO = "video";
        String TAG_TITLE = "title";
        String TAG_POPULARITY = "popularity";
        String TAG_POSTER_PATH = "posterPath";
        String TAG_ORIGINAL_LANGUAGE = "original_language";
        String TAG_ORIGINAL_TITLE = "original_title";
        String TAG_GENRE_IDS = "genre_ids";
        String TAG_BACKDROP_PATH = "backdrop_path";
        String TAG_ADULT = "adult";
        String TAG_OVERVIEW = "overview";
        String TAG_RELEASE_DATE = "release_date";
        this.print("Method debugging: ", "printeMovieObject");
        // Get the values for this specific thing
        String voteCount = (String) movie.get(TAG_VOTE_COUNT);
        String movieId = (String) movie.get(TAG_MOVIE_ID);
        String video = (String) movie.get(TAG_VIDEO);
        String title = (String) movie.get(TAG_TITLE);
        String popularity = (String) movie.get(TAG_POPULARITY);
        String posterPath = (String) movie.get(TAG_POSTER_PATH);
        String originalLanguage = (String) movie.get(TAG_ORIGINAL_LANGUAGE);
        String originalTitle = (String) movie.get(TAG_ORIGINAL_TITLE);
        String genreIds = (String) movie.get(TAG_GENRE_IDS);
        String backdropPath = (String) movie.get(TAG_BACKDROP_PATH);
        String adult = (String) movie.get(TAG_ADULT);
        String overview = (String) movie.get(TAG_OVERVIEW);
        String releaseDate = (String) movie.get(TAG_RELEASE_DATE);

        this.print("title: ", title);
        this.print("voteCount: ", voteCount);
        this.print("popularity: ", popularity);
        this.print("movieId: ", movieId);
        this.print("video: ", video);
        this.print("title: ", title);
        this.print("posterPath: ", posterPath);
        this.print("originalLanguage: ", originalLanguage);
        this.print("originalTitle: ", originalTitle);
        this.print("genreIds: ", genreIds);
        this.print("backdropPath: ", backdropPath);
        this.print("adult: ", adult);
        this.print("overview: ", overview);
        this.print("releaseDate: ", releaseDate);
    }
    public void printMovieObjectArray(ArrayList<HashMap<String, String>> movies){
        this.print("Method debugging: ", "printMovieObjectArray");
        HashMap movie = movies.get(1);
        if (movie != null){
            this.printMovieObject(movie);
        }

    }
    public String getStringMovie(HashMap movie, String tag){
        // Get the values for this specific thing
        String rString = (String) movie.get(tag);
        return rString;
    }
    public String[] getStringArray(ArrayList<HashMap<String, String>> movie, String tag){
        ArrayList<String> movieArrayList = new ArrayList<String>();
        int counter = 0;
        for (HashMap<String, String> s: movie){
            String titleString = this.getStringMovie(s, tag);
            movieArrayList.add(titleString);
            counter ++;
        }
        String[] array = new String[movieArrayList.size()];

        for (int i = 0; i < movieArrayList.size(); i++) {
            array[i] = movieArrayList.get(i);
        }
        return array;

    }
}
