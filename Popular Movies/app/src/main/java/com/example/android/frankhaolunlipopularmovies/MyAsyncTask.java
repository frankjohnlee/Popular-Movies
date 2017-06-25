package com.example.android.frankhaolunlipopularmovies;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;
import com.example.android.frankhaolunlipopularmovies.utilities.JsonParser;
import com.example.android.frankhaolunlipopularmovies.utilities.JsonParser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class MyAsyncTask extends AsyncTask<URL, Void, String> {
    private OnTaskCompleted listener;



    private void makeSearchQuery() {
        /**
         * This method retrieves the search text from the EditText, constructs
         * the URL (using {@link NetworkUtils}) for the sort order you'd like to find, displays
         * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
         * our (not yet created) {@link GithubQueryTask}
         */
        URL searchUrl = NetworkUtils.buildPopularUrl();
        Log.d("searchUrl", searchUrl.toString());
        new QueryTask().execute(searchUrl);
    }

    @Override
    protected String doInBackground(URL... params) {
        URL searchUrl = params[0];
        String searchResults = null;
        try {
            searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
        } catch (IOException e) {
                e.printStackTrace();
        }

        return searchResults;
        }

    @Override
    protected String onPostExecute(String searchResults) {

        if (searchResults != null && !searchResults.equals("")) {
            return searchResults;
        }
        return null;
    }
}
