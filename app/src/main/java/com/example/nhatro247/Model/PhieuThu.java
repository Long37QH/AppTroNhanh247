package com.example.nhatro247.Model;

public class PhieuThu {
    private int idPThu;
    private int idKhach;
    private int idPhong;
    private int dienTT;
    private int nuocTT;
    private int tienThu;
    private String tg_Lapphieu;
    private String tg_ThuTien;
    private String trangthaiphieu;

    public PhieuThu() {
    }
    public PhieuThu(int idPhong, int tienThu, String trangthaiphieu) {
        this.idPhong = idPhong;
        this.tienThu = tienThu;
        this.trangthaiphieu = trangthaiphieu;
    }

    public PhieuThu(int idPhong, int tienThu, String tg_ThuTien, String trangthaiphieu) {
        this.idPhong = idPhong;
        this.tienThu = tienThu;
        this.tg_ThuTien = tg_ThuTien;
        this.trangthaiphieu = trangthaiphieu;
    }

    public PhieuThu(int idPThu, int idKhach, int idPhong, int dienTT, int nuocTT, int tienThu, String tg_Lapphieu, String tg_ThuTien, String trangthaiphieu) {
        this.idPThu = idPThu;
        this.idKhach = idKhach;
        this.idPhong = idPhong;
        this.dienTT = dienTT;
        this.nuocTT = nuocTT;
        this.tienThu = tienThu;
        this.tg_Lapphieu = tg_Lapphieu;
        this.tg_ThuTien = tg_ThuTien;
        this.trangthaiphieu = trangthaiphieu;
    }

    public int getIdPThu() {
        return idPThu;
    }

    public void setIdPThu(int idPThu) {
        this.idPThu = idPThu;
    }

    public int getIdKhach() {
        return idKhach;
    }

    public void setIdKhach(int idKhach) {
        this.idKhach = idKhach;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }

    public int getDienTT() {
        return dienTT;
    }

    public void setDienTT(int dienTT) {
        this.dienTT = dienTT;
    }

    public int getNuocTT() {
        return nuocTT;
    }

    public void setNuocTT(int nuocTT) {
        this.nuocTT = nuocTT;
    }

    public int getTienThu() {
        return tienThu;
    }

    public void setTienThu(int tienThu) {
        this.tienThu = tienThu;
    }

    public String getTg_Lapphieu() {
        return tg_Lapphieu;
    }

    public void setTg_Lapphieu(String tg_Lapphieu) {
        this.tg_Lapphieu = tg_Lapphieu;
    }

    public String getTg_ThuTien() {
        return tg_ThuTien;
    }

    public void setTg_ThuTien(String tg_ThuTien) {
        this.tg_ThuTien = tg_ThuTien;
    }
    public String getTrangthaiphieu() {
        return trangthaiphieu;
    }

    public void setTrangthaiphieu(String trangthaiphieu) {
        this.trangthaiphieu = trangthaiphieu;
    }

    @Override
    public String toString() {
        return "PhieuThu{" +
                "idPThu=" + idPThu +
                ", idKhach=" + idKhach +
                ", idPhong=" + idPhong +
                ", dienTT=" + dienTT +
                ", nuocTT=" + nuocTT +
                ", tienThu=" + tienThu +
                ", tg_Lapphieu='" + tg_Lapphieu + '\'' +
                ", tg_ThuTien='" + tg_ThuTien + '\'' +
                ", trangthaiphieu='" + trangthaiphieu + '\'' +
                '}';
    }
}
