package com.example.unisellapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unisellapplication.R;
import com.example.unisellapplication.adapters.RecentAdapters;
import com.example.unisellapplication.databinding.FragmentHomeBinding;
import com.example.unisellapplication.models.ListingModel;

import java.util.List;

public class HomeFragment extends Fragment {


    //recent items
    List<ListingModel> listingModelList;
    RecentAdapters recentAdapters;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }


}
