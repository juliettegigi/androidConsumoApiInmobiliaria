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
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerContratoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerPagosfragmentBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Pago;

import java.util.ArrayList;

public class VerPagosFragment extends Fragment {
    FragmentVerPagosfragmentBinding binding;
    VerPagosFragmentViewModel vm;

    public static VerPagosFragment newInstance() {
        return new VerPagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        vm = new ViewModelProvider(this).get(VerPagosFragmentViewModel.class);

        binding = FragmentVerPagosfragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm.recibirPagos(getArguments());
      vm.getMutableAdapter().observe(getViewLifecycleOwner(), new Observer<RecyclerView.Adapter<PagosAdapter.ViewHolder>>() {
            @Override
            public void onChanged(RecyclerView.Adapter<PagosAdapter.ViewHolder> viewHolderAdapter) {

                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.rvPagos.setLayoutManager(glm);
                binding.rvPagos.setAdapter(viewHolderAdapter);

            }
        });


        return root;
    }



}