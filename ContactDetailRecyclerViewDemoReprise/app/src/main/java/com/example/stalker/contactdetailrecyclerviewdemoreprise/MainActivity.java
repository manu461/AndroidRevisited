package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneNumberEditText;
    private EditText addressEditText;
    private EditText emailEditText;
    private Button nextPageButton;
    private Button addContactButton;

    public void nullifyAll(){
        firstNameEditText.setText(null);
        lastNameEditText.setText(null);
        phoneNumberEditText.setText(null);
        addressEditText.setText(null);
        emailEditText.setText(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNameEditText = (EditText) findViewById(R.id.id_editText_firstName);
        phoneNumberEditText = (EditText) findViewById(R.id.id_editText_phoneNumber);
        emailEditText = (EditText) findViewById(R.id.id_editText_email);
        addressEditText = (EditText) findViewById(R.id.id_editText_address);
        lastNameEditText = (EditText) findViewById(R.id.id_editText_lastName);
        nextPageButton = (Button) findViewById(R.id.id_button_nextPage);
        addContactButton = (Button) findViewById(R.id.id_button_addContact);
        nextPageButton.setOnClickListener(this);
        addContactButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.id_button_addContact) {
            if(firstNameEditText.getText().toString() != null || lastNameEditText.getText().toString() != null){
                Contacts.addContacts(
                        addressEditText.getText().toString(),
                        firstNameEditText.getText().toString(),
                        emailEditText.getText().toString(),
                        lastNameEditText.getText().toString(),
                        phoneNumberEditText.getText().toString()
                );

                Toast.makeText(MainActivity.this, "Contact saved successfully", Toast.LENGTH_LONG).show();

                this.nullifyAll();

            }

        }

        if (view.getId() == R.id.id_button_nextPage) {
            Intent i = AllContactsDetailActivity.getNewIntent(MainActivity.this);
            startActivity(i);
        }
    }
}
