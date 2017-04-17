package com.example.stalker.mailchimpdemoone;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<ClassForList> {

    private TextView textViewDataOne;
    private TextView textViewDataTwo;
    private TextView textViewDataThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewDataOne = (TextView)findViewById(R.id.id_textView_DataOne_ActivityOne);
        textViewDataTwo = (TextView)findViewById(R.id.id_textView_DataTwo_ActivityOne);
        textViewDataThree = (TextView)findViewById(R.id.id_textView_DataThree_ActivityOne);



    }
    public void getListButtonClicked(View v){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us13.api.mailchimp.com/2.0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MailChimpService mailChimpService = retrofit.create(MailChimpService.class);

        Call<ClassForList> call = mailChimpService.getListMethod("54636415e653b9e249d558242fb9c1e9-us13");
        call.enqueue(this);



    }

    @Override
    public void onResponse(Call<ClassForList> call, Response<ClassForList> response) {
        textViewDataOne.setText(response.body().getListOfLists().get(0).getName());
        textViewDataTwo.setText(response.body().getListOfLists().get(1).getName());
        textViewDataThree.setText(response.body().getListOfLists().get(2).getName());

    }

    @Override
    public void onFailure(Call<ClassForList> call, Throwable t) {

    }
}
