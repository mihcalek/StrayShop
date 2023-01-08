package com.example.projektmb4pp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    ClientData clientData;

    private Button logout;
    SharedPreferences loggedInAs;
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
        logout = view.findViewById(R.id.profileLogoutButton);

        loggedInAs = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        long accountID = loggedInAs.getLong("accountID", -1);
        Log.i("czyZalogowany", accountID + "");

        SQLiteDatabase db = new DatabaseLMAO.DBHelper(getContext()).getReadableDatabase();
        clientData = new DatabaseLMAO.DBHelper(getContext()).getClientData(db, accountID);

        if (accountID != -1) {
            name.setText("Imie: " + clientData.getName());
            surname.setText("Nazwisko: " + clientData.getSurname());
            dob.setText("Data urodzenia: " + clientData.getDateOfBirth());
            pnumber.setText("Numer telefonu: " + clientData.getPhoneNumber());
        }
        logout.setOnClickListener(p -> {
            loggedInAs.edit().putLong("accountID", -1).apply();
            Navigation.findNavController(view).navigate(R.id.pagerLoginFragment);
        });
    }
}