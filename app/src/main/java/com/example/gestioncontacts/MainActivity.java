package com.example.gestioncontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    Button login;
    EditText user,psd;
    TextView register;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login =(Button) findViewById(R.id.loggedin);
        user=(EditText) findViewById(R.id.username);
        psd=(EditText) findViewById(R.id.password);
        register=(TextView) findViewById(R.id.Register);
        Auth=FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            loginUser();
        });

        register.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,Register.class));
        });

    }
    public void loginUser(){
        String email =user.getText().toString();
        String pass=psd.getText().toString();
        if(TextUtils.isEmpty(email)){
            user.setError("Email cannot be empty");
            user.requestFocus();
        }else if(TextUtils.isEmpty(pass)){
            psd.setError("Password cannot be empty");
            psd.requestFocus();
        }else{
            Auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User Logged in Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this,Accueil.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Failled to connect" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}