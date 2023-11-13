package com.example.unisellapplication.ui.create_listing;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unisellapplication.R;
import com.example.unisellapplication.activities.HomeActivity;

public class CreateListingFragment extends Fragment {

    ImageButton imageButton;
    EditText title, description, price;
    private static final int SELECT_PICTURE = 200;

//    FirebaseFirestore db;
    String[] item = {"Textbooks", "School Supplies","Lab Equipment", "Dorm Essentials", "Other"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter<String> adapterItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_listing, container, false);

        imageButton = root.findViewById(R.id.add_img_button);
        title = root.findViewById(R.id.editTextTitle);
        description = root.findViewById(R.id.editTextDescripiton);
        price =root.findViewById(R.id.editTextPrice);

        autoCompleteTextView =root.findViewById(R.id.SelectCategory);

        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.select_category, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getActivity(), "Item: " + item, Toast.LENGTH_SHORT).show();

            }
        });

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