package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentLogoutBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.ui.dialogos.Dialogos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino.VerInquilinoViewModel;

public class LogoutFragment extends Fragment {

    LogoutViewModel vm;
    FragmentLogoutBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(LogoutViewModel.class);

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Dialogos.dialogoSalir(getContext());
        return root;

    }



}