package com.example.stalker.aaonestartactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView1;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private Button mFetchButton;
    private static final int REQUEST_CODE_FETCH = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_FETCH){
            if(data == null){
                return;
            }
            String d[] = FormActivity.getFetchedData(data);
            mTextView1.setText(d[0]);
            mTextView2.setText(d[1]);
            mTextView3.setText(d[2]);
            mTextView4.setText(d[3]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView1 = (TextView)findViewById(R.id.textView1);
        mTextView2 = (TextView)findViewById(R.id.textView2);
        mTextView3 = (TextView)findViewById(R.id.textView3);
        mTextView4 = (TextView)findViewById(R.id.textView4);
        mFetchButton = (Button)findViewById(R.id.fetch_button);
        mFetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = FormActivity.newIntent(MainActivity.this);
                startActivityForResult(i,REQUEST_CODE_FETCH);
            }
        });

    }
}
