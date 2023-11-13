package com.example.unisellapplication.ui.create_listing;


import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
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
import com.example.unisellapplication.activities.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CreateListingFragment extends Fragment {

    ImageButton imageButton;
    EditText title, description, price;
    ProgressDialog loadingBar;
    private static final int SELECT_PICTURE = 1;
    Uri ImageUri;
    Button addListingbtn;
    StorageReference storageReference;
    DatabaseReference userReference, addListingReference;
    FirebaseAuth auth;
    String Title, Description, Category, saveCurrentDate, saveCurrentTime, postRandomName, downloadUrl, currentUserId;
    Float newPrice;
    String[] item = {"Textbooks", "School Supplies","Lab Equipment", "Dorm Essentials", "Other"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_listing, container, false);

        imageButton = root.findViewById(R.id.add_img_button);
        title = root.findViewById(R.id.editTextTitle);
        description = root.findViewById(R.id.editTextDescripiton);
        price = root.findViewById(R.id.editTextPrice);
        addListingbtn = root.findViewById(R.id.add_listing_btn);
        autoCompleteTextView = root.findViewById(R.id.SelectCategory);

        adapterItems = new ArrayAdapter<String>(getActivity(), R.layout.select_category, item);
        autoCompleteTextView.setAdapter(adapterItems);

        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();

        storageReference = FirebaseStorage.getInstance().getReference();
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        addListingReference = FirebaseDatabase.getInstance().getReference().child("Recent Products");


        loadingBar = new ProgressDialog(getActivity());


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

        addListingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidatePostInfo();
            }
        });
        return root;
    }

    private void ValidatePostInfo() {
        Title = title.getText().toString();
        Description = description.getText().toString();
        String Price = price.getText().toString();
        Category = autoCompleteTextView.getText().toString();

        if(ImageUri == null){
            Toast.makeText(getActivity(), "Please select image for the listing", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Title)){
            Toast.makeText(getActivity(), "Please input a title for the listing", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Description)){
            Toast.makeText(getActivity(), "Please input a description for the listing", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Price)){
            Toast.makeText(getActivity(), "Please input a Price for the listing", Toast.LENGTH_SHORT).show();
            return;
        }

        Price = Price.replace("$", "").replace(",", "");
        newPrice = Float.parseFloat(Price);

        if(newPrice == null){
            Toast.makeText(getActivity(), "Please input a valid price", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(Category)){
            Toast.makeText(getActivity(), "Please select a category that your item falls under", Toast.LENGTH_SHORT).show();
            return;
        }

        loadingBar.setTitle("Add New Listing");
        loadingBar.setMessage("Please waite while we create your listing...");
        loadingBar.show();
        loadingBar.setCanceledOnTouchOutside(true);
        StoreImageToFirebaseStorage();
    }
    private void StoreImageToFirebaseStorage() {
        Calendar callForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(callForDate.getTime());

        Calendar callForTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(callForTime.getTime());

        postRandomName = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = storageReference.child("Post Images").child(ImageUri.getLastPathSegment() + postRandomName + ".png");

        filePath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    downloadUrl = task.getResult().getMetadata().getReference().getDownloadUrl().toString();
                    Toast.makeText(getActivity(), "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                    SavingListInformationToDatabase();
                }
                else {
                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void SavingListInformationToDatabase() {
        userReference.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String userName = snapshot.child("name").getValue().toString();
                    String userPhone = snapshot.child("phone").getValue().toString();

                    HashMap listMap = new HashMap();
                        listMap.put("uid", currentUserId);
                        listMap.put("date", saveCurrentDate);
                        listMap.put("time", saveCurrentTime);
                        listMap.put("img_url", downloadUrl);
                        listMap.put("title", Title);
                        listMap.put("description", Description);
                        listMap.put("price", newPrice);
                        listMap.put("category", Category);
                        listMap.put("userName", userName);
                        listMap.put("userPhone", userPhone);

                    addListingReference.child(currentUserId + postRandomName).updateChildren(listMap)
                            .addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if(task.isSuccessful()){
                                        SendUserToMainActivity();
                                        Toast.makeText(getActivity(), "New listing updated successfully", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void OpenGallery() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PICTURE && data!=null && resultCode==RESULT_OK) {
            {
                ImageUri = data.getData();
                imageButton.setImageURI(ImageUri);
            }
        }
    }
    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
        startActivity(mainIntent);
    }

}