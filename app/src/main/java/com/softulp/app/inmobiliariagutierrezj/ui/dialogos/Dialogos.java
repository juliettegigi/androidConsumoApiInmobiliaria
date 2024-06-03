package com.softulp.app.inmobiliariagutierrezj.ui.dialogos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.softulp.app.inmobiliariagutierrezj.databinding.DialogoBinding;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.login.MainActivity;

public class Dialogos {


    public static void dialogoSalir(Context context){

        new AlertDialog.Builder(context) // creo una instancia de Builder, le paso el contexto
                .setTitle("Cierre de sesión")
                .setMessage("Desea cerrar la app?")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences preferences = context.getSharedPreferences("datos", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    public static void dialogoCrearInm(Context context){
        new AlertDialog.Builder(context)
         .setTitle("Error")
                .setMessage("El inmueble no ha podido ser dado de alta.")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }


    public static void dialogoInmCreado(Context context, View v){
        new AlertDialog.Builder(context)
                .setTitle("Inmueble agregado")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Navigation.findNavController(v).popBackStack();
                    }
                })
                .show();
    }

    public static void dialogoPassDistintas(Context context){
        new AlertDialog.Builder(context)
                .setTitle("Cotraseñas no coinciden")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

}
