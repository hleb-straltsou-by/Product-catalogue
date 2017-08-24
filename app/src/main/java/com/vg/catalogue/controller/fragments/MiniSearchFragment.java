package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;
import com.vg.catalogue.enums.ProductCategoryEnum;

public class MiniSearchFragment extends Fragment {

    private SearchView mSearchView;

    private AppCompatSpinner mCategorySpinner;

    private ProductCategoryEnum category = ProductCategoryEnum.HERBICIDES;

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

        mSearchView = (SearchView) v.findViewById(R.id.mini_search_view);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                String filterPatternName = mSearchView.getQuery().toString();
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class,
                        filterPatternName, category);
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
                        filterPatternName, category);
                startActivity(intent);
                return true;
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                ProductCategoryEnum.getProductCategoriesStrings(getActivity()));

        mCategorySpinner = (AppCompatSpinner) v.findViewById(R.id.mini_search_category_spinner);
        mCategorySpinner.setAdapter(adapter);
        mCategorySpinner.setSelection(0);
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = ProductCategoryEnum.
                        getProductCategoryAccordingSpinnerPossition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return v;
    }
}