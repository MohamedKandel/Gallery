package com.mkandeel.gallery.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.mkandeel.gallery.Helper.Helper;
import com.mkandeel.gallery.R;
import com.mkandeel.gallery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment navhost = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavHostFragment.findNavController(navhost);
    }
}