package com.example.nhatro247.Model;

public class PhongTro {
    private int idPhong;
    private int dienTich;
    private int giaPhong;
    private int soDien;
    private int giaDien;
    private int soNuoc;
    private int giaNuoc;
    private String trangThai;

    public PhongTro() {
    }

    public PhongTro(int idPhong, int dienTich, String trangThai) {
        this.idPhong = idPhong;
        this.dienTich = dienTich;
        this.trangThai = trangThai;
    }

    public PhongTro(int idPhong, int dienTich, int giaPhong, int soDien, int giaDien, int soNuoc, int giaNuoc, String trangThai) {
        this.idPhong = idPhong;
        this.dienTich = dienTich;
        this.giaPhong = giaPhong;
        this.soDien = soDien;
        this.giaDien = giaDien;
        this.soNuoc = soNuoc;
        this.giaNuoc = giaNuoc;
        this.trangThai = trangThai;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public int getDienTich() {
        return dienTich;
    }

    public void setDienTich(int dienTich) {
        this.dienTich = dienTich;
    }

    public int getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(int giaPhong) {
        this.giaPhong = giaPhong;
    }

    public int getSoDien() {
        return soDien;
    }

    public void setSoDien(int soDien) {
        this.soDien = soDien;
    }

    public int getGiaDien() {
        return giaDien;
    }

    public void setGiaDien(int giaDien) {
        this.giaDien = giaDien;
    }

    public int getSoNuoc() {
        return soNuoc;
    }

    public void setSoNuoc(int soNuoc) {
        this.soNuoc = soNuoc;
    }

    public int getGiaNuoc() {
        return giaNuoc;
    }

    public void setGiaNuoc(int giaNuoc) {
        this.giaNuoc = giaNuoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PhongTro{" +
                "idPhong=" + idPhong +
                ", dienTich=" + dienTich +
                ", giaPhong=" + giaPhong +
                ", soDien=" + soDien +
                ", giaDien=" + giaDien +
                ", soNuoc=" + soNuoc +
                ", giaNuoc=" + giaNuoc +
                ", trangThai='" + trangThai + '\'' +
                '}';
    }
}
