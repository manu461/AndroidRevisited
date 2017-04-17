package com.example.stalker.activitytimecycledemo;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("ssss","onCreate");
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ssss","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ssss","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ssss","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ssss","onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ssss","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ssss","onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("ssss","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("ssss","onRestoreInstanceState");
    }

    Intent i = new Intent();
}
