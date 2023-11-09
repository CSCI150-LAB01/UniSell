package com.example.unisellapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unisellapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
    }
    public void login(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
    public void registration(View view) {
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
}