package com.example.projektmb4pp.fragments;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.R;
import com.example.projektmb4pp.adapter.Item;
import com.example.projektmb4pp.adapter.OrderItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class ShowcaseItemFragment extends Fragment {

    Item item;
    private SQLiteOpenHelper dbHelper;

    public ShowcaseItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_showcase_item, container, false);
    }

    private ImageView photo;
    private TextView name;
    private TextView description;
    private TextView price;
    private Spinner spinner;
    private ImageButton less;
    private ImageButton more;
    private EditText quantity;
    private int q = 1;
    private Button addToCart;
    SharedPreferences cart;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.itemShowCaseSize);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.article_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cart = view.getContext().getSharedPreferences("cart", -1);
        photo = view.findViewById(R.id.itemShowCaseImage);
        name = view.findViewById(R.id.itemShowCaseTitle);
        description = view.findViewById(R.id.itemShowCaseDescription);
        price = view.findViewById(R.id.itemShowCasePrice);
        less = view.findViewById(R.id.showcaseButtonSubstract);
        more = view.findViewById(R.id.showcaseButtonAdd);
        quantity = view.findViewById(R.id.showcaseEditTextQuantity);
        addToCart = view.findViewById(R.id.itemShowCaseBuyButton);

        SQLiteDatabase db = new DatabaseLMAO.DBHelper(getContext()).getReadableDatabase();
        item = new DatabaseLMAO.DBHelper(getContext()).getItem(new DatabaseLMAO.DBHelper(getContext()).getWritableDatabase(), getArguments().getLong("id"));

        Log.i("ItemShowcaseFragment", item.getPhoto());
        Log.i("ItemShowcaseFragment", item.getName());
        Log.i("ItemShowcaseFragment", String.valueOf(item.getPrice()));
        Log.i("ItemShowcaseFragment", item.getDesc());
        photo.setImageResource(getResources().getIdentifier(item.getPhoto(), "drawable", getContext().getPackageName()));
        name.setText(item.getName());
        price.setText(item.getPrice() + "");
        description.setText(item.getDesc());


        less.setOnClickListener(l -> {
            q = Integer.parseInt(quantity.getText().toString());
            if (q > 1) {
                quantity.setText((q - 1) + "");
            }
        });

        more.setOnClickListener(l -> {
            if(!quantity.getText().toString().equals("")) {
                q = Integer.parseInt(quantity.getText().toString());
                quantity.setText((q + 1) + "");
            } else {
                quantity.setText(1 + "");
            }
        });

        addToCart.setOnClickListener(l -> {
            if(quantity.getText().toString().equals("") || quantity.getText().toString().isEmpty()) {
                quantity.setText("1");
            }
            Gson gson = new Gson();
            String json;
            OrderItem orderItem = new OrderItem(item, Integer.parseInt(quantity.getText().toString()), spinner.getSelectedItem().toString());
            List<OrderItem> orderItems = new ArrayList<>();
            Type orderItemsType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
            if(cart.getString("cart", null) == null) {
                orderItems.add(orderItem);
                json = gson.toJson(orderItems);
                cart.edit().putString("cart", json).apply();
                Log.i("cart", "Cart: " + json);
            } else {
                json = cart.getString("cart", null);
                Log.i("cart", json);
                orderItems = gson.fromJson(json, orderItemsType);
                for (OrderItem o : orderItems) {
                    if (o.getItem().getId() == item.getId() && o.getSize().equals(spinner.getSelectedItem().toString())) {
                        o.setCount(o.getCount() + Integer.parseInt(quantity.getText().toString()));
                        json = gson.toJson(orderItems);
                        cart.edit().putString("cart", json).apply();
                        Log.i("cart", "JSON Cart: " + json);
                        Log.i("cart", "Cart: " + orderItems);
                        return;
                    }
                }
                orderItems.add(orderItem);
                json = gson.toJson(orderItems);
                cart.edit().putString("cart", json).apply();
                Log.i("cart", "JSON Cart: " + json);
                Log.i("cart", "Cart: " + orderItems);
            }
        });
    }
}