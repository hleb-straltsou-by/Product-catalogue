package com.vg.catalogue.controller.fragments;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.vg.catalogue.R;

public class BottomAdFragment extends Fragment {

    private AdView mAdView;

    private static final String BOTTOM_AD_VIEW_UNIT_ID = "bottom_ad_view";

    public static BottomAdFragment newInstance() {
        return new BottomAdFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bottom_ad, container, false);

        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        mAdView = (AdView) v.findViewById(R.id.bottom_ad_view);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId(BOTTOM_AD_VIEW_UNIT_ID);
        mAdView.setBackgroundResource(R.drawable.background_ad);

        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Optionally populate the ad request builder.
        adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

        // Start loading the ad.
        mAdView.loadAd(adRequestBuilder.build());

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Resume the AdView.
        mAdView.resume();
    }

    @Override
    public void onPause() {
        // Pause the AdView.
        mAdView.pause();

        super.onPause();
    }

    @Override
    public void onDestroy() {
        // Destroy the AdView.
        mAdView.destroy();

        super.onDestroy();
    }
}
