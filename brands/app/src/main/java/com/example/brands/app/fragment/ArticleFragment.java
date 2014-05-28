package com.example.brands.app.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brands.app.R;

public class ArticleFragment extends Fragment{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment_layout,container,false);
        return view;
    }

    public void setText(String link) {
        TextView textView = (TextView) getView().findViewById(R.id.article);
        textView.setText(link);
    }
}
