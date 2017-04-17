package com.example.stalker.newrecyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by staLker on 22-06-2016.
 */
public class TableListViewHolder extends RecyclerView.ViewHolder {

    private TextView tableTextView;
    public TableListViewHolder(View itemView) {
        super(itemView);
        tableTextView = (TextView)itemView.findViewById(R.id.id_textView_TableRow);
    }


    public void bindTable(Table table) {
        tableTextView.setText(table.getTable());
    }
}
