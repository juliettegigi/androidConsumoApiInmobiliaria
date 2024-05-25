package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentContratoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino.InmuebleAdapter2;

public class ContratoFragment extends Fragment {
    private FragmentContratoBinding binding;
    private ContratoViewModel vm;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(ContratoViewModel.class);

        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutableAdapter().observe(getViewLifecycleOwner(), new Observer<RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>>() {
            @Override
            public void onChanged(RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3> viewHolder3Adapter) {
                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.rvListaFragmentContrato.setLayoutManager(glm);
                binding.rvListaFragmentContrato.setAdapter(viewHolder3Adapter);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}