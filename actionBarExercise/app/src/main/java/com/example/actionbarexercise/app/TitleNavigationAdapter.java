package com.example.actionbarexercise.app;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.actionbarexercise.app.model.Spin;

import java.util.List;

public class TitleNavigationAdapter extends BaseAdapter {

    private TextView txtTitle;
    private List<Spin> spin;
    private Context context;

    public TitleNavigationAdapter(Context context,
                                  List<Spin> spin) {
        this.spin = spin;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spin.size();
    }

    @Override
    public Object getItem(int index) {
        return spin.get(index);
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
        txtTitle.setText(""+ spin.get(position).getId());
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
        txtTitle.setText(""+ spin.get(position).getId());
        return convertView;
    }

}
