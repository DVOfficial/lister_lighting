package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomePage_f extends AppCompatActivity {
    BottomNavigationView bottom_Navigation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private  final  static int REQUEST_CODE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_f);


        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);


        bottom_Navigation = findViewById(R.id.bottom_Navigation);
        bottom_Navigation.setSelectedItemId(R.id.btm_home);
        bottom_Navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.btm_home:

                        return true;

                    case R.id.btm_AboutUs:
                        startActivity(new Intent(getApplicationContext(), AboutUs.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_AllProducts:
                        startActivity(new Intent(getApplicationContext(), AllProducts.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;
                    case R.id.btm_query:
                        startActivity(new Intent(getApplicationContext(), FetchProductActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

                    case R.id.btm_profile:
                        startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return true;

//                    case R.id.btm_settings:
//                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                        finish();
//                        return true;

                }
                return false;
            }
        });

    }

    private void askPermission() {
        ActivityCompat.requestPermissions(HomePage_f.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                getLastLocation();


            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }






        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void getDeviceInfo() {



//
//
//        final HashMap<String,Object> map= new HashMap<>();
////        map.put("action","addItem");
//        map.put("manufacturer", manufacturer);
//        map.put("brand", brand);
//        map.put("product", product);
//        map.put("model", model);
//        map.put("sdk", sdk);
//        map.put("versioncode", versioncode);
////                return parmas;
//
//        final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("user_data");
//
//
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
////                                            long jobno= dataSnapshot.getChildrenCount();
//
//                reference.child("deviceInfo").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        Toast.makeText(AllProducts.this, "Device Info submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(AllProducts.this, "Error: "+e.toString(),Toast.LENGTH_LONG).show();
//
//                    }
//                });

//
//                User_Class userClass = dataSnapshot.getValue(User_Class.class);
//                username.setText(userClass.getUsername());
//                if (userClass.getImageURL().equals("default")){
//                    profile_image.setImageResource(R.mipmap.ic_launcher);
//                } else {
//
//                    //change this
//                    Glide.with(getApplicationContext()).load(userClass.getImageURL()).into(profile_image);
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//
//        reference.child(FFO_Posted_Date+"_"+).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                Toast.makeText(AllProducts.this, "Job for: "+Job_C_Name+" submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
        SessionManagement sessionManagement = new SessionManagement(HomePage_f.this);
        int userID = sessionManagement.getSession();
        String userName = sessionManagement.getSSession();

        if (userID==-1){
            userName="New User";
        }
        String manufacturer = Build.MANUFACTURER;
        String brand        = Build.BRAND;
        String product      = Build.PRODUCT;
        String model        = Build.MODEL;
        String sdk        = String.valueOf(Build.VERSION.SDK_INT);
        String versioncode        = Build.VERSION.RELEASE;


//
//        String url="https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?action=addDDetails&" +
//                "manufacturer="+manufacturer+"&brand"+brand+"&product="+product+"&model="+model+"&sdk"+sdk+"&versioncode"+versioncode;
        String url="https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?" +
                "action=addDDetails&user="+userName+"&manufacturer="+manufacturer+"&brand="+brand+"&product="+product+"&model="+model+"&sdk="+sdk+"&versioncode="+versioncode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,

                response -> {
//            Toast.makeText(HomePage_f.this, "DeviceID Submitted Successfully1", Toast.LENGTH_SHORT).show();
                    SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putBoolean("firstDevice",false);
                    editor.apply();
                },
                error ->
                        Toast.makeText(HomePage_f.this, "DeviceID Error", Toast.LENGTH_SHORT).show()
        ) ;
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parmas = new HashMap<>();
////                String uuser= firebaseAuth.getUid();
//                //here we pass params
//                parmas.put("action","addDDetails");
//                parmas.put("manufacturer", manufacturer);
//                parmas.put("brand", brand);
//                parmas.put("product", product);
//                parmas.put("model", model);
//                parmas.put("sdk", sdk);
//                parmas.put("versioncode", versioncode);
//
//
//                return parmas;
//            }
//        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(HomePage_f.this);
        queue.add(stringRequest);
//        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
//        Toast.makeText(AllProducts.this, "DeviceID submitted successfully", Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(AllProducts.this, "Lagitude :" +addresses.get(0).getLatitude()+"\n"+"Longitude :"+addresses.get(0).getLongitude()
//                                            +"\n"+"Address :"+addresses.get(0).getAddressLine(0)+"\n"+"City :"+addresses.get(0).getLocality()+"\n"+"Country :"+addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();





    }
    private void getLastLocation1() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(HomePage_f.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                                    String latitude=""+addresses.get(0).getLatitude();
                                    String longitude=""+addresses.get(0).getLongitude();
                                    String address=""+addresses.get(0).getAddressLine(0);
                                    String city=""+addresses.get(0).getLocality();
                                    String country=""+addresses.get(0).getCountryName();

                                    final HashMap<String,Object> map= new HashMap<>();
//        map.put("action","addItem");
                                    map.put("latitude", latitude);

                                    map.put("longitude", longitude);
                                    map.put("address", address);
                                    map.put("city", city);
                                    map.put("country", country);
//                return parmas;

                                    final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("user_data");


                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                                            long jobno= dataSnapshot.getChildrenCount();

                                            reference.child("location").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

//                                                    Toast.makeText(HomePage_f.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();


                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(HomePage_f.this, "Error: "+e.toString(),Toast.LENGTH_LONG).show();

                                                }
                                            });

//
//                User_Class userClass = dataSnapshot.getValue(User_Class.class);
//                username.setText(userClass.getUsername());
//                if (userClass.getImageURL().equals("default")){
//                    profile_image.setImageResource(R.mipmap.ic_launcher);
//                } else {
//
//                    //change this
//                    Glide.with(getApplicationContext()).load(userClass.getImageURL()).into(profile_image);
//                }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

//
//        reference.child(FFO_Posted_Date+"_"+).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                Toast.makeText(AllProducts.this, "Job for: "+Job_C_Name+" submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });




                                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                                            "https://script.google.com/macros/s/AKfycbxuhuClqRcUCk8MEhIMEaErsqxS9wcrk1rIIn4SZ1m1zOO0llKS/exec",
//                                            "https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?",
                                            "https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?",

                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
//                                                    Toast.makeText(HomePage_f.this, "Location Submitted Successfully1", Toast.LENGTH_SHORT).show();

                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(HomePage_f.this, "Location Error", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                    ) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> parmas = new HashMap<>();
//                String uuser= firebaseAuth.getUid();
                                            //here we pass params
                                            parmas.put("action","addLDetails");
                                            parmas.put("latitude", latitude);
                                            parmas.put("longitude", longitude);
                                            parmas.put("address", address);
                                            parmas.put("city", city);
                                            parmas.put("country", country);


                                            return parmas;
                                        }
                                    };

                                    int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
                                    RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                                    stringRequest.setRetryPolicy(retryPolicy);

                                    RequestQueue queue = Volley.newRequestQueue(HomePage_f.this);
                                    queue.add(stringRequest);
//        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(HomePage_f.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(AllProducts.this, "Lagitude :" +addresses.get(0).getLatitude()+"\n"+"Longitude :"+addresses.get(0).getLongitude()
//                                            +"\n"+"Address :"+addresses.get(0).getAddressLine(0)+"\n"+"City :"+addresses.get(0).getLocality()+"\n"+"Country :"+addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location !=null){
                                Geocoder geocoder=new Geocoder(HomePage_f.this, Locale.getDefault());
                                List<Address> addresses= null;
                                try {
                                    addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

                                    SessionManagement sessionManagement = new SessionManagement(HomePage_f.this);
                                    int userID = sessionManagement.getSession();
                                    String userName = sessionManagement.getSSession();

                                    if (userID==-1){
                                        userName="New User";
                                    }

                                    String latitude=""+addresses.get(0).getLatitude();
                                    String longitude=""+addresses.get(0).getLongitude();
                                    String address=""+addresses.get(0).getAddressLine(0);
                                    String city=""+addresses.get(0).getLocality();
                                    String country=""+addresses.get(0).getCountryName();

                                    final HashMap<String,Object> map= new HashMap<>();
//        map.put("action","addItem");
                                    map.put("user", userName);
                                    map.put("latitude", latitude);

                                    map.put("longitude", longitude);
                                    map.put("address", address);
                                    map.put("city", city);
                                    map.put("country", country);
//                return parmas;

                                    final DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("user_data");


                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                                            long jobno= dataSnapshot.getChildrenCount();

                                            reference.child("location").setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    Toast.makeText(HomePage_f.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(HomePage_f.this, "Error: "+e.toString(),Toast.LENGTH_LONG).show();

                                                }
                                            });

//
//                User_Class userClass = dataSnapshot.getValue(User_Class.class);
//                username.setText(userClass.getUsername());
//                if (userClass.getImageURL().equals("default")){
//                    profile_image.setImageResource(R.mipmap.ic_launcher);
//                } else {
//
//                    //change this
//                    Glide.with(getApplicationContext()).load(userClass.getImageURL()).into(profile_image);
//                }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });

//
//        reference.child(FFO_Posted_Date+"_"+).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                Toast.makeText(AllProducts.this, "Job for: "+Job_C_Name+" submitted successfully", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });



                                    String url="https://script.google.com/macros/s/AKfycbyrZzg4U5dwsLwLuDqmGovaW6f3OtnuEHHgOCUEejxdIV0JKz0Vi4ZFzx1xkFQMJOrs/exec?action=addLDetails&user=" +userName+
                                            "&latitude="+latitude+"&longitude="+longitude+"&address="+address+"&city="+city+"&country="+country;

                                    StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
//                                                    Toast.makeText(HomePage_f.this, "Location Submitted Successfully1", Toast.LENGTH_SHORT).show();
                                                    SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
                                                    SharedPreferences.Editor editor=prefs.edit();
                                                    editor.putBoolean("firstLocation",false);
                                                    editor.apply();
                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    Toast.makeText(HomePage_f.this, "Location Error", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                    );
//                                    {
//                                        @Override
//                                        protected Map<String, String> getParams() {
//                                            Map<String, String> parmas = new HashMap<>();
////                String uuser= firebaseAuth.getUid();
//                                            //here we pass params
//                                            parmas.put("action","addLDetails");
//                                            parmas.put("latitude", latitude);
//                                            parmas.put("longitude", longitude);
//                                            parmas.put("address", address);
//                                            parmas.put("city", city);
//                                            parmas.put("country", country);
//
//
//                                            return parmas;
//                                        }
//                                    };

                                    int socketTimeOut = 50000;// u can change this .. here it is 50 seconds
                                    RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                                    stringRequest.setRetryPolicy(retryPolicy);

                                    RequestQueue queue = Volley.newRequestQueue(HomePage_f.this);
                                    queue.add(stringRequest);
//        ClearData() ;
//        Toast.makeText(this, "Job Submitted Successfully", Toast.LENGTH_SHORT).show();
//                                    Toast.makeText(HomePage_f.this, "Location submitted successfully", Toast.LENGTH_SHORT).show();

//                                    Toast.makeText(AllProducts.this, "Lagitude :" +addresses.get(0).getLatitude()+"\n"+"Longitude :"+addresses.get(0).getLongitude()
//                                            +"\n"+"Address :"+addresses.get(0).getAddressLine(0)+"\n"+"City :"+addresses.get(0).getLocality()+"\n"+"Country :"+addresses.get(0).getCountryName(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }

                        }
                    });


        }else
        {

            askPermission();

        }
    }

    @Override
    protected void onStart() {
        SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstLocation=prefs.getBoolean("firstLocation",true);
        boolean firstDevice=prefs.getBoolean("firstDevice",true);

        if(firstLocation) {
            getLastLocation();

        }if(firstDevice) {

            getDeviceInfo();
        }

        super.onStart();
    }
}