package com.example.stalker.aaonestartactivityforresult;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mLastName;
    private EditText mContact;
    private EditText mAddress;
    private Button mSubmit;
    private static final String EXTRA_FORM_ACTIVITY = "com.example.stalker.aaonestartactivityforresult.FormActivity";


    public static Intent newIntent(Context context){
        Intent i = new Intent(context,FormActivity.class);
        return i;
    }
    public static String[] getFetchedData(Intent result){
        return result.getStringArrayExtra(EXTRA_FORM_ACTIVITY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        mFirstName = (EditText)findViewById(R.id.first_name_editText);
        mLastName = (EditText)findViewById(R.id.last_name_editText);
        mContact = (EditText)findViewById(R.id.contact_editText);
        mAddress = (EditText)findViewById(R.id.address_editText);
        mSubmit = (Button)findViewById(R.id.submit_button);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d[] = new String[4];
                d[0] = mFirstName.getText().toString();
                d[1] = mLastName.getText().toString();
                d[2] = mContact.getText().toString();
                d[3] = mAddress.getText().toString();
                Intent data = new Intent();
                data.putExtra(EXTRA_FORM_ACTIVITY,d);
                setResult(RESULT_OK,data);
            }
        });

    }
}
