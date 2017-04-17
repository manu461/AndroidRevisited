package com.example.stalker.fragmenttoactivitycommunicationdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener , NewFragment.Listner {

    private Button back;
    private TextView dataOfActivity;
    private LinearLayout pane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);
        back = (Button)findViewById(R.id.id_button_back_activityTwo);
        back.setOnClickListener(this);
        dataOfActivity = (TextView)findViewById(R.id.id_textView_data_activityTwo);
        pane = (LinearLayout)findViewById(R.id.container461);
        pane.setOnClickListener(this);
    }
    public static Intent getNewIntent(Context c){
        Intent i = new Intent(c,ActivityTwo.class);
        return i;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.id_button_back_activityTwo){
            finish();
        }
        if(view.getId() == R.id.container461){
            FragmentManager manager = getSupportFragmentManager();
            Fragment fragment = new NewFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container461,fragment,null);
            transaction.commit();
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
            dataOfActivity.setText(savedInstanceState.getString("data"));
        }
    }



    @Override
    public void dataTransfer(String s) {
        dataOfActivity.setText(s);
    }
}
