package com.example.android.frankhaolunlipopularmovies;

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
import com.example.android.frankhaolunlipopularmovies.utilities.PrintHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements MovieAdapter.ListItemClickListener, OnTaskCompleted{


    private static final int NUM_LIST_ITEMS = 100;
    MyRecyclerViewAdapter adapter;

    boolean debugMode = true;
    String queryString = "";
    ArrayList<HashMap<String, String>> movieList;
    JsonParser jsonParser = new JsonParser();
    PrintHelper printHelper = new PrintHelper();

    private MovieAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Toast mToast;
    OnTaskCompleted myInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
        URL searchUrl = NetworkUtils.buildPopularUrl();
        myAsyncTask.execute(searchUrl);
        myInterface.onTaskCompleted();

        mRecyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MovieAdapter(NUM_LIST_ITEMS, this);
        mRecyclerView.setAdapter(mAdapter);
    }

        @Override
        public void onTaskCompleted(String myString){
            Log.d("do I ever reach this", "test");
            JsonParser jsonParser = new JsonParser();
            movieList = jsonParser.ParseJson(myString);
        };


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

    @Override
    public void onListItemClick(int clickedItemIndex) {

        if (mToast != null) {
            mToast.cancel();
        }

        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }


}
