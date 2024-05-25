package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

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
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerContratoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Contrato;
import com.softulp.app.inmobiliariagutierrezj.models.Inquilino;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino.VerInquilinoFragment;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino.VerInquilinoViewModel;

public class VerContratoFragment extends Fragment {
    FragmentVerContratoBinding binding;
    VerContratoViewModel vm;

    public static VerInquilinoFragment newInstance() {
        return new VerInquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(VerContratoViewModel.class);

        binding = FragmentVerContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutableContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
            }
        });



        vm.recibirContrato(getArguments());
        return root;
    }


}