package com.example.myapplicationmad;

public class Resturant {
    public String name,email,password,conpassword,mobile,address;

    public Resturant(){

    }
    public Resturant(String name, String email,String password,String conpassword,String mobile,String address){
        this.name=name;
        this.email=email;
        this.password=password;
        this.conpassword=conpassword;
        this.mobile=mobile;
        this.address=address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConpassword() {
        return conpassword;
    }

    public void setConpassword(String conpassword) {
        this.conpassword = conpassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
