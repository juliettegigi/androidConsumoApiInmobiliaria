package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
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

import java.util.List;
import androidx.navigation.Navigation;
public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.ViewHolder>{
    private List<Inmueble> lista;
    private Context context;

    public InmuebleAdapter(List<Inmueble> lista,Context context){

        this.lista=lista;
        this.context=context;
    }

    @NonNull
    @Override
    public InmuebleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inmueble,parent,false);
        return new InmuebleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inmueble inmueble=lista.get(position);
        holder.precio.setText(String.valueOf("Precio :"+inmueble.getPrecioBase()));
        holder.direccion.setText("Dirección: "+inmueble.getDireccion());

        String imageURL="";
        if(!inmueble.getImagenes().isEmpty()) {

                imageURL = ApiClient.getURL() + inmueble.getImagenes().get(0).getImagen();
           /* RequestOptions options=new RequestOptions().placeholder(R.drawable.icon_imuebles);
                                                          .error(R.drawable.icon_logout);
           * */
                Glide.with(context)
                        .load(imageURL)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        // .apply(options)
                        .into(holder.imagen);

        }
        else holder.imagen.setImageResource(R.drawable.no_image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("inmueble",inmueble);
                Navigation.findNavController((Activity) v.getContext(), R.id.nav_host_fragment_content_menu_navegable).navigate(R.id.nav_masInfoFragment,bundle);

            }
        });
    }



    public void removeItem(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView direccion,precio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.ivInmuebleFragmentInm);
            direccion=itemView.findViewById(R.id.tvDireccion);
            precio=itemView.findViewById(R.id.tvPrecioBase);

        }
    }
}
