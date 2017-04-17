package com.example.stalker.contactdetailrecyclerviewdemoreprise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by staLker on 14-07-2016.
 */
public class Contacts {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private static List<Contacts> contactlist = new ArrayList<Contacts>();


    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Contacts getNext(int index){
        return contactlist.get(index+1);
    }

    public Contacts getPrevious(int index){
        return contactlist.get(index-1);
    }

    public Contacts(String address, String firstName, String email, String lastName, String phoneNumber) {
        this.address = address;
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }


    public static void addContacts(String address, String firstName, String email, String lastName, String phoneNumber) {
        contactlist.add(new Contacts(address, firstName, email, lastName, phoneNumber));
    }

    public static List<Contacts> getAllContacts() {
        return contactlist;
    }

}
