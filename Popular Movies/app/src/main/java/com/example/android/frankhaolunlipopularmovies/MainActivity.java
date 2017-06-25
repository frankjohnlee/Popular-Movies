package com.example.android.frankhaolunlipopularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.frankhaolunlipopularmovies.utilities.JsonParser;
import com.example.android.frankhaolunlipopularmovies.utilities.MyRecyclerViewAdapter;
import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements OnTaskCompleted {


    private static final int NUM_LIST_ITEMS = 100;
    MyRecyclerViewAdapter adapter;

    boolean debugMode = true;
    String queryString = "";
    ArrayList<HashMap<String, String>> movieList;
    JsonParser jsonParser = new JsonParser();

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


    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private MovieAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Toast mToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.makeSearchQuery();


        mRecyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
            mAdapter = new MovieAdapter(NUM_LIST_ITEMS, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            /*
             * When you click the reset menu item, we want to start all over
             * and display the pretty gradient again. There are a few similar
             * ways of doing this, with this one being the simplest of those
             * ways. (in our humble opinion)
             */
            case R.id.action_refresh:
                // COMPLETED (14) Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new MovieAdapter(NUM_LIST_ITEMS, this);
                mRecyclerView.setAdapter(mAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // COMPLETED (10) Override ListItemClickListener's onListItemClick method
    /**
     * This is where we receive our callback from
     * {@link com.example.android.recyclerview.GreenAdapter.ListItemClickListener}
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {
        // COMPLETED (11) In the beginning of the method, cancel the Toast if it isn't null
        /*
         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
         * ensures that our new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         *
         * Comment out these three lines, run the app, and click on a bunch of
         * different items if you're not sure what I'm talking about.
         */
        if (mToast != null) {
            mToast.cancel();
        }

        // COMPLETED (12) Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
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
    public class QueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String searchResults = null;
            try {
                searchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("searchResults", searchResults);
            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {
            if (searchResults != null && !searchResults.equals("")) {
                movieList = jsonParser.ParseJson(searchResults);
                String[] arrayMovieTitles = this.getStringArray(movieList, TAG_TITLE);


            }
        }

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

}
