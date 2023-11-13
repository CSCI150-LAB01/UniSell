package com.example.unisellapplication.ui.create_listing;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.unisellapplication.R;

public class CreateListingFragment extends Fragment {

    ImageButton imageButton;
    EditText title, description, price;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_listing, container, false);
        return root;
    }
}