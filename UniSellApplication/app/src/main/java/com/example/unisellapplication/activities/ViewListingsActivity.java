package com.example.unisellapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.unisellapplication.R;
import com.example.unisellapplication.models.ListingModel;

public class ViewListingsActivity extends AppCompatActivity {
    ImageView listImg;
    ListingModel listItem = null;
    TextView title, description, price, category, contactInfo;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_listings);

        toolbar = findViewById(R.id.view_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("details");
        if (object instanceof ListingModel){
            listItem = (ListingModel) object;
        }

        listImg = findViewById(R.id.add_img_button);
        title = findViewById(R.id.title_txt);
        description = findViewById(R.id.editTextDescripiton);
        price = findViewById(R.id.editTextPrice);
        category = findViewById(R.id.category_txt);
        contactInfo = findViewById(R.id.editTextContact);

        if (listItem!= null){
            Glide.with(getApplicationContext()).load(listItem.getImg_url()).into(listImg);
            title.setText(listItem.getTitle());
            description.setText(listItem.getDescription());
            price.setText("$" + listItem.getPrice());
            category.setText("Category: " + listItem.getCategory());
            contactInfo.setText(listItem.getUserName() + "\n\n" + listItem.getUserPhone());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the back button press
                onBackPressed();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}