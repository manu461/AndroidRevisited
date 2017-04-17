package com.example.stalker.my_calculator;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private String string;

    private TextView screen;

    private final String answerPanelKey = "KEY_ANSWER_PANEL" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        screen = (TextView) findViewById(R.id.editText);





        String resultText = null;
        if(savedInstanceState != null){
            resultText = savedInstanceState.getString(answerPanelKey);
        }
        if(resultText != null){
            screen.setText(resultText);
        }





    }
    public void zeroPressed(View view){
        screen.append("0");

    }
    public void onePressed(View view){
        screen.append("1");

    }
    public void twoPressed(View view){
        screen.append("2");

    }
    public void threePressed(View view){
        screen.append("3");

    }
    public void fourPressed(View view){
        screen.append("4");

    }
    public void fivePressed(View view){
        screen.append("5");

    }
    public void sixPressed(View view){
        screen.append("6");

    }
    public void sevenPressed(View view){
        screen.append("7");

    }
    public void eightPressed(View view){
        screen.append("8");

    }
    public void ninePressed(View view){
        screen.append("9");

    }
    public void clearPressed(View view){
        screen.setText("");

    }
    public void sumPressed(View view){
        screen.append("+");

    }public void subPressed(View view){
        screen.append("-");

    }public void divPressed(View view){
        screen.append("/");

    }public void multiPressed(View view){
        screen.append("X");

    }public void equalPressed(View view){
        screen.append("=");

    }
    public void correctPressed(View view){
        string = screen.getText().toString();
        if(string.length() == 0)
        {
        }

        else
        string = string.substring(0,string.length()-1);
        screen.setText(string.toString());

    }
    public void dotPressed(View view){
        screen.append(".");
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        zoutState.putString(answerPanelKey,screen.getText().toString());
    }
}
