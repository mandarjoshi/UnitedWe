package com.mandarjoshi.unitedwe.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;


import com.mandarjoshi.unitedwe.domain.Country;
import com.mandarjoshi.unitedwe.adapter.CountryListAdapter;
import com.mandarjoshi.unitedwe.R;
import com.mandarjoshi.unitedwe.mvp.CountryListPresenter;
import com.mandarjoshi.unitedwe.mvp.ICountryListView;
import com.mandarjoshi.unitedwe.service.CountryListTask;

import java.util.ArrayList;
import java.util.List;

public class CountryListActivity extends AppCompatActivity implements ICountryListView {

    private boolean mTwoPane;
    private AppCompatActivity mActivity = this;
    private static final String TAG = "CountryListActivity";

    private CountryListPresenter countryListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.country_list_progress);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.country_list);
        assert mRecyclerView != null;
        setupRecyclerView(mRecyclerView);

        if (findViewById(R.id.country_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        countryListPresenter = new CountryListPresenter(this);

        progressBar.setVisibility(View.VISIBLE);

        countryListPresenter.loadCountryList(mActivity,mRecyclerView,mTwoPane,progressBar);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<Country> list = new ArrayList<Country>();
        recyclerView.setAdapter(new CountryListAdapter(list,this,mTwoPane));
    }

    @Override
    public void navigateToDetails() {

    }

}
