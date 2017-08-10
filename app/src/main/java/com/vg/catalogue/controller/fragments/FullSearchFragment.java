package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;

public class FullSearchFragment extends Fragment {

    private EditText mCultureEditText;

    private EditText mHarmfulOrganismDiseaseEditText;

    private EditText mAllNamesEditText;

    private EditText mActiveSubstanceEditText;

    private ImageButton mSearchImageButton;

    public static String culture = "";

    public static String harmfulOrganism = "";

    public static String allNames = "";

    public static String activeSubstance = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_search, container, false);

        mCultureEditText = (EditText) v.findViewById(R.id.search_culture_edit_text);

        mHarmfulOrganismDiseaseEditText = (EditText) v.findViewById(
                R.id.search_harmful_organism_disease_edit_text);

        mAllNamesEditText = (EditText) v.findViewById(R.id.search_all_names_edit_text);

        mActiveSubstanceEditText = (EditText) v.findViewById(R.id.search_active_substance);

        mSearchImageButton = (ImageButton) v.findViewById(R.id.search_image_button);
        mSearchImageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                culture = mCultureEditText.getText().toString();
                harmfulOrganism = mHarmfulOrganismDiseaseEditText.getText().toString();
                allNames = mAllNamesEditText.getText().toString();
                activeSubstance = mActiveSubstanceEditText.getText().toString();
                ProductListFragment.setFullSearchPerformed(true);
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
