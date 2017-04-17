package com.example.stalker.newrecyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by staLker on 21-06-2016.
 */
public class TableRecyclerViewClass extends RecyclerView.Adapter<TableListViewHolder> {
    @Override
    public TableListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        View view = inflater.inflate(R.layout.table_row,null);

        return new TableListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(TableListViewHolder holder, int position) {
        Table table = Table.wholeTable().get(position);
        holder.bindTable(table);

    }

    @Override
    public int getItemCount() {
        return Table.wholeTable().size();
    }
}
