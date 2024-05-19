package com.softulp.app.inmobiliariagutierrezj.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.softulp.app.inmobiliariagutierrezj.models.Usuario;

public class Archivos {
    private static SharedPreferences sp;
    private static SharedPreferences conectar(Context context){
        if(sp==null)
            sp=context.getSharedPreferences("datos",0);
        return sp;
    }

    public static void guardarTokenArchivoPreferencia(Context context,String token){
        SharedPreferences sp=conectar(context);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token", token);
        editor.commit();
    }
    public static String leerTokenArchivoPreferencia(Context context){
        SharedPreferences sp=conectar(context);
        return sp.getString("token",null);
    }
    public static void borrarTokenArchivoPreferencias(Context context){
        guardarTokenArchivoPreferencia(context,"");
    }
}
