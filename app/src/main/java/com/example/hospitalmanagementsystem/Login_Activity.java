package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Model.DoctorView;
import com.example.hospitalmanagementsystem.Model.PatientView;
import com.example.hospitalmanagementsystem.Prevalent.userData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Login_Activity extends AppCompatActivity {

    String data;
    EditText UserName,Password;
    TextView text;
    Button Login,SignUp;
    ProgressDialog loadingBar;
    String Username,password;
    DatabaseReference DoctorRef;
    private com.rey.material.widget.CheckBox checkBoxRememberMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        data = getIntent().getExtras().getString("data");
        Paper.init(Login_Activity.this);


        UserName = (EditText) findViewById(R.id.username);
        Password = (EditText) findViewById(R.id.password);
        text = (TextView) findViewById(R.id.patient_text);
        Login = (Button) findViewById(R.id.login);
        SignUp = (Button) findViewById(R.id.sign_up);

        loadingBar = new ProgressDialog(this);

        checkBoxRememberMe = (com.rey.material.widget.CheckBox) findViewById(R.id.remember_me_chkb);

        if (data.equals("patient"))
        {
            text.setVisibility(View.VISIBLE);
            SignUp.setVisibility(View.VISIBLE);
        }

        Username  = Paper.book().read(userData.UserMobile);
        password  = Paper.book().read(userData.UserPassword);

        //Remember me to save data in android...


        if (Username != "" && password != "")
        {
            if (!TextUtils.isEmpty(Username) && !TextUtils.isEmpty(password))
            {
                searchDB();
                loadingBar.setTitle("Already Logged In...");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validateData();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String Empty = "0";
                Intent intent = new Intent(Login_Activity.this,SignUp_Activity.class);
                intent.putExtra("mobno",Empty);
                startActivity(intent);
            }
        });
    }

    private void validateData()
    {
        Username = UserName.getText().toString();
        password = Password.getText().toString();

        Paper.book().write(userData.UserMobile,Username);
        Paper.book().write(userData.UserPassword,password);

        if (TextUtils.isEmpty(Username))
        {
            Toast.makeText(Login_Activity.this,"Please,Enter username.",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Username))
        {
            Toast.makeText(Login_Activity.this,"Please,Enter password.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Loading ...");
            loadingBar.setMessage("Please wait...");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            searchDB();
        }
    }

    private void searchDB()
    {

        //Remember me to save data in android...
        if (checkBoxRememberMe.isChecked()) {
            Paper.book().write(userData.UserMobile, Username);
            Paper.book().write(userData.UserPassword, password);
        }

        final DatabaseReference RootRef;

        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(data).child(Username).exists())
                {
                    PatientView userData = dataSnapshot.child("Users").child(data).child(Username).getValue(PatientView.class);

                    if (userData.getMobile().equals(Username))
                    {
                        if (userData.getPassword().equals(password))
                        {
                            if (data.equals("admin"))
                            {
                                Toast.makeText(Login_Activity.this, "Admin, logged in Successfully.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login_Activity.this, Admin_Activity.class);
                                intent.putExtra("mobno",Username);
                                startActivity(intent);
                                finish();
                            }
                            else if (data.equals("patient"))
                            {
                                Toast.makeText(Login_Activity.this, "Patient,logged in Successfully.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login_Activity.this, Patient_Activity.class);
                                intent.putExtra("mobno",Username);
                                startActivity(intent);
                                finish();

                            }
                            else if (data.equals("doctor"))
                            {
                                DoctorView docData = dataSnapshot.child("Users").child(data).child(Username).getValue(DoctorView.class);

                                Toast.makeText(Login_Activity.this, "Doctor,logged in Successfully.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login_Activity.this, Doctor_Activity.class);
                                intent.putExtra("mobno",Username);
                                intent.putExtra("spec",docData.getSpecialization());
                                startActivity(intent);
                                finish();
                            }


                            else
                            {

                                Toast.makeText(Login_Activity.this, "Please, Try Again.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }




                        }


                        else
                        {
                            Toast.makeText(Login_Activity.this, "Error in Logged in", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(Login_Activity.this, Login_Activity.class);
                            startActivity(intent);
                        }

                    }
                    else {
                        Toast.makeText(Login_Activity.this, "Check your loginId and Password.", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                } else {
                    Toast.makeText(Login_Activity.this, "Account with this " + Username + " is not Exist.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Login_Activity.this, "You have to create a new Account.,", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }
}
