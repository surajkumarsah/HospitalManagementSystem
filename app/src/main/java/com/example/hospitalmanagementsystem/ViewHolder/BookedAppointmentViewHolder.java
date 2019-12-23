package com.example.hospitalmanagementsystem.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalmanagementsystem.Interface.BookedAppointmentClickListner;
import com.example.hospitalmanagementsystem.Interface.DoctorClickListner;
import com.example.hospitalmanagementsystem.R;

public class BookedAppointmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView name,address,date,mobile,specialization;
    public BookedAppointmentClickListner listner;

    public BookedAppointmentViewHolder(@NonNull View itemView)
    {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        date = (TextView) itemView.findViewById(R.id.date);
        mobile = (TextView) itemView.findViewById(R.id.mobile);
        specialization = (TextView) itemView.findViewById(R.id.specialization);
    }

    public void setBookedAppointmentClickListener(BookedAppointmentClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v,getAdapterPosition(),false);
    }
}
