package com.vg.catalogue.controller.activities;

import android.support.v4.app.Fragment;
import com.vg.catalogue.controller.activities.templates.MultipleVerticalFragmentsActivity;
import com.vg.catalogue.controller.fragments.BottomAdFragment;
import com.vg.catalogue.controller.fragments.MainMenuFragment;
import com.vg.catalogue.enums.SideEnum;

import java.util.HashMap;
import java.util.Map;

public class MainMenuActivity extends MultipleVerticalFragmentsActivity {

    @Override
    protected Map<SideEnum, Fragment> createFragments() {
        Map<SideEnum, Fragment> fragmentMap =  new HashMap<>();
        fragmentMap.put(SideEnum.CENTER, MainMenuFragment.newInstance());
        fragmentMap.put(SideEnum.BOTTOM, BottomAdFragment.newInstance());
        return fragmentMap;
    }
}
