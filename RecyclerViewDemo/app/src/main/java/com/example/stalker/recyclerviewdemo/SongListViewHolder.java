package com.example.stalker.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by staLker on 20-06-2016.
 */
public class SongListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Song currentSong;

    private TextView songDescription;
    private TextView songTitle;
    public SongListViewHolder(View itemView) {
        super(itemView);

        songDescription = (TextView)itemView.findViewById(R.id.Song_Description_Text_View);
        songTitle = (TextView)itemView.findViewById(R.id.Song_Title_Text_View);

        songTitle.setOnClickListener(this);
        songDescription.setOnClickListener(this);
    }

    public void bindSong(Song song){
        this.currentSong = song;
        songDescription.setText(song.getArtist());
        songTitle.setText("fhgh");

    }
    @Override
    public void onClick(View v) {
        Intent i = DetailActivity.getNewIntent(itemView.getContext(),currentSong);
        itemView.getContext().startActivity(i);

    }
}
