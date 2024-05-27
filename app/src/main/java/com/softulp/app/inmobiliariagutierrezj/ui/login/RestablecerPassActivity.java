package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityRestablecerPassBinding;

public class RestablecerPassActivity extends AppCompatActivity {
    RestablecerPassViewModel vm;
    ActivityRestablecerPassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RestablecerPassViewModel.class);
        binding=ActivityRestablecerPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm.getMutableLoginStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                binding.progressBar2.setVisibility(View.GONE);
            }
        });
        vm.getMutableMsgError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMsgError2.setText(s);
                binding.progressBar2.setVisibility(View.GONE);
            }
        });

        binding.btnRestablecerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar2.setVisibility(View.VISIBLE);
                vm.enviarEmail(binding.etEmailRestablecer.getText().toString());

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.progressBar2.setVisibility(View.GONE);
    }
}