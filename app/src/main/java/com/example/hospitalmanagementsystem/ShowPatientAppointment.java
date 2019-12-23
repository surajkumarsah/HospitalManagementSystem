package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospitalmanagementsystem.Model.BookedAppointmentView;
import com.example.hospitalmanagementsystem.Model.PatientView;
import com.example.hospitalmanagementsystem.ViewHolder.BookedAppointmentViewHolder;
import com.example.hospitalmanagementsystem.ViewHolder.PatientViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ShowPatientAppointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference UsersRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String mobno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appoientment);

        mobno = getIntent().getExtras().getString("mobno");



        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child("patient").child(mobno).child("appointment");

        //UsersRef = FirebaseDatabase.getInstance().getReference().child("appointment");

        recyclerView = findViewById(R.id.appointment_recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<BookedAppointmentView> options = new FirebaseRecyclerOptions.Builder<BookedAppointmentView>().setQuery(UsersRef,BookedAppointmentView.class).build();

        FirebaseRecyclerAdapter<BookedAppointmentView, BookedAppointmentViewHolder> adapter = new FirebaseRecyclerAdapter<BookedAppointmentView, BookedAppointmentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookedAppointmentViewHolder userViewHolder, int i, @NonNull final BookedAppointmentView usersView)
            {
                userViewHolder.name.setText(usersView.getName());
                userViewHolder.specialization.setText(usersView.getSpecialization());
                userViewHolder.date.setText(usersView.getDate());
                userViewHolder.mobile.setText(usersView.getMobile());
            }

            @NonNull
            @Override
            public BookedAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_patient_appoientment_layout,parent,false);
                BookedAppointmentViewHolder holder = new BookedAppointmentViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
