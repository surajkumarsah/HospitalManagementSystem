package com.example.hospitalmanagementsystem.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalmanagementsystem.Interface.DoctorClickListner;
import com.example.hospitalmanagementsystem.R;

public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    public TextView name,address,city,email,mobile,specialization;
    public Button SessionBtn;
    public EditText Session;
    public DoctorClickListner listner;

    public DoctorViewHolder(@NonNull View itemView)
    {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.name);
        address = (TextView) itemView.findViewById(R.id.address);
        city = (TextView) itemView.findViewById(R.id.city);
        email = (TextView) itemView.findViewById(R.id.email);
        mobile = (TextView) itemView.findViewById(R.id.mobile);
        specialization = (TextView) itemView.findViewById(R.id.specialization);
    }

    public void setDoctorClickListener(DoctorClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v,getAdapterPosition(),false);
    }
}
