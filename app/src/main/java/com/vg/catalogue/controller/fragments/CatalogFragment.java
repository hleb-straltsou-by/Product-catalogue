package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;

public class CatalogFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_catalog, container, false);

        FragmentManager fm = getActivity().getSupportFragmentManager();

        // add all fragments to fragment_container in transaction
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fragment_catalog_container , MiniSearchFragment.newInstance());
        transaction.add(R.id.fragment_catalog_container , ProductListFragment.newInstance(
                MainActivity.filterPatternNameParam));
        transaction.commit();

        return v;
    }
}
