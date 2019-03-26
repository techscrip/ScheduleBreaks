package com.example.schedulebreaks;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="Schedulebreaks.db";
    public static final String TABLE_NAME="Schedulebreaks";
    public static final String COL1="ID";
    public static final String COL2="USERNAME";
    public static final String COL3="PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Schedulebreaks(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME",user);
        cv.put("PASSWORD",password);
        long res = db.insert("Schedulebreaks",null,cv);
        db.close();
        return res;
    }

    public boolean checkUser(String username, String passwored){
        String[] columns = { COL1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL2 + "=?" + " and " + COL3 + "=?";
        String[] selectionArgs = {username,passwored};
        Cursor cursor = db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count>0){
            return true;
        }
        else
            return false;
    }
}
