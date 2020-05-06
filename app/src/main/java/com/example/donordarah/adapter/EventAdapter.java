package com.example.donordarah.adapter;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donordarah.AddEvent;
import com.example.donordarah.ItemList;
import com.example.donordarah.LihatEvent;
import com.example.donordarah.R;
import com.example.donordarah.Ubah;
import com.example.donordarah.model.BaseModel;
import com.example.donordarah.model.DataBaseHelper;
import com.example.donordarah.model.EventModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.viewHolder> {
    private ArrayList<EventModel> list;
    private EventAdapter adapter;
    DataBaseHelper db;

    public class Base extends BaseModel{
    }

    public EventAdapter(ArrayList<EventModel> list) {
        this.list = list;
        this.adapter = this;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventAdapter.viewHolder holder, final int position) {
        final EventModel model = list.get(position);
        int icon = position;
        if (icon > 6) {
            icon %= 3;
            holder.image.setImageResource(iconDrawable[icon]);
        } else {
            holder.image.setImageResource(iconDrawable[icon]);
        }
        holder.location.setText(model.getLocation());
        holder.description.setText(model.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MMM-yyyy", Locale.getDefault());
        String strDate = dateFormat.format(model.getDate());
        holder.date.setText(strDate);

        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(holder.context, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    static class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView location, description, date;
        RelativeLayout rel;
        Context context;

        viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.event_item_image);
            location = itemView.findViewById(R.id.event_item_location);
            description = itemView.findViewById(R.id.event_item_desc);
            date = itemView.findViewById(R.id.event_item_date);
            rel = itemView.findViewById(R.id.event_item_rel);
            context = itemView.getContext();

        }
    }

    private void openDialog(final Context context, final int position){
        final String id = list.get(position).getId();
        final CharSequence[] dialogitem = {"View Event", "Edit", "Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Option");
        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent i = new Intent(context, LihatEvent.class);
                        i.putExtra("nomor", id);
                        context.startActivity(i);
                    break;
                    case 1:
                        Intent in = new Intent(context, AddEvent.class);
                        in.putExtra("id", id);
                        context.startActivity(in);
                    break;
                    case 2:
                        db = new DataBaseHelper(context);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("DELETE EVENT : "+list.get(position).getLocation()+" ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db.delEvent(id);
                                list.remove(list.get(position));
                                adapter.notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    break;
                }
            }
        });
        builder.create().show();
    }

    private final int[] iconDrawable = {
            R.drawable.ic_calendar,
            R.drawable.ic_calendar_1,
            R.drawable.ic_calendar_2,
            R.drawable.ic_calendar_3,
            R.drawable.ic_calendar_4,
            R.drawable.ic_calendar_5,
            R.drawable.ic_calendar_6
    };

}
