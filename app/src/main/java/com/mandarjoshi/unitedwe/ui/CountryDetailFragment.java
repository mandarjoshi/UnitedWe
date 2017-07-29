package com.mandarjoshi.unitedwe.ui;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mandarjoshi.unitedwe.R;
import com.mandarjoshi.unitedwe.domain.Country;

public class CountryDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Country mCountry;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CountryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                mCountry = (Country) getArguments().getSerializable(ARG_ITEM_ID);
                appBarLayout.setTitle(mCountry != null?mCountry.getName():"");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.country_detail, container, false);

        ((TextView)rootView.findViewById(R.id.country_detail_code)).setText(getResources().getString(R.string.code)+": "+mCountry.getAlpha2Code());
        ((TextView)rootView.findViewById(R.id.country_details_capital)).setText(getResources().getString(R.string.capital)+": "+mCountry.getCapital());
        ((TextView)rootView.findViewById(R.id.country_detail_region)).setText(getResources().getString(R.string.region)+": "+mCountry.getRegion());
        ((TextView)rootView.findViewById(R.id.country_detail_subregion)).setText(getResources().getString(R.string.subregion)+": "+mCountry.getSubregion());
        ((TextView)rootView.findViewById(R.id.country_detail_population)).setText(getResources().getString(R.string.population)+": "+mCountry.getPopulation());

        return rootView;
    }
}
