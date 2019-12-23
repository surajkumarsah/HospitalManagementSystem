package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class Book_Appointment_Activity extends AppCompatActivity {

    Spinner Specialization;
    EditText Date,Time,Fee;
    Button Submit;
    String date,time,inputSpec;

    ArrayAdapter<String> arrayAdapter1;

    ProgressDialog loadingBar;

    String specializations[] = {"Gynecologist","Physician","Dermatologist","Homeopath","Ayurveda","Dentist","E-n-t Specialist","Bones Specialist"};

    DatabaseReference bookAppointmentref, DocAppoiRef;
    String mobno;

    private String saveCurrentDate, saveCurrentTime;
    private String productRandomKey;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__appoientment_);

        Specialization = (Spinner) findViewById(R.id.specialization);
        Date = (EditText) findViewById(R.id.date);
        Time = (EditText) findViewById(R.id.time);
        Fee = (EditText) findViewById(R.id.fee);
        Submit = (Button) findViewById(R.id.submit);
        loadingBar = new ProgressDialog(this);

        mobno = getIntent().getExtras().getString("mobno");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,YYYY");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        //Specialization Spinner Code for drop down
        arrayAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,specializations);

        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Specialization.setAdapter(arrayAdapter1);

        Specialization.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch(position)
                {
                    case 0:
                        inputSpec = "Gynecologist";
                        break;

                    case 1:
                        inputSpec = "Physician";
                        break;
                    case 2:
                        inputSpec = "Dermatologist";
                        break;

                    case 3:
                        inputSpec = "Homeopath";
                        break;
                    case 4:
                        inputSpec = "Ayurveda";
                        break;

                    case 5:
                        inputSpec = "Dentist";
                        break;
                    case 6:
                        inputSpec = "E-n-t Specialist";
                        break;

                    case 7:
                        inputSpec = "Bones Specialist";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }

    private void validate()
    {
        date = Date.getText().toString();
        time = Time.getText().toString();

        if (TextUtils.isEmpty(date))
        {
            Toast.makeText(Book_Appointment_Activity.this,"Please,Enter username.",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(time))
        {
            Toast.makeText(Book_Appointment_Activity.this,"Please,Enter password.",Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingBar.setTitle("Loading ...");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            storetoDB();
            storeDocDB();
        }
    }

    private void storeDocDB()
    {
        DocAppoiRef = FirebaseDatabase.getInstance().getReference().child("appointment").child(productRandomKey);
        DocAppoiRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                HashMap<String, Object> appointmentDetails = new HashMap<>();

                appointmentDetails.put("mobile", mobno);
                appointmentDetails.put("specialization",inputSpec);
                appointmentDetails.put("date",date);
                appointmentDetails.put("time", time);


                DocAppoiRef.updateChildren(appointmentDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(Book_Appointment_Activity.this,"Congratulation,Appointment is Successfully created.",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Book_Appointment_Activity.this,Patient_Activity.class);
                                    intent.putExtra("mobno",mobno);
                                    startActivity(intent);
                                }
                                else{
                                    loadingBar.dismiss();
                                    Toast.makeText(Book_Appointment_Activity.this,"Network Error, Please try again after Sometime.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void storetoDB()
    {
        bookAppointmentref = FirebaseDatabase.getInstance().getReference().child("Users").child("patient").child(mobno).child("appointment").child(productRandomKey);
        bookAppointmentref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                HashMap<String, Object> appointmentDetails = new HashMap<>();

                appointmentDetails.put("mobile", mobno);
                appointmentDetails.put("specialization",inputSpec);
                appointmentDetails.put("date",date);
                appointmentDetails.put("time", time);


                bookAppointmentref.updateChildren(appointmentDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(Book_Appointment_Activity.this,"Congratulation,Appointment is Successfully created.",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Book_Appointment_Activity.this, Patient_Activity.class);
                                    intent.putExtra("mobno",mobno);
                                    startActivity(intent);
                                    finish();

                                }
                                else{
                                    loadingBar.dismiss();
                                    Toast.makeText(Book_Appointment_Activity.this,"Network Error, Please try again after Sometime.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}

