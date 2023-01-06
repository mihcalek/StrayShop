package com.example.projektmb4pp.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.projektmb4pp.R;
import com.example.projektmb4pp.adapter.CartAdapter;
import com.example.projektmb4pp.adapter.OrderItem;
import com.example.projektmb4pp.adapter.Order;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;


public class CartFragment extends Fragment implements CartAdapter.EventListener{

    RecyclerView recyclerView;
    ArrayList<OrderItem> orderItems;
    SharedPreferences cart;
    String json;
    Gson gson;

    ImageButton cartEmpty;
    TextView cartTotal;
    ImageButton cartCheckout;

    public CartFragment() {
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.cartItems);
        recyclerView.setAdapter(new CartAdapter(requireContext(), this));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cart = getContext().getSharedPreferences("cart", Context.MODE_PRIVATE);
        json = cart.getString("cart", "");
        gson = new Gson();
        Type orderItemsType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
        orderItems = gson.fromJson(json, orderItemsType);

        cartEmpty = view.findViewById(R.id.cartEmpty);
        cartTotal = view.findViewById(R.id.cartTotal);
        cartCheckout = view.findViewById(R.id.cartCheckout);

        cartEmpty.setOnClickListener(l -> {
            cart.edit().putString("cart", "").apply();
            recyclerView.setAdapter(new CartAdapter(requireContext(), this));
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            update();
        });

        cartCheckout.setOnClickListener(l -> {
            Navigation.findNavController(view).navigate(R.id.orderFragment);
        });
    }

    @Override
    public void update() {
        json = cart.getString("cart", "");
        gson = new Gson();
        Type orderItemsType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
        orderItems = gson.fromJson(json, orderItemsType);
        float total = 0f;
        if(orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                total += orderItem.getItem().getPrice() * (float) orderItem.getCount();
            }
        }
        Log.i("cart", "Current Total: " + total);
        cartTotal.setText(String.format("%.02f", total) + " zł");
    }
}