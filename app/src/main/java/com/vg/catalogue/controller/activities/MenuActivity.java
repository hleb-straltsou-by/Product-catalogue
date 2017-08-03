package com.vg.catalogue.controller.activities;

import android.support.v4.app.Fragment;
import com.vg.catalogue.controller.activities.templates.MultipleVerticalFragmentsActivity;
import com.vg.catalogue.controller.fragments.AdFragment;
import com.vg.catalogue.controller.fragments.MenuFragment;
import com.vg.catalogue.enums.SideEnum;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends MultipleVerticalFragmentsActivity {

    @Override
    protected Map<SideEnum, Fragment> createFragments() {
        Map<SideEnum, Fragment> fragmentMap =  new HashMap<>();
        fragmentMap.put(SideEnum.CENTER, MenuFragment.newInstance());
        fragmentMap.put(SideEnum.BOTTOM, AdFragment.newInstance());
        return fragmentMap;
    }
}
