package com.example.nhatro247.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhatro247.Data.DatabaseHelper;
import com.example.nhatro247.Model.User;
import com.example.nhatro247.fragments.DoiPassFragment;

public class UserDAO {
    private DatabaseHelper dbHelper;

    public UserDAO(Context context){dbHelper = new DatabaseHelper(context);}
    public UserDAO(DoiPassFragment context){dbHelper = new DatabaseHelper(context.getContext());}

    // kiemr tra tai khoan
    public User CheckUser(String username,String pass){
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user WHERE username = ? AND pass =?", new String[]{username,pass});
        if (cursor.moveToFirst()){
            user = new User();
            user.setIdUser(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPass(cursor.getString(2));
        }
        cursor.close();
        return user;
    }

    public User getUserById(int id){
        User user = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_user WHERE id_user = ?",new String[]{String.valueOf(id)});
        if (cursor.moveToFirst()){
            user = new User();
            user.setIdUser(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setPass(cursor.getString(2));
        }
        cursor.close();
        return user;
    }
    public void updateUser(User user){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int idUser = user.getIdUser();
        String user_name = user.getUsername();
        String pass = user.getPass();
        db.execSQL("UPDATE tbl_user SET  username = ?, pass = ? WHERE id_user = ?",
                new String[]{user_name,pass,idUser +"" });
    }
}
