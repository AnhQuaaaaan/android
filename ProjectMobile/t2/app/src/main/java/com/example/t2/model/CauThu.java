package com.example.t2.model;

public class CauThu {
    private int img;
    private String ten,ngaysinh,gioitinh;
    private boolean hauve,tienve,tiendao;

    public CauThu() {
    }

    public CauThu(int img,String ten, String ngaysinh, String gioitinh, boolean hauve, boolean tienve, boolean tiendao) {
        this.img = img;
        this.ten = ten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.hauve = hauve;
        this.tienve = tienve;
        this.tiendao = tiendao;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
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
