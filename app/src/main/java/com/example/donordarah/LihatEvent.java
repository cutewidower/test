package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.donordarah.model.DataBaseHelper;

public class LihatEvent extends AppCompatActivity {

    protected Cursor cursor;
    DataBaseHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_event);
        dbHelper = new DataBaseHelper(this);
        text1 = (TextView) findViewById(R.id.tlokasi);
        text2 = (TextView) findViewById(R.id.tdate);
        text3 = (TextView) findViewById(R.id.truangan);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM event WHERE nomor = '" +
                getIntent().getStringExtra("nomor") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
        }
        ton2 = (Button) findViewById(R.id.kembali);
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                finish();
            }
        });
    }
}
