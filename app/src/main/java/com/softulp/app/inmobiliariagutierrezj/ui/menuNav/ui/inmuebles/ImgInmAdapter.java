package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.R;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImgInmAdapter extends RecyclerView.Adapter<ImgInmAdapter.ViewHolder> {

    private List<Uri> lista;
    private Context context;


    public ImgInmAdapter(List<Uri> lista,Context context){

        this.lista=lista;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add_fotos,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            Uri img=lista.get(position);
            holder.imagen.setImageURI(img);
        holder.btnCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posicion = holder.getAdapterPosition();
                removeItem(posicion);
            }
        });


    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public List<Bitmap> getLista(Context context) {
        List<Bitmap> listaBitmap = new ArrayList<>();

        for (Uri uri : lista) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
                listaBitmap.add(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return listaBitmap;
    }

    public void removeItem(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, lista.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton btnCruz;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCruz=itemView.findViewById(R.id.btnCruz);
            imagen=itemView.findViewById(R.id.ivInmueble);

        }
    }
}
