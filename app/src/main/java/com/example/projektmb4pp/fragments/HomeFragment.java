package com.example.projektmb4pp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projektmb4pp.adapter.PagerLoginAdapter;
import com.example.projektmb4pp.fragments.login.*;

import com.example.projektmb4pp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private FragmentContainerView fragmentContainerView;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        fragmentContainerView = view.findViewById(R.id.nav_home_fragment);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        toolbar = view.findViewById(R.id.toolbar);
        navigationView = view.findViewById(R.id.navigationView);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.optionHome:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.showcaseFragment);
                        return true;

                    case R.id.optionAccount:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.pagerLoginFragment);
                        return true;

                    case R.id.optionCart:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.cartFragment2);
                        return true;

                    case R.id.optionSettings:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.settingsFragment2);
                        return true;
                }
                return false;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.authorButton:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.authorFragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                }
                return false;
            }
        });

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}