package com.example.nhatro247.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhatro247.Data.DatabaseHelper;
import com.example.nhatro247.Model.PhongTro;
import com.example.nhatro247.fragments.AddPhongFragment;
import com.example.nhatro247.fragments.HomeFragment;
import com.example.nhatro247.fragments.PhongDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class PhongTroDAO {
    private DatabaseHelper dbHelper;

    public PhongTroDAO(HomeFragment context1){dbHelper = new DatabaseHelper(context1.getContext());}
    public PhongTroDAO(AddPhongFragment context2){dbHelper = new DatabaseHelper(context2.getContext());}
    public PhongTroDAO(PhongDetailFragment context3){dbHelper = new DatabaseHelper(context3.getContext());}


    public List<PhongTro> getAll() {
        List<PhongTro> phongList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_phong", null);
        if (cursor.moveToFirst()) {
            do {
                PhongTro phong = new PhongTro();
                phong.setIdPhong(cursor.getInt(0));
                phong.setDienTich(cursor.getInt(1));
                phong.setGiaPhong(cursor.getInt(2));
                phong.setSoDien(cursor.getInt(3));
                phong.setGiaDien(cursor.getInt(4));
                phong.setSoNuoc(cursor.getInt(5));
                phong.setGiaNuoc(cursor.getInt(6));
                phong.setTrangThai(cursor.getString(7));
                phongList.add(phong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return phongList;
    }

    public PhongTro getPhongById(int id) {
        PhongTro phong = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_phong WHERE id_phong = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            phong = new PhongTro();
            phong.setIdPhong(cursor.getInt(0));
            phong.setDienTich(cursor.getInt(1));
            phong.setGiaPhong(cursor.getInt(2));
            phong.setSoDien(cursor.getInt(3));
            phong.setGiaDien(cursor.getInt(4));
            phong.setSoNuoc(cursor.getInt(5));
            phong.setGiaNuoc(cursor.getInt(6));
            phong.setTrangThai(cursor.getString(7));
        }
        cursor.close();
        return phong;
    }
    public void insertPhong(PhongTro phong){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id_phong = phong.getIdPhong();
        int dientich = phong.getDienTich();
        int giaphong = phong.getGiaPhong();
        int sodien = phong.getSoDien();
        int giadien = phong.getGiaDien();
        int sonuoc = phong.getSoNuoc();
        int gianuoc = phong.getGiaNuoc();
        String trangthai = phong.getTrangThai();

        db.execSQL("INSERT INTO tbl_phong (id_phong,dientich,giaphong,sodien,giadien,sonuoc,gianuoc,trangthai) VALUES (?,?,?,?,?,?,?,?)",
                new String[]{id_phong +"",dientich +"",giaphong+"",sodien+"",giadien+"",sonuoc+"",gianuoc+"",trangthai});
    }

    public void updatePhong(PhongTro phong){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int id_phong = phong.getIdPhong();
        int dientich = phong.getDienTich();
        int giaphong = phong.getGiaPhong();
        int sodien = phong.getSoDien();
        int giadien = phong.getGiaDien();
        int sonuoc = phong.getSoNuoc();
        int gianuoc = phong.getGiaNuoc();
        String trangthai = phong.getTrangThai();
        db.execSQL("UPDATE tbl_phong SET  dientich = ?, giaphong = ?, sodien = ?, giadien = ?, sonuoc = ?, gianuoc = ?, trangthai = ? WHERE id_phong = ?",
                new String []{dientich +"",giaphong+"",sodien+"",giadien+"",sonuoc+"",gianuoc+"",trangthai+"",id_phong +""});
    }

    public void deletePhong(int id_phong){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM tbl_phong where id_phong = ?", new String[]{String.valueOf(id_phong)});
    }

}
