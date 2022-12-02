package com.example.projektmb4pp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        final BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.optionHome:
                        Navigation.findNavController(view).navigate(R.id.homeFragment);
                        return true;

                    case R.id.optionAccount:
                        Navigation.findNavController(view).navigate(R.id.loginFragment);
                        return true;

                    case R.id.optionCart:
                        Navigation.findNavController(view).navigate(R.id.cartFragment);
                        return true;

                    case R.id.optionSettings:
                        Navigation.findNavController(view).navigate(R.id.settingsFragment);
                        return true;
                }
                return false;
            }
        });

        return view;
    }
}