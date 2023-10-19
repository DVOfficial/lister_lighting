package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class Login1Activity extends AppCompatActivity {

    String loginURL="https://script.google.com/macros/s/AKfycbw9iuzJ2v1wnelWyXQD7T1JWaJEdx6DDSH8Xkogl0Mz1s7H5GBOjTZV7RASlh_Hx089/exec";
//    String loginURL="https://script.google.com/macros/s/AKfycbw9iuzJ2v1wnelWyXQD7T1JWaJEdx6DDSH8Xkogl0Mz1s7H5GBOjTZV7RASlh_Hx089/exec?";

    Class_User userRegister;
    Calendar calenderCC=Calendar.getInstance();
    SimpleDateFormat simpleDateFormatCC= new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
    final String udateTimeCC=simpleDateFormatCC.format(calenderCC.getTime());

    EditText username;
    EditText password;
    Button loginButton;
    TextView signupText;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    ProgressDialog dialogEng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);


//        progressBar = findViewById(R.id.progressBar);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
//        signupText = findViewById(R.id.signupText);


//        signupText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(Login1Activity.this,SignupActivity.class);
//                startActivity(intent);
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                progressBar.setVisibility(View.VISIBLE);
                String userid, spassword;

//                mAuth= FirebaseAuth.getInstance();
                userid=username.getText().toString().trim();
                spassword=password.getText().toString().trim();



                if (TextUtils.isEmpty(userid)) {
                    Toast.makeText(Login1Activity.this, "Enter Username!", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(spassword)){
                    Toast.makeText(Login1Activity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                }else{

                    dialogEng = new ProgressDialog(Login1Activity.this);
                    dialogEng.setTitle("Wait Please... ");
                    dialogEng.setMessage("Login");
                    dialogEng.show();
                    String URL_Products=loginURL+"?action=login&id="+userid;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Products,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        //converting the string to json array object

                                        JSONObject object = new JSONObject(response);
                                        JSONArray array  = object.getJSONArray("items");

//                            JSONArray array = new JSONArray(response);
//                                        list_ProductDesc = new ArrayList<>();
                                        //traversing through all the object
                                        for (int i = 0; i < array.length(); i++) {

                                            //getting product object from json array
                                            JSONObject product = array.getJSONObject(i);

                                            String password1 = product.getString("password");



                                            if (Objects.equals(password1, spassword)) {

                                                int sr_No1 = Integer.parseInt(product.getString("sr_No"));
                                                String party_name1 = product.getString("party_name");
                                                String city1 = product.getString("city");
//                                                String party_name1_city1 = product.getString("party_name_city");

//                                                    progressBar.setVisibility(View.GONE);

                                        Class_User user1 = new Class_User(sr_No1,party_name1,city1);


//                                        SharedPreferences sp=getSharedPreferences("register",MODE_PRIVATE);
//                                        SharedPreferences.Editor ed=sp.edit();
//                                        ed.putString("party_name1",party_name1);
//                                        ed.putString("city1",city1);
//                                        ed.putString("party_name1_city1",party_name1_city1);
//                                        ed.apply();


                                                SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
                                                SharedPreferences.Editor editor=prefs.edit();
                                                editor.putBoolean("firstStart",false);
                                                editor.apply();


                                                SessionManagement sessionManagement = new SessionManagement(Login1Activity.this);
                                        sessionManagement.saveSession(user1);


                                                Toast.makeText(Login1Activity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//
                                                Intent intent =new Intent(Login1Activity.this,FetchProductActivity.class);
                                                startActivity(intent);

                                                finish();
                                            }
                                            else {
                                                Toast.makeText(Login1Activity.this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();

                                            }


                                        }
//
                                        dialogEng.dismiss();
//
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        dialogEng.dismiss();
                                        Toast.makeText(Login1Activity.this, "Login Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Login1Activity.this, "Incorrect USERID, Login Failed: "+error.getMessage(), Toast.LENGTH_SHORT).show();


                                }
                            });

                    Volley.newRequestQueue(getApplicationContext()).add(stringRequest);


                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
//        checkSession();
    }

//    private void checkSession() {
//        //check if user is logged in
//        //if user is logged in --> move to mainActivity
//        SessionManagement sessionManagement = new SessionManagement(Login1Activity.this);
//        int userID = sessionManagement.getSession();
//        if (userID!=-1) {
//            //user id logged in and so move to mainActivity
//            moveToMainActivity();
//        }
//    }
//    private void moveToMainActivity() {
//        Intent intent = new Intent(Login1Activity.this, HomePage_f.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        finish();
//    }


}
