package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        binding.btnRestablecerPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.enviarEmail(binding.etEmailRestablecer.getText().toString());
            }
        });

    }
}