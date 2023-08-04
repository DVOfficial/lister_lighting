package com.listerled.listerlighting;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";

    String SESSION_KEY = "session_user";
    String SESSION_NAME = "session_name";
    String SESSION_CITY = "session_city";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(Class_User user){
        //save session of user whenever user is logged in
        int id = user.getId();
        String partyName = user.party_name;
        String city=user.getCity();

        editor.putInt(SESSION_KEY,id).commit();
        editor.putString(SESSION_NAME,partyName).commit();
        editor.putString(SESSION_CITY,city).commit();

    }
    public int getSession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY, -1);
    }
    public String getSSession(){
        //return user id whose session is saved
        return sharedPreferences.getString(SESSION_NAME, "");
    }

    public String getSSSession(){
        return sharedPreferences.getString(SESSION_CITY,"");
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
    }
    public void removeSSession(){
        editor.putString(SESSION_NAME,"").commit();
    }

    public void removeSSSession(){
        editor.putString(SESSION_CITY,"").commit();
    }
}
