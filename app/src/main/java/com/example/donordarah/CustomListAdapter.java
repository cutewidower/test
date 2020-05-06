package com.example.donordarah;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname,itemname2;
    private final Integer[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, String[] itemname2,Integer[] imgid) {
        super(context, R.layout.item_list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.itemname2=itemname2;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.item_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtlist);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgicon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.txtlist2);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        extratxt.setText(itemname2[position]);
        return rowView;

    };
}