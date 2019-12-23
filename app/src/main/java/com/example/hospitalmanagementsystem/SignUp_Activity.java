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
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Model.PatientView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp_Activity extends AppCompatActivity {

    EditText Name,Address,City,Email,Password,Cpassword,Mobile;
    Button Login,SignUp;
    Spinner Gender;
    String data,inputgender,mobno;

    String name,address,city,email,password,cpassword,mobile;

    DatabaseReference patientref;
    ArrayAdapter<String> arrayAdapter2;

    ProgressDialog loadingBar;



    String genders[] = {"Male","FeMale"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up__acivity);

        Name = (EditText) findViewById(R.id.name);
        Address = (EditText) findViewById(R.id.address);
        City = (EditText) findViewById(R.id.city);
        Email = (EditText) findViewById(R.id.email);
        Mobile = (EditText) findViewById(R.id.mobile);
        Password = (EditText) findViewById(R.id.password);
        Cpassword = (EditText) findViewById(R.id.cpassword);

        Gender = (Spinner) findViewById(R.id.gender);

        mobno = getIntent().getExtras().getString("mobno");

        Login = (Button) findViewById(R.id.login);
        SignUp = (Button) findViewById(R.id.sign_up);

        loadingBar = new ProgressDialog(this);


        if (!mobno.equals("0"))
        {
            fetchStudentDetails(mobno);
        }



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


        patientref = FirebaseDatabase.getInstance().getReference().child("Users");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SignUp_Activity.this,Login_Activity.class);
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



    private void fetchStudentDetails(String txtRegNo) {
        patientref = FirebaseDatabase.getInstance().getReference().child("Users").child("patient").child(mobno);

        patientref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    PatientView usersView = dataSnapshot.getValue(PatientView.class);

                    Name.setText(usersView.getName());
                    Address.setText(usersView.getAddress());
                    City.setText(usersView.getCity());
                    Email.setText(usersView.getEmail());
                    Mobile.setText(usersView.getMobile());
                    Password.setText(usersView.getPassword());


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
            Toast.makeText(SignUp_Activity.this,"Please,Enter username.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter Address.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(city))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter City.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter Email.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter Mobile.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter password.",Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(cpassword))
        {
            Toast.makeText(SignUp_Activity.this,"Please,Enter cpassword.",Toast.LENGTH_SHORT).show();

        }
        else if (!password.equals(cpassword))
        {
            Toast.makeText(SignUp_Activity.this,"Please,password doesn't match, Rewrite password",Toast.LENGTH_SHORT).show();
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
        patientref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                HashMap<String, Object> patientDetails = new HashMap<>();

                patientDetails.put("name", name);
                patientDetails.put("address",address);
                patientDetails.put("city",city);
                patientDetails.put("gender",inputgender);
                patientDetails.put("email", email);
                patientDetails.put("mobile", mobile);
                patientDetails.put("password", password);
                patientDetails.put("cpassword", cpassword);


                patientref.child("patient").child(mobile).updateChildren(patientDetails)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    loadingBar.dismiss();
                                    Toast.makeText(SignUp_Activity.this,"Congratulation, Student Details has been created Successfully.",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(SignUp_Activity.this,Home_Activity.class);
                                    startActivity(intent);
                                }
                                else{
                                    loadingBar.dismiss();
                                    Toast.makeText(SignUp_Activity.this,"Network Error, Please try again after Sometime.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                /*}
                else{
                    loadingBar.dismiss();
                    Toast.makeText(Add_Stud_Activity.this,"This "+inputReg+" already Exists.",Toast.LENGTH_SHORT).show();

                    Toast.makeText(Add_Stud_Activity.this, "Registration number is already exist try again from another registration number.",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Add_Stud_Activity.this,MainActivity.class);
                    startActivity(intent);
                }*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    }

