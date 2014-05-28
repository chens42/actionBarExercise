package com.example.brands.app.adpter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.brands.app.R;
import com.example.brands.app.model.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private final List<Product> products;
    private final Activity context;

    public ProductAdapter(Activity context, List<Product> products) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) context.getLayoutInflater().inflate(R.layout.row, null);
        textView.setText(products.get(position).getName());
        return textView;
    }
}
