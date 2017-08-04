package com.vg.catalogue.controller.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.vg.catalogue.R;
import com.vg.catalogue.controller.fragments.CatalogFragment;
import com.vg.catalogue.controller.fragments.ContactsFragment;
import com.vg.catalogue.controller.fragments.FullSearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_FRAGMENT_CLASS = "fragment_class";

    private static final int NAVBAR_CATALOG_TAB_POSITION = 0;

    private static final int NAVBAR_FULL_SEARCH_TAB_POSITION = 1;

    private static final int NAVBAR_CONTACTS_TAB_POSITION = 2;

    private static final int NAVBAR_DEFAULT_TAB_POSITION = 0;

    private Fragment mFragment;

    public static Intent newIntent(Context context, Class<? extends Fragment> fragmentClass){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_FRAGMENT_CLASS, fragmentClass);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_with_bottom_navbar);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Class clazz = (Class) getIntent().getSerializableExtra(KEY_FRAGMENT_CLASS);
        try {
            mFragment = (Fragment) clazz.newInstance();
            transaction.add(R.id.single_fragment_container_with_bottom_navbar, mFragment);
            transaction.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.selectTabAtPosition(defineTabPosition());
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Intent intent;
                switch (tabId){
                    case R.id.tab_catalog:
                        if(mFragment.getClass() != CatalogFragment.class) {
                            intent = MainActivity.newIntent(MainActivity.this, CatalogFragment.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.tab_search:
                        if(mFragment.getClass() != FullSearchFragment.class) {
                            intent = MainActivity.newIntent(MainActivity.this, FullSearchFragment.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.tab_contacts:
                        if(mFragment.getClass() != ContactsFragment.class) {
                            intent = MainActivity.newIntent(MainActivity.this, ContactsFragment.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        });
    }

    private int defineTabPosition(){
        int tabPosition = NAVBAR_DEFAULT_TAB_POSITION;
        if(mFragment.getClass() == CatalogFragment.class){
            tabPosition = NAVBAR_CATALOG_TAB_POSITION;
        } else if(mFragment.getClass() == FullSearchFragment.class){
            tabPosition = NAVBAR_FULL_SEARCH_TAB_POSITION;
        } else if (mFragment.getClass() == ContactsFragment.class){
            tabPosition = NAVBAR_CONTACTS_TAB_POSITION;
        }
        return tabPosition;
    }
}
