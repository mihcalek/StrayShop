package com.example.projektmb4pp.adapter;

import com.example.projektmb4pp.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Item[] items;
    private Context context;
    private NavController navController;

    public ItemAdapter(Item[] items, Context context, NavController navController) {
        this.items = items;
        this.context = context;
        this.navController = navController;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView itemPhoto;
        private TextView itemTitle;
        private TextView itemCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPhoto = (ImageView) itemView.findViewById(R.id.product_image);
            itemTitle = (TextView) itemView.findViewById(R.id.product_title);
            itemCost = (TextView) itemView.findViewById(R.id.product_price);
        }

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Log.i("item", items[position].toString());
        holder.itemPhoto.setImageResource(context.getResources().getIdentifier(items[position].getPhoto(), "drawable", context.getPackageName()));
        holder.itemTitle.setText(items[position].getName());
        holder.itemCost.setText(items[position].getPrice() + " zł");
        holder.itemView.setOnClickListener(l -> {
            Bundle bundle = new Bundle();
            bundle.putLong("id", items[position].getId());
            navController.navigate(R.id.showcaseItemFragment, bundle);
        });

    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}
