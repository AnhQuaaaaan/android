package com.example.thuchanh1.Model;

public class Bao {
    private String ten,time;
    private boolean cb1,cb2,cb3;

    public Bao() {
    }

    public Bao(String ten, String time, boolean cb1, boolean cb2, boolean cb3) {
        this.ten = ten;
        this.time = time;
        this.cb1 = cb1;
        this.cb2 = cb2;
        this.cb3 = cb3;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isCb1() {
        return cb1;
    }
    public void setCb1(boolean cb1) {
        this.cb1 = cb1;
    }

    public boolean isCb2() {
        return cb2;
    }

    public void setCb2(boolean cb2) {
        this.cb2 = cb2;
    }

    public boolean isCb3() {
        return cb3;
    }

    public void setCb3(boolean cb3) {
        this.cb3 = cb3;
    }
}

