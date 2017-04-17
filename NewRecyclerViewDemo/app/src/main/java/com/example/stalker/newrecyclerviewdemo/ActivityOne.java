package com.example.stalker.newrecyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityOne extends AppCompatActivity {

    private EditText tableOf;
    private EditText upto;
    private Button getTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableOf = (EditText)findViewById(R.id.id_editText_TableOf_ActivityOne);
        upto = (EditText)findViewById(R.id.id_editText_Upto_ActivityOne);
        getTable = (Button)findViewById(R.id.id_button_getTable_ActivityOne);


        getTable.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = tableOf.getText().toString();
                int m = Integer.parseInt(p);

                String q = upto.getText().toString();
                int n = Integer.parseInt(q);


                Intent i = ActivityTwo.getNewIntent(ActivityOne.this,m,n);
                startActivity(i);

            }
        });



    }
}
