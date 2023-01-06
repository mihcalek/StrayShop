package com.example.projektmb4pp.fragments.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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

    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText nameEditText;
    private TextInputEditText surnameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText phoneEditText;
    private TextInputEditText dobEditText;
    private MaterialButton registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginEditText =  view.findViewById(R.id.email_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        registerButton = view.findViewById(R.id.register_button);
        nameEditText = view.findViewById(R.id.name_edit_text);
        surnameEditText = view.findViewById(R.id.surname_edit_text);
        phoneEditText = view.findViewById(R.id.phone_edit_text);
        dobEditText = view.findViewById(R.id.DoB_edit_text);

        SQLiteOpenHelper sqliteOpenHelper = new DatabaseLMAO.DBHelper(getActivity());
        SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();

        sharedPreferences = view.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        registerButton.setOnClickListener(v -> {
            Log.i("register", loginEditText.getText().toString());
            Log.i("register", passwordEditText.getText().toString());
            ContentValues values = new ContentValues();
            values.put("name", nameEditText.getText().toString());
            values.put("surname", surnameEditText.getText().toString());
            values.put("date_of_birth", dobEditText.getText().toString());
            values.put("telephone_number", phoneEditText.getText().toString());
            long newRowId = db.insert("Client", null, values);
            values = new ContentValues();
            values.put("id_client", newRowId);
            values.put("email", loginEditText.getText().toString());
            values.put("password", passwordEditText.getText().toString());
            db.insert("Account", null, values);
//            sharedPreferences.edit().putLong("accountId", accountID).apply();
//            Navigation.findNavController(view).navigate(R.id.homeFragment);
        });
    }
}