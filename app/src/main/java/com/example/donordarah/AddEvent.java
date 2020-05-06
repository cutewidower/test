package com.example.donordarah;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.donordarah.model.BaseModel;
import com.example.donordarah.model.DataBaseHelper;
import com.example.donordarah.model.EventModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddEvent extends BaseModel {
    Calendar calendar;
    DatePickerDialog.OnDateSetListener dateListener;
    DataBaseHelper db;
    MaterialButton ton1;
    TextInputEditText location, date, desc;
    String eventId = "add";
    TextView tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        db = new DataBaseHelper(getApplicationContext());
        location = findViewById(R.id.lokasi);
        date = findViewById(R.id.date);
        desc = findViewById(R.id.ruangan);
        tittle = findViewById(R.id.add_event_tittle);
        calendar = Calendar.getInstance();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                new DatePickerDialog(AddEvent.this, dateListener, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                setDate();
            }
        };

        ton1 = findViewById(R.id.simpan);
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (location.getText().toString().isEmpty() || date.getText().toString().isEmpty() || desc.getText().toString().isEmpty()) {
                    toastWarning(getApplicationContext(), "Isi semua Field");
                } else {
                    saveMode();

                }
            }
        });

        updateMode();
    }

    private void saveMode() {
        String loc = location.getText().toString();
        Date date = calendar.getTime();
        String description = desc.getText().toString();
        int ic = (int) (Math.random() * 10) + 1;

        if (eventId.equals("add")) {
            EventModel model = new EventModel("", ic, loc, date, description);
            db.addEvent(model);
        } else {
            EventModel model = new EventModel("", ic, loc, date, description);
            db.updateEvent(eventId, model);
        }
        finish();
    }

    private void updateMode() {
        eventId = getIntent().getStringExtra("id");
        EventModel model;
        if (eventId != null && !eventId.equals("add")) {
            tittle.setText("Edit Event");
            model = db.getEvent(eventId);
            calendar.setTime(model.getDate());
            setDate();
            location.setText(model.getLocation());
            desc.setText(model.getDescription());
        }
    }

    private void setDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,  dd-MMMM-yyyy", Locale.getDefault());
        date.setText(sdf.format(calendar.getTime()));
    }

}

