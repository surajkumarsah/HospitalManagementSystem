package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import io.paperdb.Paper;

public class Home_Activity extends AppCompatActivity {

    ImageView Patient,Doctor,Admin,About;
    String data;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);


        Patient = (ImageView) findViewById(R.id.patient);
        Doctor = (ImageView) findViewById(R.id.doctor);
        Admin = (ImageView) findViewById(R.id.admin);
        About = (ImageView) findViewById(R.id.about);
        logout = (Button) findViewById(R.id.logout);

        Paper.init(this);





        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                data = "admin";
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Paper.book().destroy();
                Toast.makeText(Home_Activity.this,"Successfully logged out.",Toast.LENGTH_SHORT).show();
            }
        });


        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                data = "doctor";
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                data = "patient";
                intent.putExtra("data",data);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Home_Activity.this,About_Hospital_Activity.class);
                startActivity(intent);
            }
        });

    }
}
