package com.smart_app.smartapp.fragmentsdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.fragmentsdemo.model.Countries;

import java.util.List;

/**
 * Created by lenovo on 2017-09-06.
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ContriesViewHolder>{

    private List<Countries> country;
    private OnCountrySelectedListener listener;
    Context context;
    Countries countries = null;

    public CountriesAdapter(List<Countries> countries, Context context) {

        this.country = countries;
        this.context = context;
    }

    @Override
    public ContriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_custom_row_layout, parent, false);
        return new ContriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ContriesViewHolder holder,final int position) {

        //countries = country.get(position);
        final OnCountrySelectedListener listener = (OnCountrySelectedListener) context;
        holder.countriesTextView.setText(country.get(position).getCountryName());
        //holder.countriesTextView.setText(countries.getCountryName());
        Log.d("","Selected_Country-----"+country.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Selected Country Is:"+country.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"Selected Country Is:"+countries.getCountryName(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                if(country.get(position).getCountryName().equals("India")){
                    listener.onCountrySelected("India");
                }else if(country.get(position).getCountryName().equals("Australia")){
                    listener.onCountrySelected("Australia");
                }else if(country.get(position).getCountryName().equals("USA")){
                    listener.onCountrySelected("USA");
                }


            }
        });
    }

    @Override
    public int getItemCount() {

        return country.size();
    }

    public class ContriesViewHolder extends RecyclerView.ViewHolder {

        TextView countriesTextView;

        public ContriesViewHolder(View itemView) {
            super(itemView);

            countriesTextView = (TextView) itemView.findViewById(R.id.contriesTextView);
        }
    }

    public interface OnCountrySelectedListener {
        void onCountrySelected(String country);
    }
}
