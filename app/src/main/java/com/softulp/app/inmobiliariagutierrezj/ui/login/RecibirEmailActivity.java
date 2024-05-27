package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityRecibirEmailBinding;

public class RecibirEmailActivity extends AppCompatActivity {
ActivityRecibirEmailBinding binding;
    RecibirEmailViewModel vm;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecibirEmailBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecibirEmailViewModel.class);
        setContentView(binding.getRoot());
        Log.d("Salida", "onCreate: Intent recibido");
        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {
            String token = data.getQueryParameter("access_token");
            if (token != null) {
                Log.d("RecibirEmailActivity", "Token recibido: " + token);
            }


            binding.btnGuardarRecibirEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.actualizarPass("Bearer " + token, binding.etPass1.getText().toString(),binding.etPass2.getText().toString());
                }
            });


        }


    }
}