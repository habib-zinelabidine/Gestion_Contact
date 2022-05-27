package com.example.gestioncontacts;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class Accueil extends AppCompatActivity implements View.OnClickListener {
Button btn_ajt_acc,btn_aff_acc;
TextView tv_user_acc;
static ArrayList<Contact> data=new
            ArrayList<Contact>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        btn_aff_acc=findViewById(R.id.btn_aff_acc);
        btn_ajt_acc=findViewById(R.id.btn_ajt_acc);
        tv_user_acc=findViewById(R.id.tv_user_acc);
        btn_ajt_acc.setOnClickListener(this);
        btn_aff_acc.setOnClickListener(this);


    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ajt_acc:
                Intent i = new Intent(Accueil.this, Ajout.class);
                startActivity(i);
                break;
            case R.id.btn_aff_acc:
                Intent j = new Intent(Accueil.this, Edition.class);
                startActivity(j);
                break;
        }
    }
}