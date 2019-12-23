package com.example.hospitalmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospitalmanagementsystem.Model.BookedAppointmentView;
import com.example.hospitalmanagementsystem.Model.DoctorView;
import com.example.hospitalmanagementsystem.Model.PatientView;
import com.example.hospitalmanagementsystem.ViewHolder.BookedAppointmentViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowDoctorAppointment extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DatabaseReference DoctorAppo, UsersRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    String mobno,address,name,inputSpec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doctor_appointment);



        mobno = getIntent().getExtras().getString("mobno");
        inputSpec = getIntent().getExtras().getString("spec");


        //DoctorAppo = FirebaseDatabase.getInstance().getReference().child("appointment");

        //UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child("patient").child(mobno);

        recyclerView = findViewById(R.id.doc_appointment_recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


       /* UsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    DoctorView doctorView = dataSnapshot.getValue(DoctorView.class);
                    address = doctorView.getAddress();
                    name = doctorView.getName();
                    inputSpec = doctorView.getSpecialization();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        return false;
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        DoctorAppo = FirebaseDatabase.getInstance().getReference().child("appointment").child(inputSpec);

        FirebaseRecyclerOptions<BookedAppointmentView> options = new FirebaseRecyclerOptions.Builder<BookedAppointmentView>().setQuery(DoctorAppo,BookedAppointmentView.class).build();

        FirebaseRecyclerAdapter<BookedAppointmentView, BookedAppointmentViewHolder> adapter = new FirebaseRecyclerAdapter<BookedAppointmentView, BookedAppointmentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookedAppointmentViewHolder userViewHolder, int i, @NonNull final BookedAppointmentView usersView)
            {
                userViewHolder.name.setText(name);
                userViewHolder.address.setText(address);
                userViewHolder.specialization.setText(inputSpec);
                userViewHolder.date.setText(usersView.getDate());
                userViewHolder.mobile.setText(usersView.getMobile());
            }

            @NonNull
            @Override
            public BookedAppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_doctor_appointment_layout,parent,false);
                BookedAppointmentViewHolder holder = new BookedAppointmentViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}
