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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentCrearInmBinding;
import com.softulp.app.inmobiliariagutierrezj.models.InmuebleTipo;
import com.softulp.app.inmobiliariagutierrezj.ui.dialogos.Dialogos;

public class CrearInmFragment extends Fragment {

    private CrearInmViewModel vm;
    private FragmentCrearInmBinding binding;
    private Intent intent;
    private ActivityResultLauncher<Intent> arl;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(CrearInmViewModel.class);
        binding=FragmentCrearInmBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        abrirGaleria();
        vm.getMutableInmuebleTipos().observe(getViewLifecycleOwner(), new Observer<ArrayAdapter<InmuebleTipo>>() {
                    @Override
                    public void onChanged(ArrayAdapter<InmuebleTipo> stringArrayAdapter) {
                        binding.spinnerImuebleTipos.setAdapter(stringArrayAdapter);
                    }
                });
        vm.getMutableDialogoCrearInm().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Dialogos.dialogoCrearInm(getContext());
            }
        });
        vm.getMutableDialogoInmCreado().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Dialogos.dialogoInmCreado(getContext());
            }
        });
        vm.getMutableAdapterRV().observe(getViewLifecycleOwner(), new Observer<RecyclerView.Adapter<ImgInmAdapter.ViewHolder>>() {
            @Override
            public void onChanged(RecyclerView.Adapter<ImgInmAdapter.ViewHolder> viewHolderAdapter) {
                GridLayoutManager glm=new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false);
                binding.rvImagenes.setLayoutManager(glm);
                binding.rvImagenes.setAdapter(viewHolderAdapter);
            }
        });
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InmuebleTipo selectedItem = (InmuebleTipo) binding.spinnerImuebleTipos.getSelectedItem();
                int inmuebleTipoId = selectedItem.getId(); // Recupera el ID del item seleccionado

                vm.AltaInmueble(inmuebleTipoId,
                                binding.etDireccion.getText().toString(),
                        binding.etCatidadAmbientes.getText().toString(),
                        binding.spinnerUso.getSelectedItem().toString(),
                        binding.etPrecioBase.getText().toString()
                        );


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