package com.mandarjoshi.unitedwe.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mandarjoshi.unitedwe.R;
import com.mandarjoshi.unitedwe.domain.Country;
import com.mandarjoshi.unitedwe.ui.CountryDetailActivity;
import com.mandarjoshi.unitedwe.ui.CountryDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mandarjoshi on 7/28/17.
 */

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.ViewHolder> {
    private final List<Country> mValues;
    private AppCompatActivity mActivity;
    private boolean mTwoPane;

    public CountryListAdapter(List<Country> items, AppCompatActivity activity, boolean twoPane) {
        mValues = items;
        this.mActivity = activity;
        this.mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        // holder.mIdView(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).getName()+" (" +mValues.get(position).getAlpha2Code()+")");
        String flagUrl = "http://www.geonames.org/flags/m/"+mValues.get(position).getAlpha2Code().toLowerCase()+".png";
        Picasso.with(mActivity).load(flagUrl).into(holder.mFlagView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putSerializable(CountryDetailFragment.ARG_ITEM_ID, holder.mItem);
                    CountryDetailFragment fragment = new CountryDetailFragment();
                    fragment.setArguments(arguments);
                    mActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.country_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, CountryDetailActivity.class);
                    intent.putExtra(CountryDetailFragment.ARG_ITEM_ID, holder.mItem);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mFlagView;
        public final TextView mContentView;
        public Country mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mFlagView = (ImageView) view.findViewById(R.id.country_list_flag);
            mContentView = (TextView) view.findViewById(R.id.country_list_title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
