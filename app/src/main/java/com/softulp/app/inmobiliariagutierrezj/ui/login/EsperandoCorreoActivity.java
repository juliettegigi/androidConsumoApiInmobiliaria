package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityEsperandoCorreoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityMainBinding;

public class EsperandoCorreoActivity extends AppCompatActivity {
    EsperandoCorreoViewModel vm;
    ActivityEsperandoCorreoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEsperandoCorreoBinding.inflate(getLayoutInflater());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(EsperandoCorreoViewModel.class);
        setContentView(binding.getRoot());

        binding.btnIniciarSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EsperandoCorreoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}