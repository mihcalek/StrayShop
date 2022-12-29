package com.example.projektmb4pp.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.R;


public class ShowcaseFragment extends Fragment {

    private SQLiteOpenHelper dbHelper;
    private SQLiteDatabase db;
    private RecyclerView recyclerView;

    public ShowcaseFragment() {
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
        return inflater.inflate(R.layout.fragment_showcase, container, false);



    }
}