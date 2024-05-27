package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

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
import com.softulp.app.inmobiliariagutierrezj.models.Contrato;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;

import java.util.List;


public class InmuebleAdapter3 extends RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>  {

    private List<Inmueble> lista;
    private Context context;

    public InmuebleAdapter3(List<Inmueble> lista,Context context){

        this.lista=lista;
        this.context=context;
    }

    @NonNull
    @Override
    public InmuebleAdapter3.ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inquilino,parent,false);
        return new InmuebleAdapter3.ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleAdapter3.ViewHolder3 holder, int position) {
        Inmueble inmueble=lista.get(position);
        holder.verInquilino.setText("Ver contrato");
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
                Contrato contrato=inmueble.getContratos().get(0);
                contrato.setInmueble(inmueble);
                bundle.putSerializable("contrato",contrato);
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.nav_verContratoFragment,bundle);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView direccion,inquilino,verInquilino;

        public ViewHolder3(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.ivInquilinoFragmentInm);
            direccion=itemView.findViewById(R.id.tvDireccionInquilinoFragment);
            inquilino=itemView.findViewById(R.id.tvInquilino);
            verInquilino=itemView.findViewById(R.id.tvVerInquilino);
        }
    }
}
