package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by staLker on 14-04-2017.
 */

public class Student implements Parcelable {
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

    protected Student(Parcel in) {
        name = in.readString();
        contact = in.readInt();
        lat = in.readInt();
        longi = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(contact);
        dest.writeInt(lat);
        dest.writeInt(longi);
    }
}
