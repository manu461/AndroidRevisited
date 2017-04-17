package com.example.stalker.fragmentonactivitytwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText data1;
    private EditText data2;
    private Button launchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data1 = (EditText)findViewById(R.id.id_editText_Data1_ActivityOne);
        data2 = (EditText)findViewById(R.id.id_editText_Data2_ActivityOne);
        launchButton = (Button)findViewById(R.id.id_button_launch_ActivityOne);
        launchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String p = data1.getText().toString();
        String q = data2.getText().toString();
        Intent i = ActivityTwo.getNewIntent(this,p,q);
        startActivity(i);

    }
}
