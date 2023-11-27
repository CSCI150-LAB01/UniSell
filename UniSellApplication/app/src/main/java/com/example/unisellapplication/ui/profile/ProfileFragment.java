package com.example.unisellapplication.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.unisellapplication.R;
import com.example.unisellapplication.ui.logout.LogOutDialogueFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {
    Button logout;
    TextView name,email, phone;
    FirebaseAuth auth;
    DatabaseReference userReference;

    String currentUserId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                                     ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        currentUserId = auth.getCurrentUser().getUid();

        userReference = FirebaseDatabase.getInstance().getReference().child("Users");

        logout = root.findViewById(R.id.logout_button);
        email = root.findViewById(R.id.email_txt);
        name = root.findViewById(R.id.name_txt);
        phone = root.findViewById(R.id.phone_txt);

        userReference.child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String userEmail = snapshot.child("email").getValue().toString();
                    String userPhone = snapshot.child("phone").getValue().toString();
                    String userName = snapshot.child("name").getValue().toString();

                    name.setText(userName);
                    email.setText(userEmail);
                    phone.setText(userPhone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOutDialogueFragment dialogFragment = new LogOutDialogueFragment();
                dialogFragment.show(getActivity().getSupportFragmentManager(), "logout_dialog");
            }
        });

        return root;
    }

}