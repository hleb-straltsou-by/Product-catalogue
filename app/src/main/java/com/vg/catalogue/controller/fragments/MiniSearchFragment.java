package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;

public class MiniSearchFragment extends Fragment {

    private SearchView mSearchView;

    private boolean isReadyToSearch;

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

        isReadyToSearch = false;
        mSearchView = (SearchView) v.findViewById(R.id.mini_search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                String filterPatternName = mSearchView.getQuery().toString();
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class,
                        filterPatternName);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                String filterPatternName = mSearchView.getQuery().toString();
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class,
                        filterPatternName);
                startActivity(intent);
                return true;
            }
        });

        return v;
    }
}