package in.codekamp.fragmentdemo;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    public static final String KEY = "sqrt";
    private Button buttonOne;
    private Button buttonTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOne = (Button)findViewById(R.id.button1);
        buttonTwo = (Button)findViewById(R.id.button2);

        buttonOne.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();

                if(manager.findFragmentByTag(KEY) == null) {

                    Fragment fragment1 = new SquareRootFragment();

                    FragmentTransaction transaction = manager.beginTransaction();

                    transaction.add(R.id.container1, fragment1, KEY);

                    transaction.commit();
                }

                else if(manager.findFragmentByTag(KEY) != null){
                    Fragment fragment = getSupportFragmentManager().findFragmentByTag(KEY);
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                }

            }
        });
        buttonTwo.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();

                Fragment fragment2 = new SquareRootFragment();

                FragmentTransaction transaction = manager.beginTransaction();

                transaction.add(R.id.container2,fragment2, KEY);

                transaction.commit();
            }
        });








    }

}
