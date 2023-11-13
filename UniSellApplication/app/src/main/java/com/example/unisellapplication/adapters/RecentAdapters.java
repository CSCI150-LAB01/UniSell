package com.example.unisellapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unisellapplication.R;
import com.example.unisellapplication.models.ListingModel;

import java.util.List;

public class RecentAdapters extends RecyclerView.Adapter<RecentAdapters.ViewHolder> {

    private Context context;
    private List<ListingModel> listingModelList;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_items, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(listingModelList.get(position).getImg_url()).into(holder.recImg);
        holder.name.setText(listingModelList.get(position).getName());
        holder.price.setText(listingModelList.get(position).getPrice());
        holder.category.setText(listingModelList.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return listingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recImg;
        TextView name,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recImg = itemView.findViewById(R.id.recent_img);
            name = itemView.findViewById(R.id.recent_name);
            price = itemView.findViewById(R.id.recent_price);
            category = itemView.findViewById(R.id.recent_cat);
        }
    }
}