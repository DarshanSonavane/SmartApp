package com.smart_app.smartapp.fragmentsdemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.fragmentsdemo.adapter.CountriesAdapter;
import com.smart_app.smartapp.fragmentsdemo.model.Countries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017-09-06.
 */

public class CountriesFragment extends android.support.v4.app.Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        rootView =  inflater.inflate(R.layout.countries_fragment,container,false);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Countries> countries = new ArrayList<>();
        countries.add(new Countries("India"));
        countries.add(new Countries("Australia"));
        countries.add(new Countries("USA"));

        CountriesAdapter countriesAdapter = new CountriesAdapter(countries,getActivity());
        RecyclerView countriesRecyclerView = (RecyclerView) view.findViewById(R.id.contriesRecyclerView);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        countriesRecyclerView.setAdapter(countriesAdapter);
        countriesAdapter.notifyDataSetChanged();

    }
}
