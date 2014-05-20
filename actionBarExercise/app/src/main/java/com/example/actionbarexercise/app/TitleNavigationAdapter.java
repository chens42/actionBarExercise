package com.example.actionbarexercise.app;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TitleNavigationAdapter extends BaseAdapter {

    private ImageView imgIcon;
    private TextView txtTitle;
    private ArrayList<SpinnerItem> spinnerItemNavItem;
    private Context context;

    public TitleNavigationAdapter(Context context,
                                  ArrayList<SpinnerItem> spinnerItemNavItem) {
        this.spinnerItemNavItem = spinnerItemNavItem;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spinnerItemNavItem.size();
    }

    @Override
    public Object getItem(int index) {
        return spinnerItemNavItem.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_view, null);
        }

        txtTitle = (TextView) convertView.findViewById(R.id.idDisplay);
        txtTitle.setText(""+ spinnerItemNavItem.get(position).getIdentify());
        return convertView;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.spinner_view, null);
        }

        txtTitle = (TextView) convertView.findViewById(R.id.idDisplay);
        txtTitle.setText(""+ spinnerItemNavItem.get(position).getIdentify());
        return convertView;
    }

}
