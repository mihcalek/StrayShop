package com.example.projektmb4pp.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends Fragment {

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextInputEditText loginEditText = view.findViewById(R.id.login_edit_text);
        final TextInputLayout loginTextInput = view.findViewById(R.id.login_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        MaterialButton registerButton = view.findViewById(R.id.register_button);

        SQLiteOpenHelper sqliteOpenHelper = new DatabaseLMAO.DBHelper(getActivity());
        SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("COLUMN_EMAIL", loginEditText.getText().toString());
                contentValues.put("COLUMN_PASSWORD", passwordEditText.getText().toString());
                db.insert("Account", null, contentValues);
            }
        });
    }
}