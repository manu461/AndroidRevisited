package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.UUID;

/**
 * Created by staLker on 19-04-2017.
 */

public class StudentDetailFragment extends Fragment {
    private static final String ARGS_KEY_STUDENT_ID = "student_id";
    private Student mStudent;
    private ImageView mStudentImageView;
    private TextView mStudentNameTextView;
    private TextView mStudentClaasTextView;
    private TextView mStudentRoll;
    private TextView mStudentAddressTextView;
    private TextView mPhoneTextView;

    private TextView mStudentAddmissionTextView;
    private TextView mLongitudeTextView;
    private TextView mLatitudeTextView;
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID mStudentID = (UUID) getArguments().getSerializable(ARGS_KEY_STUDENT_ID);
        mStudent = StudentListFragment.getThatStudent(mStudentID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.student_detail_fragment,container,false);
        mStudentImageView = (ImageView) view.findViewById(R.id.student_detail_fragment_imageView);
        mStudentNameTextView  = (TextView) view.findViewById(R.id.student_detail_fragment_textView_name);
        mStudentClaasTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_class);
        mStudentAddressTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_address);
        mPhoneTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_phone);
        mStudentRoll = (TextView) view.findViewById(R.id.student_detail_fragment_textView_roll);
        mStudentAddmissionTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_admission);
        mLongitudeTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_longitude);
        mLatitudeTextView = (TextView) view.findViewById(R.id.student_detail_fragment_textView_lattitude);




        mStudentNameTextView.setText(mStudent.getName());
        mStudentClaasTextView.setText(mStudent.getClaas());
        mStudentAddressTextView.setText(mStudent.getAddress());
        mPhoneTextView.setText(Integer.toString(mStudent.getPhone()));
        mStudentRoll.setText(Integer.toString(mStudent.getRoll()));
        mStudentAddmissionTextView.setText(mStudent.getDateOfAdmission());
        mLongitudeTextView.setText(Integer.toString(mStudent.getLongi()));
        mLatitudeTextView.setText(Integer.toString(mStudent.getLat()));






        return view;

    }

    public static StudentDetailFragment newInstance(UUID mStudentID) {
        
        Bundle args = new Bundle();
        args.putSerializable(ARGS_KEY_STUDENT_ID,mStudentID);
        
        StudentDetailFragment fragment = new StudentDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
