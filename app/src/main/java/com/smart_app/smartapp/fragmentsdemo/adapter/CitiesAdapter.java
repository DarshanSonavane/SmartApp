package com.smart_app.smartapp.fragmentsdemo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.fragmentsdemo.model.Cities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017-09-09.
 */

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private List<String> cities;
    Context context;

    public CitiesAdapter(List<String> cities, Context context) {
            this.cities = cities;
            this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_custom_row_layout,parent,false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder,final int position) {
        holder.citiesTextView.setText(cities.get(position));
        final OnSelectedCityListner onSelectedCityListner = (OnSelectedCityListner) context;
        holder.citiesTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Selected Country Is:"+cities.get(position),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context,"Selected Country Is:"+countries.getCountryName(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                if(cities.get(position).equals("Mumbai")){
                    onSelectedCityListner.onCitySelected("Mumbai");
                }else if(cities.get(position).equals("Pune")){
                    onSelectedCityListner.onCitySelected("Pune");
                }else if(cities.get(position).equals("Surat")){
                    onSelectedCityListner.onCitySelected("Surat");
                }else if(cities.get(position).equals("Melbourn")){
                    onSelectedCityListner.onCitySelected("Melbourn");
                }else if(cities.get(position).equals("Sydney")){
                    onSelectedCityListner.onCitySelected("Sydney");
                }else if(cities.get(position).equals("New York")){
                    onSelectedCityListner.onCitySelected("New York");
                }else if(cities.get(position).equals("Boston")){
                    onSelectedCityListner.onCitySelected("Boston");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder{
        TextView citiesTextView;
        public CitiesViewHolder(View itemView) {
            super(itemView);

            citiesTextView = (TextView) itemView.findViewById(R.id.citiesTextView);
        }
    }

    public interface OnSelectedCityListner{
        void onCitySelected(String city);
    }
}
