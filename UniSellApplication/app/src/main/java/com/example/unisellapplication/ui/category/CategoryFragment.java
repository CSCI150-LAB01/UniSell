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
import com.example.unisellapplication.activities.DormActivity;
import com.example.unisellapplication.activities.LabActivity;
import com.example.unisellapplication.activities.OtherActivity;
import com.example.unisellapplication.activities.SchoolActivity;
import com.example.unisellapplication.activities.TechnologyActivity;
import com.example.unisellapplication.activities.TextbooksActivity;

public class CategoryFragment extends Fragment {
    LinearLayout textbook_layout, dorm_layout, tech_layout, school_layout, lab_layout, other_layout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        textbook_layout = root.findViewById(R.id.ic_textbook_layout);
        dorm_layout = root.findViewById(R.id.dorm_layout);
        tech_layout = root.findViewById(R.id.tech_layout);
        school_layout = root.findViewById(R.id.school_layout);
        lab_layout = root.findViewById(R.id.lab_layout);
        other_layout = root.findViewById(R.id.other_layout);

        textbook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TextbooksActivity.class));
            }
        });
        dorm_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DormActivity.class));
            }
        });
        tech_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TechnologyActivity.class));
            }
        });
        school_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SchoolActivity.class));
            }
        });
        lab_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LabActivity.class));
            }
        });
        other_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OtherActivity.class));
            }
        });

        return root;
    }
}