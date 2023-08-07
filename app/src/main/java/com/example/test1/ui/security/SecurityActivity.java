package com.example.test1.ui.security;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test1.databinding.ActivitySecurityBinding;

public class SecurityActivity extends AppCompatActivity {
    private ActivitySecurityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}