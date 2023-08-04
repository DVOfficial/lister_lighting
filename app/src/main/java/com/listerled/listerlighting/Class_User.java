package com.listerled.listerlighting;

import androidx.annotation.Keep;

@Keep

public class Class_User {
    public int id;
    public String category;
    public String party_name;
    public String city;
    public String party_name_city;
    public String state;
    public String address;

    public Class_User(String party_name, String city) {
        this.party_name = party_name;
        this.city = city;
    }

    public Class_User() {
    }

    public Class_User(int id, String party_name, String city) {
        this.id = id;
        this.party_name = party_name;
        this.city = city;
    }

    public Class_User(int id, String category, String party_name, String city, String party_name_city, String state, String address) {

        this.id = id;
        this.category = category;
        this.party_name = party_name;
        this.city = city;
        this.party_name_city = party_name_city;
        this.state = state;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParty_name_city() {
        return party_name_city;
    }

    public void setParty_name_city(String party_name_city) {
        this.party_name_city = party_name_city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


