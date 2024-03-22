package com.example.exclass.Model;

public class Tour {
    private int img;
    private String lichtrinh,time;

    public Tour() {
    }

    public Tour(int img, String lichtrinh, String time) {
        this.img = img;
        this.lichtrinh = lichtrinh;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getLichtrinh() {
        return lichtrinh;
    }

    public void setLichtrinh(String lichtrinh) {
        this.lichtrinh = lichtrinh;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
