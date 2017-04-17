package com.example.stalker.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class MainActivity extends AppCompatActivity {

    public RecyclerView songListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        songListView = (RecyclerView)findViewById(R.id.song_list_recycler_view);
        songListView.setLayoutManager(new LinearLayoutManager(this));
        songListView.setAdapter(new SongRecyclerViewClass());
    }


}
