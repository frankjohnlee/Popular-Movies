    package com.example.android.frankhaolunlipopularmovies;

    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.AsyncTask;
    import android.widget.ImageView;

    import java.io.InputStream;
    import java.net.URL;


    public class MyAsyncTaskImage extends AsyncTask<ImageView, Void, Bitmap> {
        ImageView theImageView = null;
        String theDownlaodUrl = "";

        public MyAsyncTaskImage(Context context, ImageView imageView, String downloadUrl){
            theImageView = imageView;
            theDownlaodUrl = downloadUrl;
        }

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            return download_Image(theDownlaodUrl);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            theImageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Bitmap d = BitmapFactory.decodeStream(is);
                is.close();
                return d;
            } catch (Exception e) {
                return null;
            }
        }
    }