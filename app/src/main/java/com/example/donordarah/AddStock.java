package com.example.donordarah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.donordarah.model.DataBaseHelper;

public class AddStock extends AppCompatActivity {
    protected Cursor cursor;
    DataBaseHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);
        dbHelper = new DataBaseHelper(this);
        text1 = (EditText) findViewById(R.id.ET_a);
        text2 = (EditText) findViewById(R.id.ET_ab);
        text3 = (EditText) findViewById(R.id.ET_b);
        text4 = (EditText) findViewById(R.id.ET_o);
        ton1 = (Button) findViewById(R.id.save);




        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if(text1.getText().toString().isEmpty()||text2.getText().toString().isEmpty()||text3.getText().toString().isEmpty()||
                        text4.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Isi semua Field", Toast.LENGTH_LONG).show();
                }else {
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    int sa = 0;
                    int sab = 0;
                    int sb = 0;
                    int so = 0;
                    cursor = db.rawQuery("SELECT * FROM stok WHERE nomor =1",null);
                    cursor.moveToFirst();
                    if (cursor.getCount()>0)
                    {
                        cursor.moveToPosition(0);
                        sa =cursor.getInt(1);
                        sab =cursor.getInt(2);
                        sb =cursor.getInt(3);
                        so =cursor.getInt(4);

                    }



                    int a = Integer.parseInt(text1.getText().toString());
                    int sumA = sa+a;
                    int ab = Integer.parseInt(text2.getText().toString());
                    int sumAb = sab+ab;
                    int b = Integer.parseInt(text3.getText().toString());
                    int sumB = sb+b;
                    int o = Integer.parseInt(text4.getText().toString());
                    int sumO = so+o;

                    SQLiteDatabase da = dbHelper.getWritableDatabase();
                    da.execSQL("Update stok set  golA ='"+sumA+"', golAb='"+sumAb+"' ,golB='"+sumB+"', golO='"+sumO+"' where nomor = '1'");
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddStock.this, Stock.class);
                    startActivity(intent);


                }
            }
        });


    }
    }

