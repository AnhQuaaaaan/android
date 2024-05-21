package com.example.de3navi.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten,ngaybatdau,chuyennganh,hocphi;
    private int kichhoat;

    public Book() {
    }

    public Book(int id, String ten, String ngaybatdau, String chuyennganh, String hocphi, int kichhoat) {
        this.id = id;
        this.ten = ten;
        this.ngaybatdau = ngaybatdau;
        this.chuyennganh = chuyennganh;
        this.hocphi = hocphi;
        this.kichhoat = kichhoat;
    }

    public Book(String ten, String ngaybatdau, String chuyennganh, String hocphi, int kichhoat) {
        this.ten = ten;
        this.ngaybatdau = ngaybatdau;
        this.chuyennganh = chuyennganh;
        this.hocphi = hocphi;
        this.kichhoat = kichhoat;
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

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getChuyennganh() {
        return chuyennganh;
    }

    public void setChuyennganh(String chuyennganh) {
        this.chuyennganh = chuyennganh;
    }

    public String getHocphi() {
        return hocphi;
    }

    public void setHocphi(String hocphi) {
        this.hocphi = hocphi;
    }

    public int getKichhoat() {
        return kichhoat;
    }

    public void setKichhoat(int kichhoat) {
        this.kichhoat = kichhoat;
    }
}
