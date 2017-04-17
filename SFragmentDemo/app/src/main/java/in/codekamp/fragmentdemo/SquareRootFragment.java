package in.codekamp.fragmentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.widget.Button.*;


/**
 * Created by cerebro on 23/06/16.
 */
public class SquareRootFragment extends Fragment implements OnClickListener   {

    private TextView mResultTextView;
    private EditText mNumberEditText;
    private Button mCalculateButton;
    private Button mLaunchActivityTwo;
    private Button mCloseButton;

    public SquareRootFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_squre_root, container, false);

        mResultTextView = (TextView) view.findViewById(R.id.square_root_result);
        mNumberEditText = (EditText) view.findViewById(R.id.value_edit_text);
        mCalculateButton = (Button)view.findViewById(R.id.calculate_button);
        mLaunchActivityTwo = (Button)view.findViewById(R.id.launch_activity_two);
        mCloseButton = (Button)view.findViewById(R.id.close_btn);

        mCloseButton.setOnClickListener(this);
        mLaunchActivityTwo.setOnClickListener(this);
        mCalculateButton.setOnClickListener(this);

//        mCloseButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = getFragmentManager().findFragmentByTag(MainActivity.KEY);
//                getFragmentManager().beginTransaction().remove(fragment).commit();
//            }
//        });
//
//
//        mLaunchActivityTwo.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(),ActivityTwo.class);
//                startActivity(i);
//            }
//        });
//
//
//        mCalculateButton.setOnClickListener(new Button.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int number = Integer.parseInt(mNumberEditText.getText().toString());
//
//                double result = Math.sqrt(number);
//
//                mResultTextView.setText(Double.toString(result));
//
//            }
//        });

        return view;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.close_btn ){
            Fragment fragment = getFragmentManager().findFragmentByTag(MainActivity.KEY);
            getFragmentManager().beginTransaction().remove(fragment).commit();
        }
        else if (v.getId() == R.id.launch_activity_two){

            Intent i = new Intent(getActivity(),ActivityTwo.class);
               startActivity(i);

        }
        else if(v.getId() == R.id.calculate_button){
            int number = Integer.parseInt(mNumberEditText.getText().toString());

                double result = Math.sqrt(number);

                mResultTextView.setText(Double.toString(result));

        }
    }
}
