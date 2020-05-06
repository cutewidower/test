package com.example.donordarah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import java.util.ArrayList;


public class ItemList extends AppCompatActivity {

    String[] daftar,daftar2 ,daftar3;
    protected Cursor javacursor;
    DataBaseHelper dbcenter;
    List<RowItem> rowItems;
    Integer images[] = {R.drawable.ic_005_calendar_15};
    public static ItemList il;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        il = this;
        dbcenter = new DataBaseHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();

        Button tam = findViewById(R.id.tambah);
        tam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemList.this,AddEvent.class));
            }
        });

        Button stok = findViewById(R.id.stok);
        stok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemList.this, Stock.class);
                startActivity(intent);
            }
        });
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        javacursor = db.rawQuery("SELECT * FROM event", null);
        daftar = new String[javacursor.getCount()];
        daftar2 = new String[javacursor.getCount()];

        for (int cc = 0; cc < javacursor.getCount(); cc++) {
            javacursor.moveToPosition(cc);
            daftar[cc] = javacursor.getString(1).toString();
            daftar2[cc] = javacursor.getString(0).toString();

        }


        ListView javaListView = (ListView) findViewById(R.id.list);
        javaListView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        javaListView.setSelected(true);

        javaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar2[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Event", "Ubah Event", "Hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemList.this);
                builder.setTitle("Pilihan Menu");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatEvent.class);
                                i.putExtra("nomor", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), Ubah.class);
                                in.putExtra("nomor", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from event where nomor = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) javaListView.getAdapter()).notifyDataSetInvalidated();
    }
}