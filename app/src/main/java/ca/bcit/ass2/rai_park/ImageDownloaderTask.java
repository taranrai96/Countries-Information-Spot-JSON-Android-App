package ca.bcit.ass2.rai_park;

import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParser;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloaderTask extends AsyncTask<String, Void, Drawable> {
    /*
    A weak reference, simply put, is a reference that isn't strong enough to force an object to remain in memory.
    Weak references allow you to leverage the garbage collector's ability to determine reachability for you,
    so you don't have to do it yourself.
     */
    private final WeakReference<ImageView> imageViewReference;

    public ImageDownloaderTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Drawable doInBackground(String... params) {
        return download(params[0]);
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        if (isCancelled()) {
            drawable = null;
        }
        if (imageViewReference != null) {
            Log.d("imageViewReference", "not null");
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (drawable!= null) {
                    imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                    imageView.setImageDrawable(drawable);

                } else {
                    Log.d("bitmap", "is null");
                    Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    imageView.setImageDrawable(placeholder);
                }
            }
        }
    }



    private Drawable download(String url) {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();
            int statusCode = urlConnection.getResponseCode();
            if (statusCode !=  HttpURLConnection.HTTP_OK) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                SVG svg = SVG.getFromInputStream(inputStream);
                Picture picture = svg.renderToPicture();
                Drawable drawable = new PictureDrawable(picture);

                return drawable;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}
