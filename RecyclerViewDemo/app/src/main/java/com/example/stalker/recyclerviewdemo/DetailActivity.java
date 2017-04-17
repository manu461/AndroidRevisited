package com.example.stalker.recyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static Song currentSong;
    private TextView titlePressed;
    private TextView artistPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        titlePressed = (TextView)findViewById(R.id.id_title_textView_DetailActivity);
        artistPressed = (TextView)findViewById(R.id.id_artist_textView_DetailActivity);
        titlePressed.setText(currentSong.getTitle());
        artistPressed.setText(currentSong.getArtist());
    }
    public static Intent getNewIntent(Context c, Song song){
        Intent i = new Intent(c,DetailActivity.class);

        currentSong = song;

        return i;

    }
}
