package com.example.unisellapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unisellapplication.R;
import com.example.unisellapplication.activities.ViewListingsActivity;
import com.example.unisellapplication.models.ListingModel;

import java.util.List;

public class RecentAdapters extends RecyclerView.Adapter<RecentAdapters.ViewHolder> {
    private Context context;
    private List<ListingModel> listingModelList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ListingModel listItem);
    }

    public RecentAdapters(Context context, List<ListingModel> listingModelList, OnItemClickListener listener) {
        this.context = context;
        this.listingModelList = listingModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_items, parent,false));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(listingModelList.get(position), listener);
    }



    @Override
    public int getItemCount() {
        return listingModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recImg;
        TextView title,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recImg = itemView.findViewById(R.id.recent_img);
            title = itemView.findViewById(R.id.recent_title);
            price = itemView.findViewById(R.id.recent_price);
            category = itemView.findViewById(R.id.recent_cat);
        }

        public void bind(final ListingModel item, final OnItemClickListener listener) {
            Glide.with(context).load(item.getImg_url()).into(recImg);
            title.setText(item.getTitle());
            price.setText("$" + item.getPrice());
            category.setText(item.getCategory());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent mainIntent = new Intent(context, ViewListingsActivity.class);
                    mainIntent.putExtra("details", item);
                    context.startActivity(mainIntent);
                    ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    listener.onItemClick(item);
                }
            });
        }
    }
}
