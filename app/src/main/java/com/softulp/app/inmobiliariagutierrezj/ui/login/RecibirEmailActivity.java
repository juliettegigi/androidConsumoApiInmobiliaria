package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityMainBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityRecibirEmailBinding;

public class RecibirEmailActivity extends AppCompatActivity {
    ActivityRecibirEmailBinding binding;
    RecibirEmailViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRecibirEmailBinding.inflate(getLayoutInflater());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecibirEmailViewModel.class);
        setContentView(binding.getRoot());




    }
}