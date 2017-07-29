package com.mandarjoshi.unitedwe.service;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mandarjoshi.unitedwe.adapter.CountryListAdapter;
import com.mandarjoshi.unitedwe.domain.Country;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mandarjoshi on 7/28/17.
 */

public class CountryListTask  extends AsyncTask<Void,Void,List<Country>> {

    private AppCompatActivity mActivity;
    private RecyclerView mRecyclerView;
    private boolean mTwoPane;
    private ProgressBar mProgressBar;

    public CountryListTask(AppCompatActivity activity, RecyclerView recyclerView,boolean twoPane, ProgressBar  progressBar) {

        this.mActivity = activity;
        this.mRecyclerView = recyclerView;
        this.mTwoPane = twoPane;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPostExecute(List<Country> list) {
        super.onPostExecute(list);
        mRecyclerView.setAdapter(new CountryListAdapter(list,mActivity,mTwoPane));
        mProgressBar.setVisibility(View.GONE);

    }

    @Override
    protected List<Country> doInBackground(Void... params) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://restcountries.eu/rest/v2/all")
                .get()
                .addHeader("cache-control", "no-cache")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                Gson gson = new Gson();
                return gson.fromJson(response.body().string(), new TypeToken<List<Country>>(){}.getType());

            } else {
                showMessages("Couldn't finish request. Unsuccessful response.");
            }
        }catch (Exception e){
            showMessages(e.getMessage());
        }
        return null;
    }



    private void showMessages(final String errorMessage){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mActivity,"Couldn't finish request.",Toast.LENGTH_SHORT).show();
                Log.e(mActivity.getLocalClassName(),errorMessage);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

}
