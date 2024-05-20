package com.example.nhatro247.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhatro247.Data.DatabaseHelper;
import com.example.nhatro247.Model.PhieuThu;
import com.example.nhatro247.fragments.AddPhieuFragment;
import com.example.nhatro247.fragments.LapPhieuFragment;
import com.example.nhatro247.fragments.PhieuDetailFragment;
import com.example.nhatro247.fragments.ThongKeFragment;

import java.util.ArrayList;
import java.util.List;

public class PhieuThuDAO {
    private DatabaseHelper dbHelper;
    public PhieuThuDAO(LapPhieuFragment context){dbHelper = new DatabaseHelper(context.getContext());}
    public PhieuThuDAO(AddPhieuFragment context1){dbHelper = new DatabaseHelper(context1.getContext());}
    public PhieuThuDAO(PhieuDetailFragment context2){dbHelper = new DatabaseHelper(context2.getContext());}
    public PhieuThuDAO(ThongKeFragment context3){dbHelper = new DatabaseHelper(context3.getContext());}

    public List<PhieuThu> getAllPhieu(){
        List<PhieuThu> mListPhieu = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_PhieuThu WHERE trangthaiphieu = 'Chưa đóng tiền'",null);
        if (cursor.moveToFirst()){
            do {
                PhieuThu phieuThu = new PhieuThu();
                phieuThu.setIdPThu(cursor.getInt(0));
                phieuThu.setIdKhach(cursor.getInt(1));
                phieuThu.setIdPhong(cursor.getInt(2));
                phieuThu.setDienTT(cursor.getInt(3));
                phieuThu.setNuocTT(cursor.getInt(4));
                phieuThu.setTienThu(cursor.getInt(5));
                phieuThu.setTg_Lapphieu(cursor.getString(6));
                phieuThu.setTg_ThuTien(cursor.getString(7));
                phieuThu.setTrangthaiphieu(cursor.getString(8));
                mListPhieu.add(phieuThu);
            }while (cursor.moveToNext());
        }
        return mListPhieu;
    }
    public PhieuThu getPhieuById(int id){
        PhieuThu phieuThu = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_PhieuThu WHERE id_phong = ?",new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            phieuThu = new PhieuThu();
            phieuThu.setIdPThu(cursor.getInt(0));
            phieuThu.setIdKhach(cursor.getInt(1));
            phieuThu.setIdPhong(cursor.getInt(2));
            phieuThu.setDienTT(cursor.getInt(3));
            phieuThu.setNuocTT(cursor.getInt(4));
            phieuThu.setTienThu(cursor.getInt(5));
            phieuThu.setTg_Lapphieu(cursor.getString(6));
            phieuThu.setTg_ThuTien(cursor.getString(7));
            phieuThu.setTrangthaiphieu(cursor.getString(8));
        }
        cursor.close();
        return phieuThu;
    }
    public List<PhieuThu> getPhieuByDate(String date){
        List<PhieuThu> mListPhieu = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_PhieuThu WHERE tg_lapphieu = ? AND trangthaiphieu = 'Đã thu'",new String[]{date});
        if (cursor.moveToFirst()){
            do {
                PhieuThu phieuThu = new PhieuThu();
                phieuThu.setIdPThu(cursor.getInt(0));
                phieuThu.setIdKhach(cursor.getInt(1));
                phieuThu.setIdPhong(cursor.getInt(2));
                phieuThu.setDienTT(cursor.getInt(3));
                phieuThu.setNuocTT(cursor.getInt(4));
                phieuThu.setTienThu(cursor.getInt(5));
                phieuThu.setTg_Lapphieu(cursor.getString(6));
                phieuThu.setTg_ThuTien(cursor.getString(7));
                phieuThu.setTrangthaiphieu(cursor.getString(8));
                mListPhieu.add(phieuThu);
            }while (cursor.moveToNext());
        }
        return mListPhieu;
    }
    public  int getTongThu(String date){
        int tongthu = 0;
        //List<PhieuThu> mListPhieu = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_PhieuThu WHERE tg_lapphieu = ? AND trangthaiphieu = 'Đã thu'",new String[]{date});
        if (cursor.moveToFirst()) {
            do {
                PhieuThu phieuThu = new PhieuThu();
                phieuThu.setIdPThu(cursor.getInt(0));
                phieuThu.setIdKhach(cursor.getInt(1));
                phieuThu.setIdPhong(cursor.getInt(2));
                phieuThu.setDienTT(cursor.getInt(3));
                phieuThu.setNuocTT(cursor.getInt(4));
                phieuThu.setTienThu(cursor.getInt(5));
                phieuThu.setTg_Lapphieu(cursor.getString(6));
                phieuThu.setTg_ThuTien(cursor.getString(7));
                phieuThu.setTrangthaiphieu(cursor.getString(8));
                //mListPhieu.add(phieuThu);
                tongthu = tongthu + phieuThu.getTienThu();
            } while (cursor.moveToNext());
        }
        return tongthu;
    }

    public void insertPhieu(PhieuThu phieuThu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idKhach = phieuThu.getIdKhach();
        int idPhong = phieuThu.getIdPhong();
        int dienTT = phieuThu.getDienTT();
        int nuocTT = phieuThu.getNuocTT();
        int tienThu = phieuThu.getTienThu();
        String tg_LapPhieu = phieuThu.getTg_Lapphieu();
        String tg_ThuTien = phieuThu.getTg_ThuTien();
        String trangthaiphieu = phieuThu.getTrangthaiphieu();
        db.execSQL("INSERT INTO tbl_PhieuThu (id_khach,id_phong,dientt,nuoctt,tienthu,tg_lapphieu,tg_thu,trangthaiphieu) VALUES (?,?,?,?,?,?,?,?)",
                new String[]{idKhach +"",idPhong +"",dienTT +"",nuocTT +"",tienThu +"", tg_LapPhieu, tg_ThuTien, trangthaiphieu});
    }

    public void updatePhieu(PhieuThu phieuThu){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idPhieu = phieuThu.getIdPThu();
        int idKhach = phieuThu.getIdKhach();
        int idPhong = phieuThu.getIdPhong();
        int dienTT = phieuThu.getDienTT();
        int nuocTT = phieuThu.getNuocTT();
        int tienThu = phieuThu.getTienThu();
        String tg_LapPhieu = phieuThu.getTg_Lapphieu();
        String tg_ThuTien = phieuThu.getTg_ThuTien();
        String trangthaiphieu = phieuThu.getTrangthaiphieu();
        db.execSQL("UPDATE tbl_PhieuThu SET id_khach = ?, id_phong = ?, dientt = ?, nuoctt = ?,tienthu = ?, tg_lapphieu = ?, tg_thu = ?, trangthaiphieu = ? WHERE id_pthu = ?",
                new String[]{idKhach +"",idPhong +"",dienTT +"",nuocTT +"",tienThu +"", tg_LapPhieu, tg_ThuTien, trangthaiphieu,idPhieu + ""});
    }

    public void deletePhieu(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM tbl_PhieuThu where id_pthu = ?", new String[]{String.valueOf(id)});
    }
}
