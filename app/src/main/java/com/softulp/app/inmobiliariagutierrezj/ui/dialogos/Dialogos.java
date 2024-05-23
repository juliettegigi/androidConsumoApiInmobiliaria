package com.softulp.app.inmobiliariagutierrezj.ui.dialogos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.softulp.app.inmobiliariagutierrezj.databinding.DialogoBinding;

public class Dialogos {

    public static void dialogoAlerta4(Context context){
        LayoutInflater li=LayoutInflater.from(context);//
        DialogoBinding binding=DialogoBinding.inflate(li);
        new AlertDialog.Builder(context)
                .setView(binding.getRoot())
                .setPositiveButton("Acceder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      /*  String emailIngresado=binding.etEmail.getText().toString();
                        String passIngresada=binding.etPass.getText().toString();
                        if(emailIngresado.equals("julia@gmail.com") && passIngresada.equals("123") ){
                            Toast.makeText(context, "Usuario loggeado", Toast.LENGTH_SHORT).show();
                        }
                        */
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //no hacer nada.
                    }
                }).show();
    }
}
