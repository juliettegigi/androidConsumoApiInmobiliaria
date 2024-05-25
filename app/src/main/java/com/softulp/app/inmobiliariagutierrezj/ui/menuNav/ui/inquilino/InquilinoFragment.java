package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino;

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

import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentInquilinoBinding;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);

        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutableAdapter().observe(getViewLifecycleOwner(), new Observer<RecyclerView.Adapter<InmuebleAdapter2.ViewHolder2>>() {
            @Override
            public void onChanged(RecyclerView.Adapter<InmuebleAdapter2.ViewHolder2> viewHolderAdapter) {

                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
                binding.rvListaFragmentInquilino.setLayoutManager(glm);

                binding.rvListaFragmentInquilino.setAdapter(viewHolderAdapter);
                Log.d("salida", "q ewharrr 2");
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