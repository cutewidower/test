package com.example.donordarah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final static  String DATABASE_NAME = "donor.db";
    private final static int DATABASE_VERSION = 1;
    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table event(nomor integer  primary key AUTOINCREMENT , tempat text null, tanggal string null, desk text null);";
        Log.d("Data", "onCreate : " + sql);
        db.execSQL(sql);

        String sql2 = "create table stok(nomor integer  primary key, golA integer null, golAb integer null, golB integer null,golO integer null);";
        Log.d("Data", "onCreate : " + sql2);
        db.execSQL(sql2);

        String sql1 = "INSERT INTO event(tempat, tanggal , desk )VALUES ('Suite Metro','26-01-2020','Lobby Apartment Pukul 09:00 s.d. 12.00');";
        db.execSQL(sql1);

        String sql3 = "INSERT INTO stok(nomor, golA , golAb ,golB, golO )VALUES ('1','20','25','28','30');";
        db.execSQL(sql3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
