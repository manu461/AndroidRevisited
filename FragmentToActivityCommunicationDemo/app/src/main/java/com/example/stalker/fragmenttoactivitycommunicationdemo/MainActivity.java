package com.example.stalker.fragmenttoactivitycommunicationdemo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NewFragment.Listner {

    private LinearLayout pane;
    private TextView dataOfActivity;
    private Button nextPage;

    private String s = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pane = (LinearLayout)findViewById(R.id.container461);
        dataOfActivity = (TextView) findViewById(R.id.id_textView_data_activityOne);
        pane.setOnClickListener(this);
        nextPage = (Button)findViewById(R.id.id_button_nextPage_activityOne);
        nextPage.setOnClickListener(this);

        if(savedInstanceState != null){
            dataOfActivity.setText(savedInstanceState.getString("data"));
        }






    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.container461){
            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = new NewFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container461,fragment,null);
            transaction.commit();
        }
        if(view.getId() == R.id.id_button_nextPage_activityOne){
            Intent i = ActivityTwo.getNewIntent(MainActivity.this);
            startActivity(i);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data",dataOfActivity.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){
            s = savedInstanceState.getString("data");
            dataOfActivity.setText(s);
        }
    }



    @Override
    public void dataTransfer(String s) {
        dataOfActivity.setText(s);
    }
}
