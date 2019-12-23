package com.example.hospitalmanagementsystem.Model;

public class PatientView
{
    private String name;
    private String address;
    private String city;
    private String gender;
    private String email;
    private String mobile;
    private String password;
    private String cpassword;


    PatientView()
    {

    }


    public PatientView(String name, String address, String city, String gender, String email, String mobile, String password, String cpassword) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.cpassword = cpassword;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }




}
