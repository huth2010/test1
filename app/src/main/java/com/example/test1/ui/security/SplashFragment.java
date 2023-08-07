package com.example.test1.ui.security;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.test1.R;
import com.example.test1.databinding.FragmentSplashBinding;


public class SplashFragment extends Fragment {
private FragmentSplashBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_loginFragment);
            }
        }, 3000);

        return binding.getRoot();
    }


}
