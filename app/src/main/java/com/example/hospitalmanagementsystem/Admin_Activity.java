package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Admin_Activity extends AppCompatActivity {

    ImageView Patient,Doctor,Appointment;
    String mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);

        Patient = (ImageView) findViewById(R.id.patient);
        Doctor = (ImageView) findViewById(R.id.doctor);
        Appointment = (ImageView) findViewById(R.id.appointment);

        mobno = getIntent().getExtras().getString("mobno");

        Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,AdminPatient_Activity.class);
                intent.putExtra("mobno",mobno);
                startActivity(intent);
            }
        });

        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,AdminDoctor_Activity.class);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_Activity.this,Home_Activity.class);
                startActivity(intent);
            }
        });
    }
}
