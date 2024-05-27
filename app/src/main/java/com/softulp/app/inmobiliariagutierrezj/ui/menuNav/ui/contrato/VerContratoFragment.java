package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerContratoBinding;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentVerInquilinoBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Contrato;
import com.softulp.app.inmobiliariagutierrezj.models.Inquilino;
import com.softulp.app.inmobiliariagutierrezj.models.Utilidades;
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
                binding.tvFechaInicio.setText(Utilidades.formatearFecha(contrato.getFechaInicio()));
                binding.tvFechaFin.setText(Utilidades.formatearFecha(contrato.getFechaFin()));
                binding.tvPrecioPorMes.setText(String.valueOf(contrato.getPrecioXmes()));

               binding.tvInquilinoVerContrato.setText(contrato.getInquilino().getNombre()+" "+contrato.getInquilino().getApellido());

               binding.tvVerInmuebleVerContrato.setText(contrato.getInmueble().getDireccion());

               binding.btnVerPagos.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Bundle bundle=new Bundle();
                       bundle.putSerializable("pagos",contrato.getPagos());
                       Navigation.findNavController(v).navigate(R.id.nav_verPagosFragment,bundle);
                   }
               });




            }

        });
        vm.recibirContrato(getArguments());

        return root;
    }


}