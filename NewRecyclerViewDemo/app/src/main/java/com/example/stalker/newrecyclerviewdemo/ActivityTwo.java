package com.example.stalker.newrecyclerviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ActivityTwo extends AppCompatActivity {

    private RecyclerView tableListView;
    private static int tableof;
    private static int upto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_two);
        tableListView = (RecyclerView)findViewById(R.id.id_RecyclerView);
        tableListView.setLayoutManager(new LinearLayoutManager(this));
        tableListView.setAdapter(new TableRecyclerViewClass());
        Table.tableDetails(tableof,upto);
    }
    public static Intent getNewIntent(Context c,int to,int uo){

        tableof = to;
        upto = uo;

        Intent i = new Intent(c,ActivityTwo.class);

        i.putExtra("tableof",to);
        i.putExtra("upto",uo);

        return i;
    }
}
