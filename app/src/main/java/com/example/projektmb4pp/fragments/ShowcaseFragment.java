package com.example.projektmb4pp.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.R;
import com.example.projektmb4pp.adapter.Item;
import com.example.projektmb4pp.adapter.ItemAdapter;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dbHelper = new DatabaseLMAO.DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        Item[] items = new DatabaseLMAO.DBHelper(getContext()).getItemList(db);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new ItemAdapter(items, getContext(), Navigation.findNavController(view)));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


    }
}