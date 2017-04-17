package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by staLker on 20-07-2016.
 */
public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewviewHolder> {
    @Override
    public ContactRecyclerViewviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_contact_row,null);
        return new ContactRecyclerViewviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactRecyclerViewviewHolder holder, int position) {
        Contacts contacts = Contacts.getAllContacts().get(position);
        holder.bindContact(contacts, position);
    }

    @Override
    public int getItemCount() {
        return Contacts.getAllContacts().size();
    }
}
