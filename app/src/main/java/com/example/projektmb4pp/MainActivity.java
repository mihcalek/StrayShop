package com.example.projektmb4pp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.optionHome);
    }

    RegisterFragment registerFragment = new RegisterFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.optionHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.lol, registerFragment).commit();
                return true;

//            case R.id.optionAccount:
//                getSupportFragmentManager().beginTransaction().replace(R.id.navigationView, secondFragment).commit();
//                return true;
//
//            case R.id.optionCart:
//                getSupportFragmentManager().beginTransaction().replace(R.id.navigationView, thirdFragment).commit();
//                return true;
//
//            case R.id.optionSettings:
//                getSupportFragmentManager().beginTransaction().replace(R.id.navigationView, thirdFragment).commit();
//                return true;
        }
        return false;
    }
}
