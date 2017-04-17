package com.example.stalker.fragmentonactivitytwo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class NewFragment extends Fragment{

    private TextView dataOne;
    private TextView dataTwo;
    private String dataOneString;
    private String dataTwoString;

    public NewFragment(){

    }
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new,container,false);
        dataOne = (TextView) view.findViewById(R.id.id_dataOne_textView_fragment);
        dataTwo = (TextView) view.findViewById(R.id.id_dataTwo_textView_fragment);
        dataOne.setText(dataOneString);
        dataTwo.setText(dataTwoString);
        return view;
    }
    public static NewFragment newInstance(String s1, String s2) {


        NewFragment fragment = new NewFragment();
        Bundle args = new Bundle();


        args.putString("dataOne",s1);
        args.putString("dataTwo",s2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = this.getArguments();
        if(b != null){
           dataOneString =  b.getString("dataOne");
           dataTwoString =  b.getString("dataTwo");
        }

    }
}

