package com.smart_app.smartapp.dashboard.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.smart_app.smartapp.R;
import com.smart_app.smartapp.changepassword.fragment.ChangePasswordFragment;
import com.smart_app.smartapp.fragmentsdemo.activity.FragmentMainActivity;
import com.smart_app.smartapp.fragmentsdemo.adapter.CitiesAdapter;
import com.smart_app.smartapp.fragmentsdemo.adapter.CountriesAdapter;
import com.smart_app.smartapp.fragmentsdemo.fragment.CitiesFragment;
import com.smart_app.smartapp.fragmentsdemo.fragment.CountriesFragment;
import com.smart_app.smartapp.fragmentsdemo.fragment.PopulationFragment;
import com.smart_app.smartapp.fragmentsdemo.model.Cities;
import com.smart_app.smartapp.login.activity.LoginActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity implements CountriesAdapter.OnCountrySelectedListener,CitiesAdapter.OnSelectedCityListner {

    private DrawerLayout drawerLayout;
    FrameLayout contentView;
    LinearLayout fragmentsLayout;
    String population = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentView = (FrameLayout) findViewById(R.id.contentView);
        fragmentsLayout = (LinearLayout) findViewById(R.id.fragmentsLayout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(DashboardActivity.this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(drawerLayout.isDrawerOpen(Gravity.START)){
                    drawerLayout.closeDrawer(Gravity.START);
                }
                switch (item.getItemId()){
                    case R.id.menu_change_password:
                        displayFragment();
                        return true;
                    case R.id.menu_logout:
                        openLogoutDialog();
                        return true;
                    case R.id.menu_fragments:
                        contentView.setVisibility(View.GONE);
                        fragmentsLayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.countriesContainer,new CountriesFragment()).
                                commit();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void openLogoutDialog() {

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(getResources().getString(R.string.logout_dialog_title));
        alertDialog.setMessage(getResources().getString(R.string.logout_dialog_message));

        alertDialog.setPositiveButton(getResources().getString(R.string.logout_dialog_positive_button_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton(getResources().getString(R.string.logout_dialog_negative_dialog_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        alertDialog.show();
    }

    private void displayFragment() {
        getSupportFragmentManager().beginTransaction().
                replace(R.id.contentView,new ChangePasswordFragment()).
                commit();
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onCountrySelected(String country) {
        ArrayList<Cities> cities = new ArrayList<>();
        switch (country){
            case "India" :
                cities.add(new Cities("Mumbai"));
                cities.add(new Cities("Pune"));
                cities.add(new Cities("Surat"));
                break;
            case "Australia" :
                cities.add(new Cities("Melbourn"));
                cities.add(new Cities("Sydney"));
                break;
            case "USA" :
                cities.add(new Cities("New York"));
                cities.add(new Cities("Boston"));
                break;

        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.citiesContainer, CitiesFragment.newInstance(cities))
                .commit();
    }

    @Override
    public void onCitySelected(String cities) {


        if(cities.equals("Mumbai")){
            population = "1000000000";
        }else if(cities.equals("Pune")){
            population = "7500000000";
        }else if(cities.equals("Surat")){
            population = "5000000000";
        }else if(cities.equals("Melbourn")){
            population = "2500000000";
        }else if(cities.equals("Sydney")){
            population = "25000000";
        }else if(cities.equals("New York")){
            population = "50000000";
        }else if(cities.equals("Boston")){
            population = "75000000";
        }

        getSupportFragmentManager().beginTransaction().
                replace(R.id.populationContainer, PopulationFragment.newInstance(population)).
                commit();


    }
}
