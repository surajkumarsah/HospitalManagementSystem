package com.example.hospitalmanagementsystem.Model;

public class BookedAppointmentView
{
    private String date;
    private String time;
    private String fee;
    private String specialization;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public BookedAppointmentView(String date, String time, String fee, String specialization,String mobile, String name) {
        this.date = date;
        this.time = time;
        this.fee = fee;
        this.specialization = specialization;
        this.mobile = mobile;
        this.name = name;
    }


    BookedAppointmentView()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }



}
