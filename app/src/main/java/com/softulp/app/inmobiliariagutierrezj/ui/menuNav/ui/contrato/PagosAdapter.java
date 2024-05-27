package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.models.Pago;
import com.softulp.app.inmobiliariagutierrezj.models.Utilidades;


import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {
    private List<Pago> lista;
    private Context context;

    public PagosAdapter(List<Pago> lista,Context context){

        this.lista=lista;
        this.context=context;
    }
    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pagos,parent,false);
        return new PagosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {



        Pago pago=lista.get(position);

        holder.nroPago.setText(pago.getNumeroPago()+"");
        holder.codigoContrato.setText(pago.getContratoId()+"");
        holder.codigoPago.setText(pago.getId()+"");
        holder.fechaDePago.setText(Utilidades.formatearFecha(pago.getFechaPago()));
        holder.importe.setText(String.valueOf(pago.getImporte()+""));


    }

    @Override
    public int getItemCount() {

        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nroPago,codigoContrato,codigoPago,fechaDePago,importe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nroPago=itemView.findViewById(R.id.tvNumeroPago);
            codigoContrato=itemView.findViewById(R.id.tvCodigoContrato);
            codigoPago=itemView.findViewById(R.id.tvCodigoPago);
            fechaDePago=itemView.findViewById(R.id.tvFechaDePago);
            importe=itemView.findViewById(R.id.tvImportePago);
        }
    }
}
