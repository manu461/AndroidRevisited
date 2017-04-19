package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by staLker on 14-04-2017.
 */

public class StudentListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mCurrentLat;
    private TextView mCurrentLong;
    private StudentListAdapter mStudentAdapter;
    private ArrayList<Student> mStudentList;
    private View v;
    protected Handler handler;
    private GPSTracker gps;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS = 201;
    private static final int OPEN_SETTING_FOR_PERMISSION = 100;
    private boolean sentToSettings = false;
    private SharedPreferences permissionStatus;
    private String[] requiredPermissions = new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.student_list_fragment,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mCurrentLat = (TextView) v.findViewById(R.id.current_location_Latitude__textView);
        mCurrentLong = (TextView) v.findViewById(R.id.current_location_Longitude_textView);
        mStudentList = new ArrayList<Student>();

        handler = new Handler();

        loadTheList();

        updateUI();



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        permissionStatus = getActivity().getSharedPreferences("permissionStatus",MODE_PRIVATE);
            getReadContactandGPSPermission();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_SETTING_FOR_PERMISSION) {
            if (ActivityCompat.checkSelfPermission(getActivity(), requiredPermissions[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                gotAllPermissions();

            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if(sentToSettings){
            if(ActivityCompat.checkSelfPermission(getActivity(),requiredPermissions[0]) == PackageManager.PERMISSION_GRANTED){
                gotAllPermissions();
            }
        }
    }

    private void getReadContactandGPSPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), requiredPermissions[0]) != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(getActivity(), requiredPermissions[1]) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), requiredPermissions[0])
                    ||ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),requiredPermissions[1])) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app needs GPS and Contact permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(getActivity(), requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
            else if (permissionStatus.getBoolean(requiredPermissions[0],false)) {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Need Multiple Permission");
                builder.setMessage("This app needs GPS and Contact permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings = true;
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent, OPEN_SETTING_FOR_PERMISSION);
                                           }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(), requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            SharedPreferences.Editor editor = permissionStatus.edit();
            editor.putBoolean(Manifest.permission.READ_CONTACTS,true);
            editor.commit();
        }
        else {

            gotAllPermissions();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS: {
                // If request is cancelled, the result arrays are empty.
                boolean allgranted = false;
                for(int i=0;i<grantResults.length;i++){
                    if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                        allgranted = true;
                    } else {
                        allgranted = false;
                        break;
                    }
                }

                if(allgranted) {
                    gotAllPermissions();

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), requiredPermissions[0])
                        ||ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),requiredPermissions[1])) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Need Multiple Permission");
                    builder.setMessage("This app needs GPS and Contact permission.");
                    builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            ActivityCompat.requestPermissions(getActivity(), requiredPermissions, MY_PERMISSIONS_REQUEST_READ_CONTACTS_AND_FINE_GPS);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }
                else{
                    Toast.makeText(getActivity().getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                }

            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void gotAllPermissions() {
        loadLocation();
        Log.e("One:","called load location");



    }


    private void updateUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mStudentAdapter = new StudentListAdapter(mStudentList,mRecyclerView);
            mRecyclerView.setAdapter(mStudentAdapter);
        mStudentAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mStudentList.add(null);
                mStudentAdapter.notifyItemInserted(mStudentList.size()-1);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mStudentList.remove(mStudentList.size()-1);
                        mStudentAdapter.notifyItemRemoved(mStudentList.size());
                        int start = mStudentList.size();
                        int end = start + 20;
                        for(int i=start+1;i<=end;i++){
                            mStudentList.add(new Student("Student"+i,i+91231432));
                            mStudentAdapter.notifyItemInserted(mStudentList.size());
                        }
                        mStudentAdapter.setLoaded();
                    }
                },2000);
            }
        });


    }
    private void loadTheList() {
        for(int i=1;i<=20;i++){
            mStudentList.add(new Student("Student"+i,i+91231432));
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
                ((StudentListViewHolder) holder).mContact.setText(Integer.toString(student.getContact()));
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

        public StudentListViewHolder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.list_row_name);
            mContact = (TextView) itemView.findViewById(R.id.list_row_contact);
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
