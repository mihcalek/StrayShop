package com.example.projektmb4pp.fragments.login;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflater łączący xml fragment_login i z contenerem w activity
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        final TextInputEditText loginEditText = view.findViewById(R.id.login_edit_text);
        final TextInputLayout loginTextInput = view.findViewById(R.id.login_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        MaterialButton nextButton = view.findViewById(R.id.login_button);
        MaterialButton registerButton = view.findViewById(R.id.register_button);


        SQLiteOpenHelper sqliteOpenHelper = new DatabaseLMAO.DBHelper(getActivity());
        SQLiteDatabase db = sqliteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Sprawdza czy haslo spelnia warunki (nie -> error) || (tak -> przenosi do fragmentu)
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(getString(R.string.welcome_error_password));
                } else {
                    passwordTextInput.setError(null); // Usuwa błąd
                    Navigation.findNavController(view).navigate(R.id.registerFragment2);
                }
            }
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
