package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentMasInfoBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;

public class MasInfoFragment extends Fragment {
    private MasIfoViewModel vm;
    private FragmentMasInfoBinding binding;


    public static MasInfoFragment newInstance() {

        return new MasInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        vm=new ViewModelProvider(this).get(MasIfoViewModel.class);
        binding=FragmentMasInfoBinding.inflate(inflater,container,false);
        View root=binding.getRoot();



        vm.getMutableImagen().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("salida","qqq3  ---  "+s);
                if (s != null && !s.isEmpty()) {
                    Glide.with(getContext())
                            .load(s)
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .into(binding.ivInm);
                } else {
                    binding.ivInm.setImageResource(R.drawable.no_image);
                }
            }
        });
        vm.getMutableInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvDireccion.setText(inmueble.getDireccion());
                binding.tvCantidadAmbientes.setText(String.valueOf(inmueble.getCantidadAmbientes()));
                binding.tvDispoible.setText(inmueble.isDisponible()?"si":"no");
                binding.tvPrecio.setText(inmueble.getPrecioBase()+"");
                binding.tvSuspendido.setText(inmueble.isSuspendido()?"si":"no");
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.getImg(1);
            }
        });

        binding.btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.getImg(-1);
            }
        });
        vm.recibirInmueble(getArguments());
        return root;
    }



}