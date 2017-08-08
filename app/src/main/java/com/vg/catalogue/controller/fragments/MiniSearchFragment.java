package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;
import com.vg.catalogue.dao.implementations.SQLiteProductDao;
import com.vg.catalogue.dao.interfaces.ProductDao;

public class MiniSearchFragment extends Fragment {

    private EditText mSearchEditText;

    private ImageView mSearchImageView;

    private ProductDao mProductDao;

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

        mProductDao = SQLiteProductDao.getInstance(getActivity().getApplicationContext());

        mSearchEditText = (EditText) v.findViewById(R.id.mini_search_edit_view);
        mSearchEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mSearchEditText.setText("");
            }
        });

        mSearchImageView = (ImageView) v.findViewById(R.id.mini_search_image_button);
        mSearchImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String filterPatternName = mSearchEditText.getText().toString();
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class,
                        filterPatternName);
                startActivity(intent);
            }
        });

        return v;
    }
}