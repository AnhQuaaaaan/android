package com.example.congviecktra.models;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id, soluong;
    private String tencongviec, noidungcongviec, ngayhoanthanh, tinhtrang, congtac;

    public CongViec( String tinhtrang, int soluong) {
        this.soluong = soluong;
        this.tinhtrang = tinhtrang;
    }

    public CongViec(int id, String tencongviec, String noidungcongviec, String ngayhoanthanh, String tinhtrang, String congtac) {
        this.id = id;
        this.tencongviec = tencongviec;
        this.noidungcongviec = noidungcongviec;
        this.ngayhoanthanh = ngayhoanthanh;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public CongViec(String tencongviec, String noidungcongviec, String ngayhoanthanh, String tinhtrang, String congtac) {
        this.tencongviec = tencongviec;
        this.noidungcongviec = noidungcongviec;
        this.ngayhoanthanh = ngayhoanthanh;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTencongviec() {
        return tencongviec;
    }

    public void setTencongviec(String tencongviec) {
        this.tencongviec = tencongviec;
    }

    public String getNoidungcongviec() {
        return noidungcongviec;
    }

    public void setNoidungcongviec(String noidungcongviec) {
        this.noidungcongviec = noidungcongviec;
    }

    public String getNgayhoanthanh() {
        return ngayhoanthanh;
    }

    public void setNgayhoanthanh(String ngayhoanthanh) {
        this.ngayhoanthanh = ngayhoanthanh;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getCongtac() {
        return congtac;
    }

    public void setCongtac(String congtac) {
        this.congtac = congtac;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "id=" + id +
                ", tencongviec='" + tencongviec + '\'' +
                ", noidungcongviec='" + noidungcongviec + '\'' +
                ", ngayhoanthanh='" + ngayhoanthanh + '\'' +
                ", tinhtrang='" + tinhtrang + '\'' +
                ", congtac='" + congtac + '\'' +
                '}';
    }
}
