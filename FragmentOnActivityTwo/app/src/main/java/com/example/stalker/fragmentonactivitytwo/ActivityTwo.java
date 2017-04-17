package com.example.stalker.fragmentonactivitytwo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    private String dataOne;
    private String dataTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);
        Intent i = getIntent();
        dataOne = i.getExtras().getString("DATA_ONE");
        dataTwo = i.getExtras().getString("DATA_TWO");
        Log.d("ssss",dataOne);
        Log.d("ssss",dataTwo);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = NewFragment.newInstance(dataOne,dataTwo);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container461,fragment,"fragTag");
        transaction.commit();



    }
    public static Intent getNewIntent(Context c,String d1,String d2){
        Intent i = new Intent(c,ActivityTwo.class);
        i.putExtra("DATA_ONE",d1);
        i.putExtra("DATA_TWO",d2);
        return i;
    }
}
