package com.example.donordarah;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.donordarah.adapter.EventAdapter;
import com.example.donordarah.model.DataBaseHelper;
import com.example.donordarah.model.EventModel;
import java.util.ArrayList;
import java.util.Map;

public class ItemList extends AppCompatActivity {
    RecyclerView recyclerEvent;
    RecyclerView.Adapter eventAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<EventModel> eventList;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DataBaseHelper(this);
        SQLiteDatabase db = this.db.getReadableDatabase();
        recyclerEvent = findViewById(R.id.list);
        eventList = new ArrayList<>();

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

        populateEventData();
    }

    private void populateEventData() {
        eventList.clear();
        Map<Integer, EventModel> eventData = db.getEvents();
        for (int i = 0; i<eventData.size(); i++){
            EventModel model = new EventModel();
            model = eventData.get(i);

            eventList.add(model);
            eventAdapter = new EventAdapter(eventList);
            recyclerEvent.setNestedScrollingEnabled(false);
            recyclerEvent.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(ItemList.this, RecyclerView.VERTICAL, false);
            recyclerEvent.setLayoutManager(layoutManager);
            recyclerEvent.setAdapter(eventAdapter);
            eventAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateEventData();
    }
}