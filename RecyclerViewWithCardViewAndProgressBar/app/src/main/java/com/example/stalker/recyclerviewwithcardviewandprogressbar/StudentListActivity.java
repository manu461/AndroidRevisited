package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class StudentListActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return new StudentListFragment();
    }
}
