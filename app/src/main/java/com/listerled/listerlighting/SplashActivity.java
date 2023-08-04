package com.listerled.listerlighting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {




    public static int SPLASH_TIMER = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart=prefs.getBoolean("firstStart",true);

        if(firstStart){
            //showStartDialog();
//            SharedPreferences sp=getSharedPreferences("register",MODE_PRIVATE);
//            SharedPreferences.Editor ed=sp.edit();
//            ed.putString("id","");
//            ed.apply();
//            getDeviceInfo();
            Class_User user1 = new Class_User(-1,"","");

            SessionManagement sessionManagement = new SessionManagement(SplashActivity.this);
            sessionManagement.saveSession(user1);
        }
        checkSession();

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent intent =new Intent(SplashActivity.this, HomePage.class);
                startActivity(intent);

                finish();

            }
        },SPLASH_TIMER
        );





    }

    @Override
    protected void onStart() {


        super.onStart();
    }

    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(SplashActivity.this);
        String userID = sessionManagement.getSSession();
        if (!userID.equals("")) {
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }



    }
    private void moveToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, Home2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

}