package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class AllContactsDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recyclerView = (RecyclerView)findViewById(R.id.id_contactList_recyclerView);
        emptyRecyclerView = (TextView)findViewById(R.id.id_emptyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactRecyclerViewAdapter());

        if(Contacts.getAllContacts().isEmpty()){
            recyclerView.setVisibility(View.GONE);
            emptyRecyclerView.setVisibility(View.VISIBLE);
        }
        else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyRecyclerView.setVisibility(View.GONE);

        }
    }

    public static Intent getNewIntent(Context c){
        Intent i = new Intent(c,AllContactsDetailActivity.class);
        return i;
    }

}
