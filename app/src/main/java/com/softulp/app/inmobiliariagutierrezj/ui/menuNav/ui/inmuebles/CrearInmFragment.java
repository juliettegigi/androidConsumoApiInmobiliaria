package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentCrearInmBinding;

public class CrearInmFragment extends Fragment {

    private CrearInmViewModel vm;
    private FragmentCrearInmBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;

    public static CrearInmFragment newInstance() {
        return new CrearInmFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(CrearInmViewModel.class);
        binding=FragmentCrearInmBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        abrirGaleria();
        vm.getMutableInmuebleTipos().observe(getViewLifecycleOwner(), new Observer<ArrayAdapter<String>>() {
                    @Override
                    public void onChanged(ArrayAdapter<String> stringArrayAdapter) {
                        binding.spinnerImuebleTipos.setAdapter(stringArrayAdapter);
                    }
                });
        binding.btnAgregarImgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch(intent);
            }
        });
        vm.getMutableAdapterUsos().observe(getViewLifecycleOwner(), new Observer<ArrayAdapter<String>>() {
            @Override
            public void onChanged(ArrayAdapter<String> stringArrayAdapter) {
                binding.spinnerUso.setAdapter(stringArrayAdapter);
            }
        });

        vm.setAdapterInmuebleTipos();
        vm.setAdapterUsos();




        return root ;
    }





    private void abrirGaleria(){


        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    vm.recibirFotos(result);
                }
            }
        });
    };



}