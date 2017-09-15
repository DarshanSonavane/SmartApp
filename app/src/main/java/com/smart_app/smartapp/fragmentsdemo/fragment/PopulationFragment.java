package com.smart_app.smartapp.fragmentsdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smart_app.smartapp.R;

/**
 * Created by lenovo on 2017-09-09.
 */

public class PopulationFragment extends Fragment {

    public static PopulationFragment newInstance(String population) {

        Bundle bundle = new Bundle();
        bundle.putString("POPULATION",population);
        PopulationFragment fragment = new PopulationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.population_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        String population = bundle.getString("POPULATION");

        TextView populationTextView = (TextView) view.findViewById(R.id.populationTextView);

        populationTextView.setText("Population is : " + population);



    }
}
