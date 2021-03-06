package com.example.android.frankhaolunlipopularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.frankhaolunlipopularmovies.utilities.JsonParser;
import com.example.android.frankhaolunlipopularmovies.utilities.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.android.frankhaolunlipopularmovies.R.id.imageView;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.NumberViewHolder> {
    private static final String TAG = MovieAdapter.class.getSimpleName();

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

    private Context myContext;
    final private ListItemClickListener mOnClickListener;
    private static int viewHolderCount;
    private int mNumberItems;
    private ArrayList<HashMap<String, String>> movieList;
    private String mySearchType;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
    public MovieAdapter(ArrayList<HashMap<String, String>> movielist,
                        ListItemClickListener listener,
                        Context context, String searchType) {
        /**
         * Constructor for MovieAdapter that accepts a number of items to display and the specification
         * for the ListItemClickListener.
         *
         * @param movielist [Movie1, Movie2,...] where each object is a Hashmap containing movie information
         * @param listener Listener for list item clicks
         * @param context is the context
         */
        movieList = movielist;
        if (movielist != null){ mNumberItems = movieList.size();}
        else {mNumberItems = 0;};
        mOnClickListener = listener;
        myContext = context;
        viewHolderCount = 0;
        mySearchType = searchType;
    }


    @Override public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        /**
         *
         * This gets called when each new ViewHolder is created. This happens when the RecyclerView
         * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
         *
         * @param viewGroup The ViewGroup that these ViewHolders are contained within.
         * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
         *                  can use this viewType integer to provide a different layout. See
         *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
         *                  for more details.
         * @return A new NumberViewHolder that holds the View for each list item
         */
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recyclerview_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        String movieTitle = movieList.get(viewHolderCount).get(TAG_TITLE);
//        viewHolder.viewHolderIndex.setText(movieTitle);
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }
    public void onTaskCompleted(String myString){
        JsonParser jsonParser = new JsonParser();
        movieList = jsonParser.ParseJson(myString);
    };
    @Override public void onBindViewHolder(NumberViewHolder holder, int position) {
        /**
         * OnBindViewHolder is called by the RecyclerView to display the data at the specified
         * position. In this method, we update the contents of the ViewHolder to display the correct
         * indices in the list for this particular position, using the "position" argument that is conveniently
         * passed into us.
         *
         * @param holder   The ViewHolder which should be updated to represent the contents of the
         *                 item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        Log.d(TAG, "#" + position);
        holder.bind(myContext, position);

    }
    @Override public int getItemCount() {
        /**
         * This method simply returns the number of items to display. It is used behind the scenes
         * to help layout our Views and for animations.
         *
         * @return The number of items available
         */
        return mNumberItems;
    }



    class NumberViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener { // Cache of the children views for a list item.
        TextView listItemNumberView; // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView viewHolderIndex; // Will display which ViewHolder is displaying this data
        ImageView myImageView; // Will display the image
        public NumberViewHolder(View itemView) {
            /**
             * Constructor for our ViewHolder. Within this constructor, we get a reference to our
             * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
             * onClick method below.
             * @param itemView The View that you inflated in
             *                 {@link MovieAdapter#onCreateViewHolder(ViewGroup, int)}
             */
            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.recyclerViewId);
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);
            myImageView = (ImageView) itemView.findViewById(imageView);
            // COMPLETED (7) Call setOnClickListener on the View passed into the constructor (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }
        void bind(Context myContext, int listIndex) {
            /**
             * A method we wrote for convenience. This method will take an integer as input and
             * use that integer to display the appropriate text within a list item.
             * @param listIndex Position of the item in the list
             */
            String partialImageUrl = movieList.get(listIndex).get(TAG_POSTER_PATH);
            String imageUrlString = NetworkUtils.buildImageUrlString(partialImageUrl);
            Picasso.with(myContext).load(imageUrlString).placeholder(R.drawable.loading).into(myImageView);

        }
        @Override public void onClick(View v) {
            /**
             * Called whenever a user clicks on an item in the list.
             * @param v The View that was clicked
             */
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
