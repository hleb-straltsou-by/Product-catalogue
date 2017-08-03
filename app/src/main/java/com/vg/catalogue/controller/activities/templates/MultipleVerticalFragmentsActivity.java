package com.vg.catalogue.controller.activities.templates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.vg.catalogue.R;
import com.vg.catalogue.enums.SideEnum;

import java.util.Map;

public abstract class MultipleVerticalFragmentsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_fragments);

        FragmentManager fm = getSupportFragmentManager();

        // add all fragments to fragment_container in transaction
        Map<SideEnum, Fragment> fragmentMap = createFragments();
        FragmentTransaction transaction = fm.beginTransaction();
        for(Map.Entry<SideEnum, Fragment> entry : fragmentMap.entrySet()){
            switch (entry.getKey()){
                case TOP:
                    transaction.add(R.id.top_container, entry.getValue());
                    break;
                case CENTER:
                    transaction.add(R.id.center_container, entry.getValue());
                    break;
                case BOTTOM:
                    transaction.add(R.id.bottom_container, entry.getValue());
                    break;
                default:
                    break;
            }
        }
        transaction.commit();
    }

    protected abstract Map<SideEnum, Fragment> createFragments();
}
