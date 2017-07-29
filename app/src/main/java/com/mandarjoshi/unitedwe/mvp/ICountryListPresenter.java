package com.mandarjoshi.unitedwe.mvp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

/**
 * Created by mandarjoshi on 7/28/17.
 */

public interface ICountryListPresenter {

    void loadCountryList(AppCompatActivity mActivity, RecyclerView mRecyclerView, boolean mTwoPane, ProgressBar progressBar);
}
