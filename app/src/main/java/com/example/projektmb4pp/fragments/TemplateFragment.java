package com.example.projektmb4pp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.projektmb4pp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TemplateFragment extends Fragment {

    public TemplateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_template, container, false);

        final BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navigation);

        return view;
    }
}