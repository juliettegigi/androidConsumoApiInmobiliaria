package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.softulp.app.inmobiliariagutierrezj.R;

import java.util.List;

public class ImagenesAdapter extends RecyclerView.Adapter<ImagenesAdapter.ViewHolder>{
    private List<String> lista;
    private Context context;

    private int recyclerViewWidth;
    public ImagenesAdapter(List<String> lista,Context context){

        this.lista=lista;
        this.context=context;  this.recyclerViewWidth = recyclerViewWidth;
    }
    @NonNull
    @Override
    public ImagenesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_imginm,parent,false);


        return new ImagenesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenesAdapter.ViewHolder holder, int position) {
       int pos=position%getItemCount();
        String img=lista.get(pos);
        if (img != null && !img.isEmpty()) {
        Glide.with(context)
                .load(img)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(holder.imagen);
    } else {
        holder.imagen.setImageResource(R.drawable.no_image);
    }



    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.ivItemImg);

        }
    }
}
