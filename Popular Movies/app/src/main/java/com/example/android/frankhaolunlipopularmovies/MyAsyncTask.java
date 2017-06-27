    package com.example.android.frankhaolunlipopularmovies;

    import android.os.AsyncTask;

import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;


    public class MyAsyncTask extends AsyncTask<URL, Void, String> {
        private OnTaskCompleted listener;

        public MyAsyncTask (OnTaskCompleted listener){
            this.listener = listener;
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = NetworkUtils.buildPopularUrl();
            searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                    e.printStackTrace();
            }

            return searchResults;
            }

        @Override
        protected void onPostExecute(String searchResults) {
            listener.onTaskCompleted(searchResults);
        }



    }
