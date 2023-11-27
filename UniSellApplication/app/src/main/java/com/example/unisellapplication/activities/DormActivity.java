package com.example.unisellapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unisellapplication.R;
import com.example.unisellapplication.adapters.RecentAdapters;
import com.example.unisellapplication.models.ListingModel;
import com.example.unisellapplication.ui.category.CategoryFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DormActivity extends AppCompatActivity {
    RecyclerView recentRecycle;
    DatabaseReference addListingReference;
    List<ListingModel> listingModelList;
    RecentAdapters recentAdapters, filteredAdapter;
    EditText search_box;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dorm);

        recentRecycle = findViewById(R.id.recent_rec);
        addListingReference = FirebaseDatabase.getInstance().getReference().child("Recent Products");

        toolbar = findViewById(R.id.dorm_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recent items recycle
        recentRecycle.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( DormActivity.this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recentRecycle.setLayoutManager(linearLayoutManager);

        listingModelList = new ArrayList<>();
        recentAdapters = new RecentAdapters( DormActivity.this, listingModelList, new RecentAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(ListingModel listItem) {
            }
        });
        recentRecycle.setAdapter(recentAdapters);

        addListingReference.orderByChild("category").equalTo("Dorm Essentials").addValueEventListener(new ValueEventListener() {
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
        search_box = findViewById(R.id.search_box);
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                Fragment fragment = new CategoryFragment();
                replaceFragment(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment); // Replace R.id.fragment_container with the actual ID of your fragment container
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void searchProduct(String inText) {
        List<ListingModel> filteredList = new ArrayList<>();
        filteredAdapter = new RecentAdapters(DormActivity.this, filteredList, new RecentAdapters.OnItemClickListener() {
            @Override
            public void onItemClick(ListingModel listItem) {
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
                Toast.makeText(DormActivity.this, "Search results not found", Toast.LENGTH_SHORT).show();
            }
            else {
                filteredAdapter.notifyDataSetChanged();
            }
        }

    }
}