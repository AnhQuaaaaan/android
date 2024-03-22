package com.example.ex2.model;

public class CauThu {
    private String ten,ngaysinh;
    private boolean gioitinh,hauve,tienve,tiendao;

    public CauThu() {
    }

    public CauThu(String ten, String ngaysinh, boolean gioitinh, boolean hauve, boolean tienve, boolean tiendao) {
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.hauve = hauve;
        this.tienve = tienve;
        this.tiendao = tiendao;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public boolean isHauve() {
        return hauve;
    }

    public void setHauve(boolean hauve) {
        this.hauve = hauve;
    }

    public boolean isTienve() {
        return tienve;
    }

    public void setTienve(boolean tienve) {
        this.tienve = tienve;
    }

    public boolean isTiendao() {
        return tiendao;
    }

    public void setTiendao(boolean tiendao) {
        this.tiendao = tiendao;
    }
}
