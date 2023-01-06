package com.example.projektmb4pp.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.example.projektmb4pp.R;
import com.google.gson.reflect.TypeToken;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    ArrayList<OrderItem> orderItems;
    Context context;
    SharedPreferences cart;
    String json;
    Gson gson;
    EventListener eventListener;

    public CartAdapter(Context context, EventListener eventListener) {
        this.context = context;
        cart = context.getSharedPreferences("cart", Context.MODE_PRIVATE);
        json = cart.getString("cart", "");
        gson = new Gson();
        Type orderItemsType = new TypeToken<ArrayList<OrderItem>>(){}.getType();
        orderItems = gson.fromJson(json, orderItemsType);

        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.cartItemName.setText("[" + orderItems.get(position).getSize() + "] " + orderItems.get(position).getItem().getName());
        float price = orderItems.get(position).getItem().getPrice() * orderItems.get(position).getCount();
        holder.cartItemPrice.setText(String.format("%.02f", price) + " zł");
        holder.cartItemCount.setText(orderItems.get(position).getCount() + "");
        holder.cartItemLess.setOnClickListener(l -> {
            if(orderItems.get(position).getCount() > 1) {
                orderItems.get(position).setCount(orderItems.get(position).getCount() - 1);

                json = gson.toJson(orderItems);
                cart.edit().putString("cart", json).apply();

                Log.i("JSON cart", cart.getString("cart", ""));
                Log.i("cart", orderItems.toString());

                holder.cartItemCount.setText(orderItems.get(position).getCount() + "");
                holder.cartItemPrice.setText(String.format("%.02f", orderItems.get(position).getItem().getPrice() * orderItems.get(position).getCount()) + " zl");
                eventListener.update();
            } else {
                orderItems.remove(position);
                json = gson.toJson(orderItems);
                cart.edit().putString("cart", json).apply();
                eventListener.update();
                notifyDataSetChanged();
            }
        });

        holder.cartItemMore.setOnClickListener(l -> {
            orderItems.get(position).setCount(orderItems.get(position).getCount() + 1);

            json = gson.toJson(orderItems);
            cart.edit().putString("cart", json).apply();

            Log.i("JSON cart", cart.getString("cart", ""));
            Log.i("cart", orderItems.toString());

            holder.cartItemCount.setText(orderItems.get(position).getCount() + "");
            holder.cartItemPrice.setText(String.format("%.02f", orderItems.get(position).getItem().getPrice() * orderItems.get(position).getCount()) + " zl");
            eventListener.update();
        });

        holder.cartItemDelete.setOnClickListener(l -> {
            orderItems.remove(position);
            json = gson.toJson(orderItems);
            cart.edit().putString("cart", json).apply();
            eventListener.update();
            notifyDataSetChanged();
        });
        eventListener.update();
    }

    @Override
    public int getItemCount() {
        if (orderItems == null) {
            return 0;
        }
        return orderItems.size();
    }

    public interface EventListener {
        void update();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cartItemName;
        TextView cartItemPrice;
        ImageButton cartItemLess;
        TextView cartItemCount;
        ImageButton cartItemMore;
        ImageButton cartItemDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemName = itemView.findViewById(R.id.cartItemName);
            cartItemPrice = itemView.findViewById(R.id.cartItemPrice);
            cartItemLess = itemView.findViewById(R.id.cartItemLess);
            cartItemCount = itemView.findViewById(R.id.cartItemCount);
            cartItemMore = itemView.findViewById(R.id.cartItemMore);
            cartItemDelete = itemView.findViewById(R.id.cartItemDelete);
        }
    }
}