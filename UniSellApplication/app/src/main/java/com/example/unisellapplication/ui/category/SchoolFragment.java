package com.example.unisellapplication.ui.category;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unisellapplication.R;
import com.example.unisellapplication.adapters.RecentAdapters;
import com.example.unisellapplication.models.ListingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SchoolFragment extends Fragment {
    RecyclerView recentRecycle;
    DatabaseReference addListingReference;
    List<ListingModel> listingModelList;
    RecentAdapters recentAdapters, filteredAdapter;
    EditText search_box;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_school, container, false);

        recentRecycle = root.findViewById(R.id.recent_rec);
        addListingReference = FirebaseDatabase.getInstance().getReference().child("Recent Products");

        //Recent items recycle
        recentRecycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recentRecycle.setLayoutManager(linearLayoutManager);

        listingModelList = new ArrayList<>();
        recentAdapters = new RecentAdapters(getActivity(), listingModelList, new RecentAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(ListingModel listItem) {
                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
            }
        });
        recentRecycle.setAdapter(recentAdapters);

        addListingReference.orderByChild("category").equalTo("School Supplies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot listSnapShot : snapshot.getChildren()) {
                        ListingModel listingModel = listSnapShot.getValue(ListingModel.class);
                        listingModelList.add(listingModel);
                        recentAdapters.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Search
        search_box = root.findViewById(R.id.search_box);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()){
                    // listingModelList.clear();
                    recentAdapters.notifyDataSetChanged();
                }
                else {
                    searchProduct(s.toString());
                }
            }
        });

        if (container != null) {
            container.removeAllViews();
        }

        return root;
    }
    private void searchProduct(String inText) {
        List<ListingModel> filteredList = new ArrayList<>();
        filteredAdapter = new RecentAdapters(getActivity(), filteredList, new RecentAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(ListingModel listItem) {
                Toast.makeText(getContext(), "Item Clicked", Toast.LENGTH_LONG).show();
            }
        });
        recentRecycle.swapAdapter(filteredAdapter, true);

        if(!inText.isEmpty()){
            for (ListingModel listItem : listingModelList) {
                if (listItem.getTitle().toLowerCase().contains(inText.toLowerCase())) {
                    filteredList.add(listItem);
                }
            }

            if(filteredList.isEmpty()){
                Toast.makeText(getActivity(), "Search results not found", Toast.LENGTH_SHORT).show();
            }
            else {
                filteredAdapter.notifyDataSetChanged();
            }
        }

    }
}