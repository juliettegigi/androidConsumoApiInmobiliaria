package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Inquilino;

public class VerInquilinoFragment extends Fragment {

    FragmentVerInquilinoBinding binding;
    VerInquilinoViewModel vm;

    public static VerInquilinoFragment newInstance() {
        return new VerInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(VerInquilinoViewModel.class);

        binding = FragmentVerInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutableInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {
                binding.tvApellidoInq.setText(inquilino.getApellido());
                binding.tvNombreInquilino.setText(inquilino.getNombre());
                binding.tvDNInq.setText(inquilino.getDni());
                binding.tvTelefonoInq.setText(inquilino.getTelefono());
                binding.tvEmailInq.setText(inquilino.getEmail());
                binding.tvDomicilioInq.setText(inquilino.getDomicilio());
            }
        });



        vm.recibirInquilino(getArguments());
        return root;
    }


}