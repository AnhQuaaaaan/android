package com.example.de3navi.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,noikhoihanh,dichvu,ngay;

    private int kigui;

    public Book() {
    }

    public Book(int id, String ten, String noikhoihanh, String dichvu, String ngay, int kigui) {
        this.id = id;
        this.ten = ten;
        this.noikhoihanh = noikhoihanh;
        this.dichvu = dichvu;
        this.ngay = ngay;
        this.kigui = kigui;
    }

    public Book(String ten, String noikhoihanh, String dichvu, String ngay, int kigui) {
        this.ten = ten;
        this.noikhoihanh = noikhoihanh;
        this.dichvu = dichvu;
        this.ngay = ngay;
        this.kigui = kigui;
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

    public String getNoikhoihanh() {
        return noikhoihanh;
    }

    public void setNoikhoihanh(String noikhoihanh) {
        this.noikhoihanh = noikhoihanh;
    }

    public String getDichvu() {
        return dichvu;
    }

    public void setDichvu(String dichvu) {
        this.dichvu = dichvu;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getKigui() {
        return kigui;
    }

    public void setKigui(int kigui) {
        this.kigui = kigui;
    }
}
