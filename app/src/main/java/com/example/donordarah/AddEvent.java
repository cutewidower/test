package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    protected Cursor cursor;
    DataBaseHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3;
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        dbHelper = new DataBaseHelper(getApplicationContext());
        text1 = findViewById(R.id.lokasi);
        text2 = findViewById(R.id.date);
        text3 = findViewById(R.id.ruangan);

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AddEvent.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                text2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        ton1 =findViewById(R.id.simpan);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(text1.getText().toString().isEmpty()||text2.getText().toString().isEmpty()||text3.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isi semua Field", Toast.LENGTH_LONG).show();
                }else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into event(tempat, tanggal , desk) values('" +text1.getText().toString() + "','" +
                            text2.getText().toString() + "','" +
                            text3.getText().toString() + "')");
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                    ItemList.il.RefreshList();
                    finish();
                }
            }
        });
    }
}

