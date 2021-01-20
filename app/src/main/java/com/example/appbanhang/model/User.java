package com.example.appbanhang.model;

public class User {
    public String UserName, MatKhau, HoTen, DiaChi, SDT;

    public User(){}

    public User(String UserName, String MatKhau, String HoTen,String DiaChi,String SDT) {
        this.UserName = UserName;
        this.MatKhau =MatKhau;
        this.HoTen=HoTen;
        this.DiaChi=DiaChi;
        this.SDT=SDT;
    }
}
