package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;
import java.util.UUID;

/**
 * Created by staLker on 14-04-2017.
 */

public class Student {

    private String name;
    private String claas;
    private int roll;
    private String address;
    private int phone;
    private UUID studentID;
    private String dateOfAdmission;
    private int lat;
    private int longi;


    public void setName(String name) {
        this.name = name;
    }

    public void setClaas(String claas) {
        this.claas = claas;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setDateOfAdmission(String dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
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

    public String getClaas() {
        return claas;
    }

    public int getRoll() {
        return roll;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    public int getLat() {
        return lat;
    }

    public int getLongi() {
        return longi;
    }

    public Student(String name, String claas, int roll, String address, int phone, String dateOfAdmission, int lat, int longi) {
        this.studentID = UUID.randomUUID();

        this.name = name;
        this.claas = claas;
        this.roll = roll;
        this.address = address;
        this.phone = phone;
        this.dateOfAdmission = dateOfAdmission;
        this.lat = lat;
        this.longi = longi;
    }
}
