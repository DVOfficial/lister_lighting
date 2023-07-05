package com.listerled.listerlighting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    class_User userRegister;
    Calendar calenderCC=Calendar.getInstance();
    SimpleDateFormat simpleDateFormatCC= new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
    final String udateTimeCC=simpleDateFormatCC.format(calenderCC.getTime());

    EditText username;
    EditText password;
    Button loginButton;
    TextView signupText;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        signupText = findViewById(R.id.signupText);


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email, spassword;

                mAuth= FirebaseAuth.getInstance();
                email=username.getText().toString().trim();
                spassword=password.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Enter Email!", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(spassword)){
                    Toast.makeText(LoginActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.signInWithEmailAndPassword(email,spassword)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user=mAuth.getCurrentUser();
                                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        Intent intent =new Intent(LoginActivity.this,Home.class);
                                        startActivity(intent);
                                        class_User user1 = new class_User(12,"DevDV");
                                        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                                        sessionManagement.saveSession(user1);

                                    } else {
                                        Toast.makeText(LoginActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
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
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        String userID = sessionManagement.getSSession();
        if (!userID.equals("")) {
            //user id logged in and so move to mainActivity
            moveToMainActivity();
        }
    }
    private void moveToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, Home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
