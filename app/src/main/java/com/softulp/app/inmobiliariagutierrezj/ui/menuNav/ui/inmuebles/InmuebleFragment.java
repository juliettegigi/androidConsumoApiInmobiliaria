package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentInmuebleBinding;

public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;
    private InmuebleViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(InmuebleViewModel.class);

        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

     vm.cargarInmuebles();
        binding.btAddInm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle=new Bundle();
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.nav_crearInmFragment);

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