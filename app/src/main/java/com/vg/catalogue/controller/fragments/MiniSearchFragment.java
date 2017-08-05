package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vg.catalogue.R;

public class MiniSearchFragment extends Fragment {

    private SearchView mSearchView;

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

        mSearchView = (SearchView) v.findViewById(R.id.search_view);
        mSearchView.setOnSearchClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // searching
            }
        });

        return v;
    }
}