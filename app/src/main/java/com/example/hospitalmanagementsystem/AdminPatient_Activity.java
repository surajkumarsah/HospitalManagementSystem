package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AdminPatient_Activity extends AppCompatActivity {

    TextView AddPatient,Appointment,Patient;
    String mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_patient_);

        AddPatient = (TextView) findViewById(R.id.add_doctor);
        Appointment = (TextView) findViewById(R.id.appointment);
        Patient = (TextView) findViewById(R.id.doctors);

        mobno = getIntent().getExtras().getString("mobno");


        AddPatient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Empty = "0";
                Intent intent = new Intent(AdminPatient_Activity.this,SignUp_Activity.class);
                intent.putExtra("mobno",Empty);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminPatient_Activity.this,Show_Patient_AppointmentIndvl.class);
                startActivity(intent);
            }
        });

        Patient.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminPatient_Activity.this,ShowPatient_Activity.class);
                startActivity(intent);
            }
        });

    }
}
