package com.vg.catalogue.controller.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.vg.catalogue.R;

public class MenuFragment extends Fragment{

    private ImageButton catalogButton;

    private ImageButton searchButton;

    private ImageButton contactsButton;

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        catalogButton = (ImageButton) v.findViewById(R.id.menu_item_search_button);
        catalogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        searchButton = (ImageButton) v.findViewById(R.id.menu_item_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        contactsButton = (ImageButton) v.findViewById(R.id.menu_item_contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });

        return v;
    }
}
