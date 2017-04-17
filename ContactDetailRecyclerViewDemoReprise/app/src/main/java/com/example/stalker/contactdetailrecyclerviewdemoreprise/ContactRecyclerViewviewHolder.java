package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by staLker on 20-07-2016.
 */
public class ContactRecyclerViewviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView firstName;
    private TextView lastName;
    private Contacts currentContact;
    private int currentContactPosition;
    public ContactRecyclerViewviewHolder(View itemView) {
        super(itemView);
        firstName = (TextView)itemView.findViewById(R.id.id_detailActivity_firstName);
        lastName = (TextView)itemView.findViewById(R.id.id_detailActivity_lastName);
        firstName.setOnClickListener(this);
        lastName.setOnClickListener(this);

    }

    public void bindContact(Contacts contacts, int position) {
        this.currentContact = contacts;
        this.currentContactPosition = position;

        firstName.setText(contacts.getFirstName());
        lastName.setText(contacts.getLastName());
    }

    @Override
    public void onClick(View view) {

        Intent i = SingleContactDetailActivity.getNewIntent(itemView.getContext(),currentContact,currentContactPosition);
        itemView.getContext().startActivity(i);

    }
}
