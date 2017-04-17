package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SingleContactDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView firstName;
    private TextView lastName;
    private TextView phoneNumber;
    private TextView address;
    private TextView eMail;
    private TextView previousButton;
    private TextView nextButton;
    private static Contacts currentContacts;
    private static int currentContactPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_contact_detail);
        firstName = (TextView)findViewById(R.id._id_firstname);

        lastName = (TextView)findViewById(R.id._id_lastname);

        phoneNumber = (TextView)findViewById(R.id._id_phoneNumber);

        address = (TextView)findViewById(R.id._id_address);

        eMail = (TextView)findViewById(R.id._id_email);

        previousButton = (TextView)findViewById(R.id.id_previous_button);
        previousButton.setOnClickListener(this);
        nextButton = (TextView)findViewById(R.id.id_next_button);
        nextButton.setOnClickListener(this);
        setData();
    }
    public static Intent getNewIntent(Context c ,Contacts contacts, int position){
        Intent i = new Intent(c,SingleContactDetailActivity.class);
        currentContacts = contacts;
        currentContactPosition = position;
        return i;
    }
    public void setData(){
        firstName.setText(currentContacts.getFirstName());
        lastName.setText(currentContacts.getLastName());
        phoneNumber.setText(currentContacts.getPhoneNumber());
        address.setText(currentContacts.getAddress());
        eMail.setText(currentContacts.getEmail());
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.id_next_button ){
            currentContacts = currentContacts.getNext(currentContactPosition);
            currentContactPosition++;
            setData();
        }
        if (v.getId() == R.id.id_previous_button) {
            currentContacts = currentContacts.getPrevious(currentContactPosition);
            currentContactPosition--;
            setData();
        }
    }
}
