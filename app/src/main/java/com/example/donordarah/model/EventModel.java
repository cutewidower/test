package com.example.donordarah.model;

import android.widget.ImageView;

import java.util.Date;

public class EventModel {
    private String id;
    private int icon;
    private String location;
    private Date date;
    private String description;

    public EventModel() {
    }

    public EventModel(String id, int icon, String location, Date date, String description) {
        this.id = id;
        this.icon = icon;
        this.location = location;
        this.date = date;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
