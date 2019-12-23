package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Doctor_Activity extends AppCompatActivity {

    String mobno,inputspec;
    ImageView Profile,Appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_);

        mobno = getIntent().getExtras().getString("mobno");
        inputspec = getIntent().getExtras().getString("spec");

        Profile = (ImageView) findViewById(R.id.profile);
        Appointment = (ImageView) findViewById(R.id.appointment);

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Doctor_Activity.this,AddDoctors_Activity.class);
                intent.putExtra("mobno",mobno);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Doctor_Activity.this,ShowDoctorAppointment.class);
                intent.putExtra("mobno",mobno);
                intent.putExtra("spec",inputspec);
                startActivity(intent);
            }
        });



    }
}
