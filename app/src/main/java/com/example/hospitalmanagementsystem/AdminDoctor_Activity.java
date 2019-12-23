package com.example.hospitalmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AdminDoctor_Activity extends AppCompatActivity
{

    ImageView AddDoctor,Appointment,Doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_doctor_);

        AddDoctor = (ImageView) findViewById(R.id.add_doctor);
        Appointment = (ImageView) findViewById(R.id.manage_doc);
        Doctors = (ImageView) findViewById(R.id.view_doctor);


        AddDoctor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Empty = "0";
                Intent intent = new Intent(AdminDoctor_Activity.this,AddDoctors_Activity.class);
                intent.putExtra("mobno",Empty);
                startActivity(intent);
            }
        });

        Appointment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminDoctor_Activity.this,ShowDoctorAppointment.class);
                startActivity(intent);
            }
        });

        Doctors.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminDoctor_Activity.this,ShowDoctor_Activity.class);
                startActivity(intent);
            }
        });

    }
}
