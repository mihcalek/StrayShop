package com.example.projektmb4pp.fragments.login;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText phoneEditText;
    private EditText dobEditText;
    private MaterialButton registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailEditText =  view.findViewById(R.id.email_edit_text);
        passwordEditText = view.findViewById(R.id.password_edit_text);
        confirmPasswordEditText = view.findViewById(R.id.confirm_password_edit_text);
        registerButton = view.findViewById(R.id.register_button);
        nameEditText = view.findViewById(R.id.name_edit_text);
        surnameEditText = view.findViewById(R.id.surname_edit_text);
        phoneEditText = view.findViewById(R.id.phone_edit_text);
        dobEditText = view.findViewById(R.id.DoB_edit_text);

        SQLiteOpenHelper sqliteOpenHelper = new DatabaseLMAO.DBHelper(getActivity());
        SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();

        sharedPreferences = view.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);

        registerButton.setOnClickListener(l -> {

            String e = emailEditText.getText().toString();
            String p = passwordEditText.getText().toString();
            Log.i("db", e + " " + p);
            Cursor cursor = db.query("Account", new String[]{"_id", "email"}, "email=?", new String[]{e}, null, null, null);
            Log.i("db", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();

            if(nameEditText.getText().toString().isEmpty() || surnameEditText.getText().toString().isEmpty() || dobEditText.getText().toString().isEmpty() || phoneEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty() || confirmPasswordEditText.getText().toString().isEmpty()){
                Toast.makeText(view.getContext(), "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            } else if(!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())){
                Toast.makeText(view.getContext(), "Hasła się nie zgadzają", Toast.LENGTH_SHORT).show();
            } else if(cursor.getCount() > 0){
                Toast.makeText(view.getContext(), "Konto już istnieje", Toast.LENGTH_SHORT).show();
                cursor.close();
            } else {
                cursor.close();
                ContentValues values = new ContentValues();
                values.put("name", nameEditText.getText().toString());
                values.put("surname", surnameEditText.getText().toString());
                values.put("date_of_birth", dobEditText.getText().toString());
                values.put("telephone_number", phoneEditText.getText().toString());
                Log.i("iLoveLife", values.toString());
                long newRowId = db.insert("Client", null, values);
                Log.i("iLoveLife", "onViewCreated: " + newRowId);
                values = new ContentValues();
                values.put("id_client", newRowId);
                values.put("email", emailEditText.getText().toString());
                values.put("password", passwordEditText.getText().toString());
                long accountID = db.insert("Account", null, values);
                if(accountID != -1){
                    sharedPreferences.edit().putLong("accountID", accountID).apply();
                    Navigation.findNavController(view).navigate(R.id.pagerLoginFragment);
                } else {
                    Toast.makeText(view.getContext(), "email już istnieje", Toast.LENGTH_SHORT).show();
                    db.delete("Client", "id_client = ?", new String[]{String.valueOf(newRowId)});
                }
            }
        });
    }
}