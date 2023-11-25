package com.example.unisellapplication.ui.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unisellapplication.R;
import com.example.unisellapplication.activities.TextbooksActivity;

public class CategoryFragment extends Fragment {
    LinearLayout textbook_layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        textbook_layout = root.findViewById(R.id.ic_textbook_layout);
        textbook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TextbooksActivity.class));
            }
        });
        return root;
    }


//    public void login(View view) {
//        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//    }
//
//    public void login(View view) {
//        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//    }
//
//    public void login(View view) {
//        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//    }
//
//    public void login(View view) {
//        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//    }
//
//    public void login(View view) {
//        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//    }

}