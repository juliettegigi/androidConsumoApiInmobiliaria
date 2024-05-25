package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles.InmuebleAdapter;

import java.util.List;

public class InmuebleAdapter2 extends RecyclerView.Adapter<InmuebleAdapter2.ViewHolder2> {
    private List<Inmueble> lista;
    private Context context;

    public InmuebleAdapter2(List<Inmueble> lista,Context context){

        this.lista=lista;
        this.context=context;
    }

    @NonNull
    @Override
    public InmuebleAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inquilino,parent,false);
        return new InmuebleAdapter2.ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter2.ViewHolder2 holder, int position) {
        Inmueble inmueble=lista.get(position);
        holder.inquilino.setText("Iquilino: "+inmueble.getContratos().get(0).getInquilino().getNombre()+" "+inmueble.getContratos().get(0).getInquilino().getApellido());
        holder.direccion.setText("Dirección: "+inmueble.getDireccion());

        String imageURL="";
        if(!inmueble.getImagenes().isEmpty()) {

            imageURL = ApiClient.getURL() + inmueble.getImagenes().get(0).getImagen();
            Glide.with(context)
                    .load(imageURL)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    // .apply(options)
                    .into(holder.imagen);

        }
        else holder.imagen.setImageResource(R.drawable.no_image);


        holder.verInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("inquilino",inmueble.getContratos().get(0).getInquilino());
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.nav_verInquilinoFragment,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView direccion,inquilino,verInquilino;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.ivInquilinoFragmentInm);
            direccion=itemView.findViewById(R.id.tvDireccionInquilinoFragment);
            inquilino=itemView.findViewById(R.id.tvInquilino);
            verInquilino=itemView.findViewById(R.id.tvVerInquilino);
        }
    }
}
