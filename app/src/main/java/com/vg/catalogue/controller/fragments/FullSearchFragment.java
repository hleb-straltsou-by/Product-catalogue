package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;
import com.vg.catalogue.enums.ProductCategoryEnum;

public class FullSearchFragment extends Fragment {

    private EditText mCultureEditText;

    private EditText mHarmfulOrganismDiseaseEditText;

    private EditText mAllNamesEditText;

    private EditText mActiveSubstanceEditText;

    private AppCompatSpinner mCategorySpinner;

    public static String culture = "";

    public static String harmfulOrganism = "";

    public static String allNames = "";

    public static String activeSubstance = "";

    public static ProductCategoryEnum category = ProductCategoryEnum.HERBICIDES;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_search, container, false);

        mCultureEditText = (EditText) v.findViewById(R.id.search_culture_edit_text);
        mCultureEditText.setText(culture);

        mHarmfulOrganismDiseaseEditText = (EditText) v.findViewById(
                R.id.search_harmful_organism_disease_edit_text);
        mHarmfulOrganismDiseaseEditText.setText(harmfulOrganism);

        mAllNamesEditText = (EditText) v.findViewById(R.id.search_all_names_edit_text);
        mAllNamesEditText.setText(allNames);

        mActiveSubstanceEditText = (EditText) v.findViewById(R.id.search_active_substance);
        mActiveSubstanceEditText.setText(activeSubstance);

        Button searchButton = (Button) v.findViewById(R.id.search_image_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                ProductCategoryEnum.getProductCategoriesStrings(getActivity()));

        mCategorySpinner = (AppCompatSpinner) v.findViewById(R.id.search_category_spinner);
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
