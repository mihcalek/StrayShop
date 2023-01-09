package com.example.projektmb4pp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projektmb4pp.R;
import com.example.projektmb4pp.adapter.PagerLoginAdapter;
import com.example.projektmb4pp.fragments.login.LoginFragment;
import com.example.projektmb4pp.fragments.login.RegisterFragment;
import com.google.android.material.tabs.TabLayout;

public class PagerLoginFragment extends Fragment {

    public PagerLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_login, container, false);
    }

    private PagerLoginAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager);
        viewPagerAdapter = new PagerLoginAdapter(getChildFragmentManager());
        viewPagerAdapter.add(new LoginFragment(), getString(R.string.login));
        viewPagerAdapter.add(new RegisterFragment(), getString(R.string.register));

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}