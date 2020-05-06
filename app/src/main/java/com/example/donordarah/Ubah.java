package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Ubah extends AppCompatActivity {
    protected Cursor cursor;
    DataBaseHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3,text4;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        dbHelper = new DataBaseHelper(this);
        text1 = (EditText) findViewById(R.id.nomor);
        text2 = (EditText) findViewById(R.id.ubahlokasi);
        text3 = (EditText) findViewById(R.id.ubahdate);
        text4 = (EditText) findViewById(R.id.ubahruangan);
        ton1 = (Button) findViewById(R.id.ubah);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(Ubah.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text3.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM event WHERE nomor = '" +
                getIntent().getStringExtra("nomor") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());

        }text1.setEnabled(false);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text1.getText().toString().isEmpty()||text2.getText().toString().isEmpty()||text3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isi semua Field", Toast.LENGTH_LONG).show();
                }else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("update event set tempat ='" +
                            text2.getText().toString() + "',tanggal ='" +
                            text3.getText().toString() + "',desk ='" +
                            text4.getText().toString() + "' where nomor ='" +
                            text1.getText().toString() + "'");

                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                    ItemList.il.RefreshList();
                    finish();
                }
            }
        });


    }
}
