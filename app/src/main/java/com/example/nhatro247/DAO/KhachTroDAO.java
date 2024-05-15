package com.example.nhatro247.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhatro247.Data.DatabaseHelper;
import com.example.nhatro247.Model.KhachTro;
import com.example.nhatro247.fragments.AddPhongFragment;
import com.example.nhatro247.fragments.PhongDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class KhachTroDAO {
    private DatabaseHelper dbHelper;
    public KhachTroDAO(AddPhongFragment context1){dbHelper = new DatabaseHelper(context1.getContext());}
    public KhachTroDAO(PhongDetailFragment context2){dbHelper = new DatabaseHelper(context2.getContext());}

    public List<KhachTro> getKhachAll(){
        List<KhachTro> mListKhach = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_KhachHang", null);
        if (cursor.moveToFirst()){
            do {
                KhachTro khachTro = new KhachTro();
                khachTro.setIdKhach(cursor.getInt(0));
                khachTro.setIdPhong(cursor.getInt(1));
                khachTro.setTenKh(cursor.getString(2));
                khachTro.setSdt(cursor.getString(3));
                khachTro.setNgayVao(cursor.getString(4));
                mListKhach.add(khachTro);
            }while (cursor.moveToNext());
        }
        return mListKhach;
    }

    public KhachTro getKhachById(int id){
        KhachTro khachTro = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_KhachHang WHERE id_phong = ?", new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()) {
            khachTro = new KhachTro();
            khachTro.setIdKhach(cursor.getInt(0));
            khachTro.setIdPhong(cursor.getInt(1));
            khachTro.setTenKh(cursor.getString(2));
            khachTro.setSdt(cursor.getString(3));
            khachTro.setNgayVao(cursor.getString(4));
        }
        cursor.close();
        return khachTro;
    }

    public void insertKhach(KhachTro khachTro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idKhach = khachTro.getIdKhach();
        int idPhong = khachTro.getIdPhong();
        String tenKh = khachTro.getTenKh();
        String sdt = khachTro.getSdt();
        String ngayVao = khachTro.getNgayVao();
        db.execSQL("INSERT INTO tbl_KhachHang (id_khach,id_phong,ten_kh,sdt,ngayvao) VALUES (?,?,?,?,?)",
                new String[]{idKhach +"",idPhong +"",tenKh, sdt, ngayVao});
    }

    public void updateKhach(KhachTro khachTro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idKhach = khachTro.getIdKhach();
        int idPhong = khachTro.getIdPhong();
        String tenKh = khachTro.getTenKh();
        String sdt = khachTro.getSdt();
        String ngayVao = khachTro.getNgayVao();
        db.execSQL("UPDATE tbl_KhachHang SET  id_phong = ?, ten_kh = ?, sdt = ?, ngayvao = ? WHERE id_khach = ?",
                new String[]{idPhong +"",tenKh, sdt, ngayVao, idKhach +""});
    }

    public void deleteKhach(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM tbl_KhachHang where id_khach = ?", new String[]{String.valueOf(id)});
    }
}
