    package com.example.android.frankhaolunlipopularmovies;

    import android.os.AsyncTask;
    import android.util.Log;

    import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;

    import java.io.IOException;
    import java.net.URL;


    public class MyAsyncTask extends AsyncTask<URL, Void, String> {
        private OnTaskCompleted listener;

        public MyAsyncTask (OnTaskCompleted listener){
            this.listener = listener;
        }

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
        protected void onPostExecute(String searchResults) {
            listener.onTaskCompleted(searchResults);
        }



    }
