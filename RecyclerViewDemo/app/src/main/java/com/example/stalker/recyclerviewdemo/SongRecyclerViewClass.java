package com.example.stalker.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by staLker on 20-06-2016.
 */
public class SongRecyclerViewClass extends RecyclerView.Adapter<SongListViewHolder> {

    @Override
    public SongListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.songs_row,null);

        return new SongListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongListViewHolder holder, int position) {
        Song song = Song.allsong().get(position);
        holder.bindSong(song);

    }

    @Override
    public int getItemCount() {
        return Song.allsong().size();
    }
}
