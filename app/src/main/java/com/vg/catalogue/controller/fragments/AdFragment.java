package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vg.catalogue.R;

public class AdFragment extends Fragment {

    private TextView adTextView;

    public static AdFragment newInstance() {
        return new AdFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ad, container, false);

        adTextView = (TextView) v.findViewById(R.id.ad_text_view);
        adTextView.setText("Реклама");

        return v;
    }
}
