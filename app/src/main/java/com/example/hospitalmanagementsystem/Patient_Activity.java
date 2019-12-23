package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Patient_Activity extends AppCompatActivity {

    ImageView Profile,Appointment,BookAppointment;
    String mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_);

        Profile = (ImageView) findViewById(R.id.profile);
        Appointment = (ImageView) findViewById(R.id.appointment);
        BookAppointment = (ImageView) findViewById(R.id.book_appointment);

        mobno = getIntent().getExtras().getString("mobno");

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Patient_Activity.this,SignUp_Activity.class);
                intent.putExtra("mobno",mobno);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Patient_Activity.this, ShowPatientAppointment.class);
                intent.putExtra("mobno",mobno);
                startActivity(intent);
            }
        });

        BookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Patient_Activity.this, Book_Appointment_Activity.class);
                intent.putExtra("mobno",mobno);
                startActivity(intent);
            }
        });



    }
}
