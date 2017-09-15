package com.smart_app.smartapp.fragmentsdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.fragmentsdemo.adapter.CitiesAdapter;
import com.smart_app.smartapp.fragmentsdemo.model.Cities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017-09-09.
 */

public class CitiesFragment extends Fragment {

    public static CitiesFragment newInstance(ArrayList<Cities> cities) {

        ArrayList<String> city = new ArrayList<>();
        Bundle bundle = new Bundle();
        for (Cities myCities:cities) {
                city.add(myCities.getCityName());
        }
        bundle.putStringArrayList("KEY_CITIES",city);
        CitiesFragment fragment = new CitiesFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cities_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        List<String> cities = bundle.getStringArrayList("KEY_CITIES");

        CitiesAdapter citiesAdapter = new CitiesAdapter(cities,getActivity());
        RecyclerView citiesRecyclerView = (RecyclerView) view.findViewById(R.id.citiesRecyclerView);
        citiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        citiesRecyclerView.setAdapter(citiesAdapter);
        citiesAdapter.notifyDataSetChanged();


    }
}
