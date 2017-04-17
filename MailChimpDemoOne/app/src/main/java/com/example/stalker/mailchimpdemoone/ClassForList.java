package com.example.stalker.mailchimpdemoone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by staLker on 07-07-2016.
 */
public class ClassForList {


    @SerializedName("data")
    @Expose

    private List<ListOfLists> listOfLists = new ArrayList<ListOfLists>();

    public List<ListOfLists> getListOfLists() {
        return listOfLists;
    }


}
