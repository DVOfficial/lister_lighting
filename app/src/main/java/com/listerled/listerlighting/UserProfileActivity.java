package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserProfileActivity extends AppCompatActivity {

    BottomNavigationView bottom_Navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);



        bottom_Navigation=findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_profile);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btm_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                    case R.id.btm_allproduct:
                        startActivity(new Intent(getApplicationContext(),AllProductsActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(),FetchProductDataActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_profile:

                        return true;
                    case R.id.btm_settings:
                        startActivity(new Intent(getApplicationContext(),SettingActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                }
                return false;            }
        });

    }
}