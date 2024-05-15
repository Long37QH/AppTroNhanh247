package com.example.nhatro247.Model;

public class KhachTro {
    private int idKhach;
    private int idPhong;
    private String tenKh;
    private String sdt;
    private String ngayVao;

    public KhachTro() {
    }

    public KhachTro(int idKhach, int idPhong, String tenKh, String sdt, String ngayVao) {
        this.idKhach = idKhach;
        this.idPhong = idPhong;
        this.tenKh = tenKh;
        this.sdt = sdt;
        this.ngayVao = ngayVao;
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

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getNgayVao() {
        return ngayVao;
    }

    public void setNgayVao(String ngayVao) {
        this.ngayVao = ngayVao;
    }

    @Override
    public String toString() {
        return "KhachTro{" +
                "idKhach=" + idKhach +
                ", idPhong=" + idPhong +
                ", tenKh='" + tenKh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngayVao='" + ngayVao + '\'' +
                '}';
    }
}
