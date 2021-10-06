package com.crusoft.gloify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context,"Login.db",null,1);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create Table users" +
                        "(username TEXT primary key,password TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
       // cv.put("Email","jdjjd");
        //cv.put("fullname","john mem");
        //cv.put("gender","male");
        long result = MyDB.insert("users",null,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor =  MyDb.rawQuery("select * from users where username = ?",new String[] {username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamepasswd(String username,String password) {
        SQLiteDatabase MyDb = this.getWritableDatabase();
        Cursor cursor = MyDb.rawQuery("select * from users where username = ? and password = ?", new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
