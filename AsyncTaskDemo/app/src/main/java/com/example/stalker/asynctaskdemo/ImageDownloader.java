package com.example.stalker.asynctaskdemo;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.widget.Button;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by staLker on 28-06-2016.
 */
public class ImageDownloader extends AsyncTask<String, Void, Image> {



    public Drawable drawable;

    @Override
    protected Image doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream stream = connection.getInputStream();
            stream.read();
            return drawable = Drawable.createFromStream(connection.getInputStream(),"drawable name");
        } catch (Exception e){
            e.printStackTrace();

        }
    }

    @Override
    protected void onPostExecute(Image image) {
        super.onPostExecute(image);
    }


}
