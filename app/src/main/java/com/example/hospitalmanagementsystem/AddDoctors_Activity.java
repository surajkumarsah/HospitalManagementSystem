package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Model.DoctorView;
import com.example.hospitalmanagementsystem.Model.PatientView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddDoctors_Activity extends AppCompatActivity {

    EditText Name,Address,City,Email,Password,Cpassword,Mobile;
    Button Login,SignUp;
    Spinner Gender,Specialization;
    String data,inputgender,inputSpec;
    TextView Spec;

    String name,address,city,email,password,cpassword,mobile,mobno;

    DatabaseReference DoctorRef;
    ArrayAdapter<String> arrayAdapter1,arrayAdapter2;

    ProgressDialog loadingBar;



    String genders[] = {"Male","FeMale"};
    String specializations[] = {"Gynecologist","Physician","Dermatologist","Homeopath","Ayurveda","Dentist","E-n-t Specialist","Bones Specialist"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctors_);

        mobno = getIntent().getExtras().getString("mobno");

        if (!mobno.equals("0"))
        {
            fetchStudentDetails(mobno);
        }


        Name = (EditText) findViewById(R.id.name);
        Address = (EditText) findViewById(R.id.address);
        City = (EditText) findViewById(R.id.city);
        Email = (EditText) findViewById(R.id.email);
        Mobile = (EditText) findViewById(R.id.mobile);
        Password = (EditText) findViewById(R.id.password);
        Cpassword = (EditText) findViewById(R.id.cpassword);
        Spec = (TextView) findViewById(R.id.spec);

        Gender = (Spinner) findViewById(R.id.gender);
        Specialization = (Spinner) findViewById(R.id.specialization);


        Login = (Button) findViewById(R.id.login);
        SignUp = (Button) findViewById(R.id.sign_up);

        loadingBar = new ProgressDialog(this);

        //Gender Spinner Code for drop down
        arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,genders);

        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Gender.setAdapter(arrayAdapter2);

        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch(position)
                {
                    case 0:
                        inputgender = "Male";
                        break;

                    case 1:
                        inputgender = "FeMale";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
                        Spec.setText(inputSpec);
                        break;

                    case 1:
                        inputSpec = "Physician";
                        Spec.setText(inputSpec);
                        break;
                    case 2:
                        inputSpec = "Dermatologist";
                        Spec.setText(inputSpec);
                        break;

                    case 3:
                        inputSpec = "Homeopath";
                        Spec.setText(inputSpec);
                        break;
                    case 4:
                        inputSpec = "Ayurveda";
                        Spec.setText(inputSpec);
                        break;

                    case 5:
                        inputSpec = "Dentist";
                        Spec.setText(inputSpec);
                        break;
                    case 6:
                        inputSpec = "E-n-t Specialist";
                        Spec.setText(inputSpec);
                        break;
                    case 7:
                        inputSpec = "Bones Specialist";
                        Spec.setText(inputSpec);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        DoctorRef = FirebaseDatabase.getInstance().getReference().child("Users");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddDoctors_Activity.this,Login_Activity.class);
                data = "patient";
                intent.putExtra("data",data);
                startActivity(intent);

            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validateAcc();
            }
        });



    }

    private void validateAcc()
    {

        name = Name.getText().toString();
        address = Address.getText().toString();
        city = City.getText().toString();
        email = Email.getText().toString();
        mobile = Mobile.getText().toString();
        password = Password.getText().toString();
        cpassword = Cpassword.getText().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter username.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter Address.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(city))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter City.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter Email.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter Mobile.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter password.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(cpassword))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,Enter cpassword.",Toast.LENGTH_SHORT).show();

        }
        else if (!password.equals(cpassword))
        {
            Toast.makeText(AddDoctors_Activity.this,"Please,password doesn't match, Rewrite password",Toast.LENGTH_SHORT).show();
        }
        else
        {

            loadingBar.setTitle("Loading ...");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            storeInDB();
        }


    }

    private void storeInDB()
    {
        DoctorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                HashMap<String, Object> patientDetails = new HashMap<>();

                patientDetails.put("name", name);
                patientDetails.put("address",address);
                patientDetails.put("city",city);
                patientDetails.put("email", email);
                patientDetails.put("gender",inputgender);
                patientDetails.put("specialization",inputSpec);
                patientDetails.put("mobile", mobile);
                patientDetails.put("password", password);
                patientDetails.put("cpassword", cpassword);


                DoctorRef.child("doctor").child(mobile).updateChildren(patientDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(AddDoctors_Activity.this,"Congratulation, Doctors Details has been created Successfully.",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(AddDoctors_Activity.this,Home_Activity.class);
                                    startActivity(intent);
                                }
                                else{
                                    loadingBar.dismiss();
                                    Toast.makeText(AddDoctors_Activity.this,"Network Error, Please try again after Sometime.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void fetchStudentDetails(String txtRegNo) {
        DoctorRef = FirebaseDatabase.getInstance().getReference().child("Users").child("doctor").child(mobno);

        DoctorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DoctorView usersView = dataSnapshot.getValue(DoctorView.class);

                    Name.setText(usersView.getName());
                    Address.setText(usersView.getAddress());
                    City.setText(usersView.getCity());
                    Email.setText(usersView.getEmail());
                    Mobile.setText(usersView.getMobile());
                    Password.setText(usersView.getPassword());
                    Spec.setText(usersView.getSpecialization());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}

