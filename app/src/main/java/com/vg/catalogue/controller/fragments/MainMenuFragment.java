package com.vg.catalogue.controller.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.vg.catalogue.R;
import com.vg.catalogue.controller.activities.MainActivity;

public class MainMenuFragment extends Fragment{

    private ImageButton mCatalogButton;

    private ImageButton mSearchButton;

    private ImageButton mContactsButton;

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_menu, container, false);

        mCatalogButton = (ImageButton) v.findViewById(R.id.menu_item_catalog_button);
        mCatalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newIntent(getActivity(), CatalogFragment.class);
                startActivity(intent);
            }
        });

        mSearchButton = (ImageButton) v.findViewById(R.id.menu_item_search_button);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newIntent(getActivity(), FullSearchFragment.class);
                startActivity(intent);
            }
        });

        mContactsButton = (ImageButton) v.findViewById(R.id.menu_item_contacts_button);
        mContactsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.newIntent(getActivity(), ContactsFragment.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
