package com.example.stalker.recyclerviewdemo;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by staLker on 17-06-2016.
 */
public class Song {
    private String title;
    private String artist;
    public Song(String t, String a) {
        this.title = t;
        this.artist = a;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public static List<Song> allsong() {
        List<Song> songs = new ArrayList<>();

        songs.add(new Song("Numb", "Linkin Park"));
        songs.add(new Song("LeanOn", "DJ snake"));
        songs.add(new Song("RainOverMe", "Pitbull"));
        for (int i = 0; i < 30; i++) {
            songs.add(new Song("Numb" + i, "Linkin Park" + i));
            songs.add(new Song("LeanOn" + i, "DJ snake" + i));
            songs.add(new Song("RainOverMe" + i, "Pitbull" + i));
        }
        return songs;
    }
}
