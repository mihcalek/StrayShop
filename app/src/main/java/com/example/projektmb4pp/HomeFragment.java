package com.example.projektmb4pp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
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

    FragmentContainerView fragmentContainerView;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bottomNavigationView = view.findViewById(R.id.bottom_navigation);
        fragmentContainerView = view.findViewById(R.id.nav_home_fragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.optionHome:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.showcaseFragment);
                        return true;

                    case R.id.optionAccount:
                        Navigation.findNavController(fragmentContainerView).navigate(R.id.loginFragment2);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


//        FragmentManager fragmentManager = getChildFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.nav_home_fragment)

//        FragmentContainerView
        NavHostFragment navHostFragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.nav_home_fragment);
        NavController navController = navHostFragment.getNavController();


//        View viewChild = inflater.


        return view;
    }
}