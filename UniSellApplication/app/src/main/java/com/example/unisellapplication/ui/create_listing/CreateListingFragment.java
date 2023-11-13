package com.example.unisellapplication.ui.create_listing;

import android.content.Intent;
import android.net.Uri;
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
    private static final int SELECT_PICTURE = 200;

//    FirebaseFirestore db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_listing, container, false);

        imageButton = root.findViewById(R.id.add_img_button);
        title = root.findViewById(R.id.editTextTitle);
        description = root.findViewById(R.id.editTextDescripiton);
        price =root.findViewById(R.id.editTextPrice);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });

        return root;
    }

    private void OpenGallery() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
}