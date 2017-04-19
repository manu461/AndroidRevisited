package com.example.stalker.recyclerviewwithcardviewandprogressbar;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by staLker on 14-04-2017.
 */

public class StudentListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mCurrentLat;
    private TextView mCurrentLong;
    private StudentListAdapter mStudentAdapter;
    private static ArrayList<Student> sStudentList;
    private View v;
    protected Handler handler;
    private GPSTracker gps;

    public static ArrayList<Student> getStudentList() {
        return sStudentList;
    }
    public static Student getThatStudent(UUID mStudentID){
        for(Student mStudent : sStudentList){
            if(mStudent.getStudentID().equals(mStudentID)){
                return mStudent;
            }
        }
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("1","onCreate");



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("1","onCreateView");

        v = inflater.inflate(R.layout.student_list_fragment,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mCurrentLat = (TextView) v.findViewById(R.id.current_location_Latitude__textView);
        mCurrentLong = (TextView) v.findViewById(R.id.current_location_Longitude_textView);
        sStudentList = new ArrayList<Student>();
        loadLocation();


        handler = new Handler();

        loadTheList();

        updateUI();



        return v;
    }









    private void updateUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mStudentAdapter = new StudentListAdapter(sStudentList,mRecyclerView);
            mRecyclerView.setAdapter(mStudentAdapter);
        mStudentAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                sStudentList.add(null);
                mStudentAdapter.notifyItemInserted(sStudentList.size()-1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sStudentList.remove(sStudentList.size()-1);
                        mStudentAdapter.notifyItemRemoved(sStudentList.size());
                        int start = sStudentList.size();
                        int end = start + 20;
                        for(int i=start+1;i<=end;i++){
                            sStudentList.add(new Student("FirstName"+(i)+"_"+"LastName"+(i)+"","btech._year"+((i%4)+1),1403210+i,"Address "+i,989913+i," "+(i%30)+"/"+((i%12)+1)+"/"+"2016"+" ", i,i));
                            mStudentAdapter.notifyItemInserted(sStudentList.size());
                        }
                        mStudentAdapter.setLoaded();
                    }
                },2000);
            }
        });


    }
    private void loadTheList() {
        for(int i=1;i<=20;i++){
            sStudentList.add(new Student("FirstName"+(i)+"_"+"LastName"+(i)+"","btech._year"+((i%4)+1),1403210+i,"Address "+i,989913+i," "+(i%30)+"/"+((i%12)+1)+"/"+"2016"+" ", i,i));
        }
    }

    private  void loadLocation(){
        gps = new GPSTracker(getActivity());
        Log.e("One:","inside load location");


        if(gps.canGetLocation()){
            Log.e("One:","inside if of load location");


            double longitude = gps.getLongitude();
            double latitude = gps .getLatitude();
            mCurrentLong.setText("Long: "+Double.toString(longitude));
            mCurrentLat.setText("Lat: "+Double.toString(latitude));

        }
        else
        {
            Log.e("One:","inside else of load location");

            gps.showSettingsAlert();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gps.stopUsingGPS();
    }

    public class StudentListAdapter extends RecyclerView.Adapter {
        private final int VIEW_ITEM = 1;
        private final int VIEW_PROG = 0;
        private List<Student> mStudentList;
        private int visibleThreshold = 5;
        private int lastVisibleItem, totalItemCount;
        private boolean loading;
        private OnLoadMoreListener onLoadMoreListener;

        public StudentListAdapter(List<Student> studentList, RecyclerView mRecyclerView) {
            mStudentList = studentList;
            if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {

                final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView
                        .getLayoutManager();
                mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        totalItemCount = linearLayoutManager.getItemCount();
                        lastVisibleItem = linearLayoutManager
                                .findLastVisibleItemPosition();
                        if (!loading
                                && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                            if (onLoadMoreListener != null) {
                                onLoadMoreListener.onLoadMore();
                            }
                            loading = true;
                        }
                    }

                });

            }
        }

        @Override
        public int getItemViewType(int position) {
            return mStudentList.get(position) != null ? VIEW_ITEM : VIEW_PROG;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder vh;
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            if(viewType == VIEW_ITEM){
                View v = layoutInflater.inflate(R.layout.list_row,parent,false);
                vh = new StudentListViewHolder(v);
            }
            else{
                View v = layoutInflater.inflate(R.layout.progress_bar,parent,false);
                vh = new ProgressBarViewHolder(v);

            }
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof StudentListViewHolder){
                Student student = mStudentList.get(position);
                ((StudentListViewHolder) holder).mName.setText(student.getName());
                ((StudentListViewHolder) holder).mContact.setText(Integer.toString(student.getPhone()));
                ((StudentListViewHolder) holder).mStudent = student;
            }else{
                ((ProgressBarViewHolder) holder).mProgressBar.setIndeterminate(true);

            }

        }

        public void setLoaded() {
            loading = false;

        }

        @Override
        public int getItemCount() {
            return mStudentList.size();
        }


        public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
            this.onLoadMoreListener = onLoadMoreListener;
        }
    }

    public class StudentListViewHolder extends RecyclerView.ViewHolder{
        private TextView mName;
        private TextView mContact;
        private Student mStudent;

        public StudentListViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.list_row_name);
            mContact = (TextView) itemView.findViewById(R.id.list_row_contact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = StudentDetailPagerActivity.newIntent(getActivity(),mStudent.getStudentID());
                    startActivity(i);

                }
            });
        }
    }

    public class ProgressBarViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mProgressBar;

        public ProgressBarViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.student_list_progress_bar);
        }
    }


}
