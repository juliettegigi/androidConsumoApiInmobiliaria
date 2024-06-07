package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.softulp.app.inmobiliariagutierrezj.databinding.ActivityRecibirEmailBinding;
import com.softulp.app.inmobiliariagutierrezj.ui.dialogos.Dialogos;

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


            binding.btnGuardarNuevaPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.actualizarPass("Bearer " + token, binding.etPass1.getText().toString(),binding.etPass2.getText().toString(),RecibirEmailActivity.this);

                }
            });

            vm.getMutableDialogo().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    Dialogos.dialogoPassDistintas(RecibirEmailActivity.this);
                }
            });
            vm.getMutableVerProgressBar().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer i) {
                    binding.progressBar3.setVisibility(i);
                }
            });

            vm.getMutableVisible1().observe(this, new Observer<Visible>() {
                @Override
                public void onChanged(Visible visible) {
                    binding.etPass1.setInputType(visible.getTipoInput());
                    binding.ivVisible1.setImageResource(visible.getImagen());
                    binding.etPass1.setSelection(visible.getCursorPosition());
                }
            });
            vm.getMutableVisible2().observe(this, new Observer<Visible>() {
                @Override
                public void onChanged(Visible visible) {
                    binding.etPass2.setInputType(visible.getTipoInput());
                    binding.ivVisible2.setImageResource(visible.getImagen());
                    binding.etPass2.setSelection(visible.getCursorPosition());
                }
            });

            binding.ivVisible1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.cambiarVisible( binding.etPass1.getSelectionStart(),1);
                }
            });

            binding.ivVisible2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vm.cambiarVisible( binding.etPass2.getSelectionStart(),2);
                }
            });




        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        vm.setMutableVerProgressBar(View.GONE);
    }
}