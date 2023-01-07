package com.example.projektmb4pp.fragments.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;



public class LoginFragment extends Fragment {
    public LoginFragment() {
    }

    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private MaterialButton nextButton;
    private MaterialButton registerButton;
    private SQLiteOpenHelper dbHelper;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflater łączący xml fragment_login i z contenerem w activity
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginEditText = view.findViewById(R.id.login_edit_text_login);
        passwordEditText = view.findViewById(R.id.password_edit_text_login);
        nextButton = view.findViewById(R.id.login_button);
        registerButton = view.findViewById(R.id.register_button_to_fragment);

        dbHelper = new DatabaseLMAO.DBHelper(getActivity());
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        nextButton.setOnClickListener(l -> {
            String e = loginEditText.getText().toString();
            String p = passwordEditText.getText().toString();
            Log.i("db", e + " " + p);
            Cursor cursor = db.query("Account", new String[]{"_id", "email", "password"}, "email=? AND password=?", new String[]{e, p}, null, null, null);
            Log.i("db", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
            if (cursor.getCount() == 1){
                Toast.makeText(view.getContext(), "Correct", Toast.LENGTH_SHORT).show();
                sharedPreferences = this.requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                sharedPreferences.edit().putLong("accountID", cursor.getLong(0)).apply();
            } else {
                Toast.makeText(this.getContext(), "Incorrect", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.registerFragment2);
            }
        });

        return view;
    }

    //Ustawia warunki poprawnego hasła
    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }
}
