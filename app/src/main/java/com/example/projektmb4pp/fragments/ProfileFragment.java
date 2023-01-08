package com.example.projektmb4pp.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projektmb4pp.R;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.adapter.*;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private TextView name;
    private TextView surname;
    private TextView dob;
    private TextView pnumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.profileName);
        surname = view.findViewById(R.id.profileSurname);
        dob = view.findViewById(R.id.profileDob);
        pnumber = view.findViewById(R.id.profilePhoneNumber);

        SQLiteDatabase db = new DatabaseLMAO.DBHelper(getContext()).getReadableDatabase();
        ClientData clientData = new DatabaseLMAO.DBHelper(getContext()).getClientData(new DatabaseLMAO.DBHelper(getContext()).getWritableDatabase(), getArguments().getLong("id"));

        Log.i("ClientData", clientData.getName());
        Log.i("ClientData", clientData.getSurname());
        Log.i("ClientData", clientData.getDateOfBirth());
        Log.i("ClientData", clientData.getPhoneNumber());
    }
}