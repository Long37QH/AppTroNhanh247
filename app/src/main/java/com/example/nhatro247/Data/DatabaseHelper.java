package com.example.nhatro247.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tronhanh.db";
    private static final int DATABASE_VERSION = 1;
    // Các câu lệnh tạo bảng
    private static final String CREATE_TABLE_PHONG = "CREATE TABLE tbl_phong (" +
            "id_phong INTEGER PRIMARY KEY," +
            "dientich INTEGER," +
            "giaphong INTEGER," +
            "sodien INTEGER," +
            "giadien INTEGER," +
            "sonuoc INTEGER," +
            "gianuoc INTEGER," +
            "trangthai TEXT);";

    private static final String CREATE_TABLE_KHACH_TRO = "CREATE TABLE tbl_KhachHang (" +
            "id_khach INTEGER PRIMARY KEY," +
            "id_phong INTEGER," +
            "ten_kh TEXT," +
            "sdt TEXT," +
            "ngayvao TEXT," +
            "FOREIGN KEY(id_phong) REFERENCES tbl_phong(id_phong));";

    private static final String CREATE_TABLE_PHIEU_THU = "CREATE TABLE tbl_PhieuThu (" +
            "id_pthu INTEGER PRIMARY KEY AUTOINCREMENT," +
            "id_khach INTEGER," +
            "id_phong INTEGER," +
            "dientt INTEGER," +
            "nuoctt INTEGER," +
            "tienthu INTEGER," +
            "tg_lapphieu TEXT," +
            "tg_thu TEXT," +
            "trangthaiphieu TEXT," +
            "FOREIGN KEY(id_khach) REFERENCES tbl_KhachHang(id_khach)," +
            "FOREIGN KEY(id_phong) REFERENCES tbl_phong(id_phong));";

    private static final String CREATE_TABLE_USER = "CREATE TABLE tbl_user (" +
            "id_user INTEGER PRIMARY KEY," +
            "username TEXT," +
            "pass TEXT);";
    private static final String insertAdminUser = "INSERT INTO tbl_user (id_user, username, pass) VALUES (1, 'admin', 'admin')";
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PHONG);
        db.execSQL(CREATE_TABLE_KHACH_TRO);
        db.execSQL(CREATE_TABLE_PHIEU_THU);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(insertAdminUser);

        // Insert default data
        String INSERT_DEFAULT_PHONG = "INSERT INTO tbl_phong (id_phong, dientich, giaphong, sodien, giadien, sonuoc, gianuoc, trangthai) " +
                "VALUES (1, 25, 300000, 0, 3000, 0, 9000, 'Phòng Trống')";
        for (int i = 2; i <= 10; i++) {
            INSERT_DEFAULT_PHONG += ", (" + i + ", 25, 300000, 0, 3000, 0, 9000, 'Phòng Trống')";
        }
        db.execSQL(INSERT_DEFAULT_PHONG);

        String INSERT_DEFAULT_KHACH_TRO = "INSERT INTO tbl_KhachHang (id_khach, id_phong, ten_kh, sdt, ngayvao) " +
                "VALUES (1, 1, 'Phòng Trống', 'Phòng Trống', 'Phòng Trống')";
        for (int i = 2; i <= 10; i++) {
            INSERT_DEFAULT_KHACH_TRO += ", (" + i + ", " + i + ", 'Phòng Trống', 'Phòng Trống', 'Phòng Trống')";
        }
        db.execSQL(INSERT_DEFAULT_KHACH_TRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
