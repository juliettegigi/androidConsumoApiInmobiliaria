package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.databinding.FragmentMasInfoBinding;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;

public class MasInfoFragment extends Fragment {
    private MasIfoViewModel vm;
    private FragmentMasInfoBinding binding;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding=FragmentMasInfoBinding.inflate(inflater,container,false);
        vm=new ViewModelProvider(this).get(MasIfoViewModel.class);
        View root=binding.getRoot();

        binding.btnHabilitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.cambiarSuspendido();
            }
        });


        vm.getMutableAdapter().observe(getViewLifecycleOwner(), new Observer<RecyclerView.Adapter<ImagenesAdapter.ViewHolder>>() {
            @Override
            public void onChanged(RecyclerView.Adapter<ImagenesAdapter.ViewHolder> viewHolderAdapter) {
                GridLayoutManager glm=new GridLayoutManager(getContext(),1,GridLayoutManager.HORIZONTAL,false);
                binding.recyclerView.setLayoutManager(glm);
                binding.recyclerView.setAdapter(viewHolderAdapter);
            }
        });

        vm.getMutableTexto().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnHabilitar.setText(s);
            }
        });
        vm.getMutableInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.tvDireccion.setText("Dirección: "+inmueble.getDireccion());
                binding.tvCantidadAmbientes.setText("Cantidad de ambientes: "+String.valueOf(inmueble.getCantidadAmbientes()));
                binding.tvDispoible.setText("Disponible: "+ (inmueble.isDisponible()?"si":"no"));
                binding.tvPrecio.setText("Precio: "+inmueble.getPrecioBase()+"");
                binding.tvSuspendido.setText("Suspendido: "+(inmueble.isSuspendido()?"si":"no"));
                binding.tvUso.setText("Uso: "+inmueble.getUso());
                binding.tvTipoDeInmueble.setText("Tipo: "+inmueble.getInmuebleTipo());
            }
        });


        vm.recibirInmueble(getArguments());


        return root;
    }



}