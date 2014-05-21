package com.example.actionbarexercise.app.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.actionbarexercise.app.R;

public class TabFragment extends Fragment {

    private LinearLayout rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = (LinearLayout) inflater.inflate(R.layout.fragment_one, container, false);

        return rootView;
    }

    public void addTextView(TextView textView) {
        rootView.addView(textView);
    }
}
