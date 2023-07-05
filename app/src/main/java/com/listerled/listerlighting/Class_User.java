package com.listerled.listerlighting;

import androidx.annotation.Keep;

@Keep

public class Class_User {
    public int id;
    public String datetime;
    public String name;
    public String phoneno;
    public String emailid;
    public String token;
    public String password;
    public String userid;
    private String imageURL;
    private String status;
    private String search;

    public Class_User(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public Class_User(String datetime, String name, String phoneno, String emailid, String token, String userid, String imageURL, String status, String search) {

        this.datetime = datetime;
        this.name = name;
        this.phoneno = phoneno;
        this.emailid = emailid;
        this.token = token;
        this.userid = userid;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
    }

    public Class_User(int id, String datetime, String name, String phoneno, String emailid, String token, String password, String userid) {
        this.id = id;
        this.datetime = datetime;
        this.name = name;
        this.phoneno = phoneno;
        this.emailid = emailid;
        this.token = token;
        this.password = password;
        this.userid = userid;
    }

    public Class_User(String datetime, String name, String phoneno, String emailid, String token, String password, String userid) {
        this.datetime = datetime;
        this.name = name;
        this.phoneno = phoneno;
        this.emailid = emailid;
        this.token = token;
        this.password = password;
        this.userid = userid;
    }

    public Class_User() {
    }

    public Class_User(String datetime, String name, String phoneno, String emailid, String token, String password) {
        this.datetime = datetime;
        this.name = name;
        this.phoneno = phoneno;
        this.emailid = emailid;
        this.token = token;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}

