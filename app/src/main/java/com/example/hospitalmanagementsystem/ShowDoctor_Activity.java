package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospitalmanagementsystem.Model.DoctorView;
import com.example.hospitalmanagementsystem.ViewHolder.DoctorViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowDoctor_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference UsersRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child("doctor");

        recyclerView = findViewById(R.id.doctor_recycler_menu);
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

        FirebaseRecyclerOptions<DoctorView> options = new FirebaseRecyclerOptions.Builder<DoctorView>().setQuery(UsersRef,DoctorView.class).build();

        FirebaseRecyclerAdapter<DoctorView, DoctorViewHolder> adapter = new FirebaseRecyclerAdapter<DoctorView, DoctorViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DoctorViewHolder userViewHolder, int i, @NonNull final DoctorView usersView)
            {
                userViewHolder.name.setText(usersView.getName());
                userViewHolder.address.setText(usersView.getAddress());
                userViewHolder.city.setText(usersView.getCity());
                userViewHolder.mobile.setText(usersView.getMobile());
                userViewHolder.specialization.setText(usersView.getSpecialization());

            }

            @NonNull
            @Override
            public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_doctor_layout,parent,false);
                DoctorViewHolder holder = new DoctorViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
