package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vg.catalogue.R;

public class MiniSearchFragment extends Fragment {

    private EditText mSearchEditText;

    public static MiniSearchFragment newInstance() {
        return new MiniSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mini_search, container, false);

        mSearchEditText = (EditText) v.findViewById(R.id.search_edit_text);

        return v;
    }
}