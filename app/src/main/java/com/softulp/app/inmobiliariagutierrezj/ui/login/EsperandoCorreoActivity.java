package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.os.Bundle;

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
        setContentView(R.layout.activity_esperando_correo);

    }
}