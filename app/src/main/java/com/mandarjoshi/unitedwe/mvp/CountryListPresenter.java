package com.mandarjoshi.unitedwe.mvp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.mandarjoshi.unitedwe.service.CountryListTask;

/**
 * Created by mandarjoshi on 7/28/17.
 */

public class CountryListPresenter implements ICountryListPresenter{

    private ICountryListView  mICountryListView;

    public CountryListPresenter(ICountryListView  iCountryListView){
        this.mICountryListView = iCountryListView;
    }

    @Override
    public void loadCountryList(AppCompatActivity mActivity, RecyclerView mRecyclerView, boolean mTwoPane, ProgressBar progressBar) {
        CountryListTask listTask = new CountryListTask(mActivity,mRecyclerView,mTwoPane,progressBar);
        listTask.execute();

    }
}
