package com.example.a10052024.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,cautruc,ngay,thegioi,vietnam;

    private int vacxin;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getCautruc() {
        return cautruc;
    }

    public void setCautruc(String cautruc) {
        this.cautruc = cautruc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getThegioi() {
        return thegioi;
    }

    public void setThegioi(String thegioi) {
        this.thegioi = thegioi;
    }

    public String getVietnam() {
        return vietnam;
    }

    public void setVietnam(String vietnam) {
        this.vietnam = vietnam;
    }

    public int getVacxin() {
        return vacxin;
    }

    public void setVacxin(int vacxin) {
        this.vacxin = vacxin;
    }

    public Book(String ten, String cautruc, String ngay, String thegioi, String vietnam, int vacxin) {
        this.ten = ten;
        this.cautruc = cautruc;
        this.ngay = ngay;
        this.thegioi = thegioi;
        this.vietnam = vietnam;
        this.vacxin = vacxin;
    }

    public Book(int id, String ten, String cautruc, String ngay, String thegioi, String vietnam, int vacxin) {
        this.id = id;
        this.ten = ten;
        this.cautruc = cautruc;
        this.ngay = ngay;
        this.thegioi = thegioi;
        this.vietnam = vietnam;
        this.vacxin = vacxin;
    }
}
