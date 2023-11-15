package com.example.unisellapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unisellapplication.R;
import com.example.unisellapplication.adapters.RecentAdapters;
import com.example.unisellapplication.databinding.FragmentHomeBinding;
import com.example.unisellapplication.models.ListingModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecyclerView recentRecycle;
    DatabaseReference addListingReference;
    List<ListingModel> listingModelList;
    RecentAdapters recentAdapters;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recentRecycle = root.findViewById(R.id.recent_rec);
        addListingReference = FirebaseDatabase.getInstance().getReference().child("Recent Products");

        //Recent items recycle
        recentRecycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recentRecycle.setLayoutManager(linearLayoutManager);

        listingModelList = new ArrayList<>();
        recentAdapters = new RecentAdapters(getActivity(), listingModelList);
        recentRecycle.setAdapter(recentAdapters);

        addListingReference.addValueEventListener(new ValueEventListener() {
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


        return root;
    }

}
