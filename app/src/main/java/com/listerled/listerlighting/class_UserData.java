package com.listerled.listerlighting;

import androidx.annotation.Keep;

@Keep
public class class_UserData {

    public String name;
    public String mobile;
    public String email;
    public String password;
    public String userId;
    public String dateTime;
    public String formname;
    public String formremarks;
    private boolean expanded;

    public class_UserData(String name, String mobile, String email, String password, String userId, String dateTime, String formname, String formremarks) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.dateTime = dateTime;
        this.formname = formname;
        this.formremarks = formremarks;
        this.expanded=false;
    }


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public class_UserData(String email, String dateTime, String name, String mobile, String userId, String password) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.dateTime = dateTime;

    }

    public class_UserData(String name, String mobile, String email, String formremarks) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.formremarks = formremarks;
    }

    public class_UserData(String email, String dateTime, String name, String mobile, String formname) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.dateTime = dateTime;
        this.formname = formname;
        this.expanded=false;
    }

    public class_UserData(String email, String password, String dateTime) {
        this.email = email;
        this.password = password;
        this.dateTime = dateTime;
    }

    public class_UserData(String email, String dateTime) {
        this.email = email;
        this.dateTime = dateTime;
    }

    public class_UserData(String name, String mobile, String email, String userId, String dateTime, String formname, String formremarks) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.userId = userId;
        this.dateTime = dateTime;
        this.formname = formname;
        this.formremarks = formremarks;
    }

    public class_UserData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getFormremarks() {
        return formremarks;
    }

    public void setFormremarks(String formremarks) {
        this.formremarks = formremarks;
    }
}
