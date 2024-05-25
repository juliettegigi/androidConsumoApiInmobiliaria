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
        binding= ActivityRecibirEmailBinding.inflate(getLayoutInflater());
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecibirEmailViewModel.class);
        setContentView(binding.getRoot());
        Log.d("Salida", "onCreate: Intent recibido");
        Intent intent = getIntent();
        if (intent != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            if (uri != null) {
                token = uri.getQueryParameter("access_token");
                if (token != null) {
                    // Use the token to reset the password
                    // For example, show the UI for entering a new password

                } else {
                    // Handle error: no token found

                }
            } else {
                // Handle error: no URI

            }
        }

        binding.btnGuardarRecibirEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.actualizarPass("Bearer "+token,binding.etPassRecibirEmail.getText().toString());
            }
        });


    }







}