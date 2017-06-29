package com.example.android.frankhaolunlipopularmovies.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Frank2 on 6/12/2017.
 */

public class NetworkUtils {

    final static String MOVIEDB_BASE_URL =
            "http://api.themoviedb.org/3/movie/";

    final static String MOVIEDB_API_KEY =
            "?api_key=" + "857dab6372c0b45e15f3827ce51d1a7a";


    final static String MOVIEDB_MOST_POPULAR_TAG =
            "popular";

    final static String MOVIEDB_TOP_RATED_TAG =
            "top_rated";

    final static String PARAM_SORT = "sort";
    final static String PARAM_API_KEY = "api_key";

    final static String MOVIEDB_BASE_IMAGE = "https://image.tmdb.org/t/p";
    final static String MOVIEDB_w500_SIZE = "/w500";

    /**
     * @return The URL for the query
     */

    public static URL buildPopularUrl() {
        String stringUri = MOVIEDB_BASE_URL + MOVIEDB_MOST_POPULAR_TAG + MOVIEDB_API_KEY;
        URL url = null;
        try {
            url = new URL(stringUri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static URL buildTopRatedUrl() {
        String stringUri = MOVIEDB_BASE_URL + MOVIEDB_TOP_RATED_TAG + MOVIEDB_API_KEY;
        URL url = null;
        try {
            url = new URL(stringUri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildImageUrl(String myString){
        String stringUri = MOVIEDB_BASE_IMAGE + MOVIEDB_w500_SIZE + myString;
        URL url = null;
        try {
            url = new URL(stringUri);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}
