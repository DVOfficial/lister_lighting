package com.listerled.listerlighting;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    String SESSION_Email = "session_email";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(Class_User user){
        //save session of user whenever user is logged in
        int id = user.getId();
        String email=user.getName();

        editor.putInt(SESSION_KEY,id).commit();
        editor.putString(SESSION_Email,email).commit();

    }

    public int getSession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }
    public String getSSession(){
        return sharedPreferences.getString(SESSION_Email,"");
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }

    public void removeSSession(){
        editor.putString(SESSION_Email,"").commit();
    }
}
