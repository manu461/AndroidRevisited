package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import java.util.UUID;

/**
 * Created by staLker on 14-04-2017.
 */

public class Student {
    private UUID id;
    private String name;
    private int contact;
    private int lat;
    private int longi;

    public Student(){
        this.id = UUID.randomUUID();
    }
    public Student(String name,int contact){
        this();
        this.name = name;
        this.contact = contact;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLongi(int longi) {
        this.longi = longi;
    }

    public String getName() {

        return name;
    }

    public int getContact() {
        return contact;
    }

    public int getLat() {
        return lat;
    }

    public int getLongi() {
        return longi;
    }
}
