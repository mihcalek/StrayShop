package com.example.projektmb4pp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projektmb4pp.R;
import com.example.projektmb4pp.adapter.OrderAdapter;
import com.example.projektmb4pp.DatabaseLMAO;


public class OrderHistoryFragment extends Fragment {

    RecyclerView recyclerView;
    SharedPreferences sharedPreferences;
    private DatabaseLMAO.DBHelper dbHelper;
    private SQLiteDatabase db;

    public OrderHistoryFragment() {
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
        return inflater.inflate(R.layout.fragment_order_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        long accountID = sharedPreferences.getLong("accountID", -1);

        dbHelper = new DatabaseLMAO.DBHelper(getContext());
        db = dbHelper.getWritableDatabase();

        recyclerView = view.findViewById(R.id.historyRecycler);
        recyclerView.setAdapter(new OrderAdapter(getContext(), db, accountID));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
}