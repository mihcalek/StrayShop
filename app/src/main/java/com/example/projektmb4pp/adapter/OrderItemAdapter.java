package com.example.projektmb4pp.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projektmb4pp.R;
import com.example.projektmb4pp.DatabaseLMAO;
import com.example.projektmb4pp.adapter.OrderItem;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    ArrayList<OrderItem> items;
    Context context;

    public OrderItemAdapter(Context context, SQLiteDatabase db, long orderID) {
        this.context = context;
        this.items = new DatabaseLMAO.DBHelper(context).getOrderItems(db, orderID);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.orderElement);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(items.get(position).getCount() + " x " + items.get(position).getItem().getName() + " [" + items.get(position).getSize() + "]");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}