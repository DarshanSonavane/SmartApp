package com.smart_app.smartapp.fragmentsdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.fragmentsdemo.fragment.CountriesFragment;

public class FragmentMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.countriesContainer,new CountriesFragment()).
                commit();
    }
}
