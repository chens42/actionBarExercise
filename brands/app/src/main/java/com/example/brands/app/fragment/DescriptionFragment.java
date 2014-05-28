package com.example.brands.app.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brands.app.R;
import com.example.brands.app.model.Product;

public class DescriptionFragment extends Fragment {
    public static final String FRAGMENT_ARGUMENT_PRODUCT = "product";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment_layout, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.logoImage);
        TextView textView = (TextView) view.findViewById(R.id.article);
        Bundle arguments = getArguments();
        Parcelable parcelable = arguments.getParcelable(FRAGMENT_ARGUMENT_PRODUCT);
        Product product = (Product) parcelable;
        if (product.getType().equals("car")) {
            if (product.getName().equals("ford")) {
                imageView.setImageResource(R.drawable.ford);
            }
        } else {
            if (product.getName().equals("omega SA")) {
                imageView.setImageResource(R.drawable.omega);
            }
        }
        textView.setText(product.getArticle());
        return view;
    }
}
