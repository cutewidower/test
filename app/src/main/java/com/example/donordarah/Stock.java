package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Stock extends AppCompatActivity {
    protected Cursor cursor;
    DataBaseHelper dbHelper;
    ImageButton ton2;
    TextView text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock2);

        ImageButton upd = findViewById(R.id.update);
        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stock.this, AddStock.class);
                startActivity(intent);

            }
        });
        dbHelper = new DataBaseHelper(this);
        text1 = (TextView) findViewById(R.id.a);
        text2 = (TextView) findViewById(R.id.ab);
        text3 = (TextView) findViewById(R.id.b);
        text4 = (TextView) findViewById(R.id.o);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM stok WHERE nomor =1",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(1).toString());
            text2.setText(cursor.getString(2).toString());
            text3.setText(cursor.getString(3).toString());
            text4.setText(cursor.getString(4).toString());

        }
        ton2 = (ImageButton) findViewById(R.id.back);
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                startActivity(new Intent(Stock.this,ItemList.class));
            }
        });
    }
    }

