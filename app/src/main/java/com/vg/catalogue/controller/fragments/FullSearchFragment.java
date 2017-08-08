package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.vg.catalogue.R;

public class FullSearchFragment extends Fragment {

    private EditText mCultureEditText;

    private EditText mHarmfulOrganismDiseaseEditText;

    private EditText mAllNamesEditText;

    private EditText mActiveSubstanceEditText;

    private ImageButton mSearchImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_search, container, false);

        mCultureEditText = (EditText) v.findViewById(R.id.search_culture_edit_text);
        mCultureEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCultureEditText.setText("");
            }
        });

        mHarmfulOrganismDiseaseEditText = (EditText) v.findViewById(
                R.id.search_harmful_organism_disease_edit_text);
        mHarmfulOrganismDiseaseEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCultureEditText.setText("");
            }
        });

        mAllNamesEditText = (EditText) v.findViewById(R.id.search_all_names_edit_text);
        mAllNamesEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCultureEditText.setText("");
            }
        });

        mActiveSubstanceEditText = (EditText) v.findViewById(R.id.search_active_substance);
        mActiveSubstanceEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mActiveSubstanceEditText.setText("");
            }
        });

        mSearchImageButton = (ImageButton) v.findViewById(R.id.search_image_button);
        mSearchImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
