package com.bikeapp.bikingapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter<RowItems> {

    public CustomAdapter(Context context, ArrayList<RowItems> Rows) {
        super(context, 0, Rows);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RowItems Rows = getItem(position);


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lstlayout, parent, false);

        }

        TextView title = (TextView) convertView.findViewById(R.id.textviewlocation);
        TextView length = (TextView) convertView.findViewById(R.id.txtmiles);
        ImageView biketype = (ImageView) convertView.findViewById(R.id.imgBikeType);
        TextView desc = (TextView) convertView.findViewById(R.id.txtDesc);

        title.setText(Rows.getTitle());
        desc.setText(Rows.getDescription());
        length.setText(Rows.getLength() + " Miles");
        biketype.setImageResource(Rows.getDrawable());
        return convertView;



    }
}



