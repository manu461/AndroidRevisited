package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by staLker on 19-04-2017.
 */

public class StudentDetailPagerActivity extends FragmentActivity{

    private static final String EXTRA_STUDENT_ID = "com.example.stalker.recyclerviewwithcardviewandprogressbar.studentID";
    private ViewPager mViewPager;
    private ArrayList<Student> mStudentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_fragment_view_pager);
        UUID mStudentId = (UUID) getIntent().getSerializableExtra(EXTRA_STUDENT_ID);
        mViewPager = (ViewPager) findViewById(R.id.student_view_pager);
        mStudentList = StudentListFragment.getStudentList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Student student = mStudentList.get(position);
                return StudentDetailFragment.newInstance(student.getStudentID());
            }

            @Override
            public int getCount() {
                return mStudentList.size();
            }
        });
        for(int i=0;i<mStudentList.size();i++){
            if(mStudentList.get(i).getStudentID().equals(mStudentId)){
                mViewPager.setCurrentItem(i);
            break;
            }
        }

    }


    public static Intent newIntent(Context context,UUID mStudentID){
      Intent i = new Intent(context,StudentDetailPagerActivity.class);
        i.putExtra(EXTRA_STUDENT_ID,mStudentID);
        return i;
    }
}

