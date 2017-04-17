package com.example.stalker.mailchimpdemoone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by staLker on 07-07-2016.
 */
public class ListOfLists {


    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
