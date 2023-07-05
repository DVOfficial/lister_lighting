package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button signupButton;
    TextView loginButton;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;

    FirebaseUser firebaseUser;

    Calendar calenderCC=Calendar.getInstance();
    SimpleDateFormat simpleDateFormatCC= new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
    final String udateTimeCC=simpleDateFormatCC.format(calenderCC.getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        firebaseAuth=FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);
        loginButton = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, spassword;
                email=username.getText().toString().trim();
                spassword=password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignupActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(spassword)){
                    Toast.makeText(SignupActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(email,spassword)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user=firebaseAuth.getCurrentUser();
                                        Toast.makeText(SignupActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent =new Intent(SignupActivity.this,Home.class);
                                        startActivity(intent);

                                        DatabaseReference refAdmin = FirebaseDatabase.getInstance().getReference().child("Admin").child("UserRegister");
                                        HashMap<String,Object> map= new HashMap<>();
                                        String uuser= firebaseAuth.getUid();
                                        map.put("EmailId",email);
                                        map.put("Password",spassword);
                                        map.put("Userid",uuser);
                                        map.put("DateTime",udateTimeCC);

                                        DatabaseReference refUserRegister = FirebaseDatabase.getInstance().getReference().child("UserRegistration").child(udateTimeCC+uuser);
                                        refUserRegister.setValue(map);
                                        DatabaseReference refUserUIDRegister = FirebaseDatabase.getInstance().getReference().child("User").child(uuser);
                                        refUserUIDRegister.setValue(map);

                                        SharedPreferences sp=getSharedPreferences("register",MODE_PRIVATE);
                                        SharedPreferences.Editor ed=sp.edit();
                                        ed.putString("EMail",email);
                                        ed.commit();

                                    } else {
//                                        progressBar.setVisibility(View.GONE);

                                        Toast.makeText(SignupActivity.this, "Email-ID Already Registered,Please Login!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                    }

                                }
                            });

                }
            }
        });
    }
    @Override
    protected void onStart() {

        super.onStart();
        checkSession();
    }
    private void checkSession() {
        //check if user is logged in
        //if user is logged in --> move to mainActivity
        SessionManagement sessionManagement = new SessionManagement(SignupActivity.this);
        String userID = sessionManagement.getSSession();
        if (!userID.equals("")) {
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }
    }
    private void moveToMainActivity() {
        Intent intent = new Intent(SignupActivity.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
//
}