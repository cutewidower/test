package com.example.donordarah.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final static  String DATABASE_NAME = "donor.db";
    private final static int DATABASE_VERSION = 1;
    private Context context;

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table event(id INTEGER PRIMARY KEY AUTOINCREMENT , icon INTEGER NULL, location TEXT NULL, date DATE NULL, description TEXT NULL);";
        Log.d("Data", "onCreate : " + sql);
        db.execSQL(sql);

        String sql2 = "create table stok(nomor integer  primary key, golA integer null, golAb integer null, golB integer null,golO integer null);";
        Log.d("Data", "onCreate : " + sql2);
        db.execSQL(sql2);

        String sql1 = "INSERT INTO event(icon,  location, date, description)VALUES (1,'Suites Metro - Soekarno Hatta','2020-01-26','Lobby Apartment Pukul 09:00 s.d. 12.00');";
        db.execSQL(sql1);

        String sql3 = "INSERT INTO stok(nomor, golA , golAb ,golB, golO )VALUES ('1','20','25','28','30');";
        db.execSQL(sql3);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}


    //=======================================  EVENT =============================================//
    public Map<Integer, EventModel> getEvents(){
        Map<Integer, EventModel> data = new HashMap<>();
        String query = "SELECT * FROM event ORDER BY date DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int i =0;
        while (!cursor.isAfterLast()){
            String id = cursor.getString(0);
            int icon = cursor.getInt(1);
            String location = cursor.getString(2);
            Date date = stringToDate(cursor.getString(3));
            String description = cursor.getString(4);
            EventModel model = new EventModel(id, icon, location, date, description);

            data.put(i, model);
            i++;
            cursor.moveToNext();
        }
        return data;
    }

    public EventModel getEvent(String id){
        String query = "SELECT * FROM event WHERE id ="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        int icon = cursor.getInt(1);
        String location = cursor.getString(2);
        Date date = stringToDate(cursor.getString(3));
        String description = cursor.getString(4);
        return new EventModel(id, icon, location, date, description);
    }

    public void addEvent(EventModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("icon", model.getIcon());
        values.put("location", model.getLocation());
        values.put("date", dateString(model.getDate()));
        values.put("description", model.getDescription());
        db.insert("event", null, values);
        db.close();
        Toasty.success(context, dateString(model.getDate()), Toasty.LENGTH_SHORT).show();
    }

    public void delEvent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("event", "id=?", new String[]{id});
        db.close();
        Base.toastSuccess(context, "Event DELETED");
    }

    public void updateEvent(String id, EventModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("icon", model.getIcon());
        values.put("location", model.getLocation());
        values.put("date", dateString(model.getDate()));
        values.put("description", model.getDescription());
        db.update("event", values,"id=?", new String[]{id});
        db.close();
        Base.toastSuccess(context, "Event UPDATED");
    }


    //========================================= Utility ==========================================//
    private Date stringToDate(String stringDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date d = new Date();
        try {
            d =  dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    private String dateString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return dateFormat.format(date);
    }

    private static class Base extends BaseModel{}
}
