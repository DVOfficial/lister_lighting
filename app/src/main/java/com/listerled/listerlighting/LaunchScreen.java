package com.listerled.listerlighting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchScreen extends AppCompatActivity {

    public static int SPLASH_TIMER = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        splashScreen.setKeepOnScreenCondition(() -> true );
        Intent intent =new Intent(LaunchScreen.this, Home2.class);
        startActivity(intent);

        finish();
//
//        new Handler().postDelayed(new Runnable(){
//
//                                      @Override
//                                      public void run() {
//                                          Intent intent =new Intent(LaunchScreen.this, Home2.class);
//                                          startActivity(intent);
//
//                                          finish();
//
//                                      }
//                                  },SPLASH_TIMER
//        );
//        finish();
    }
}