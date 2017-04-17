package com.example.stalker.recyclerviewwithcardviewandprogressbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by staLker on 14-04-2017.
 */

public class StudentListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mCurrentLocation;
    private StudentListAdapter mStudentAdapter;
    private List<Student> mStudentList;
    protected Handler handler;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.student_list_fragment,container,false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mCurrentLocation = (TextView) v.findViewById(R.id.current_location_textView);

        mStudentList = new ArrayList<Student>();
        handler = new Handler();
        loadTheList();

        mRecyclerView.setHasFixedSize(true);

        updateUI();

        return v;
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
