package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {


    public InmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public void cargarInmuebles(){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();//clase q creó el retrofit
        String token="Bearer "+ Archivos.leerTokenArchivoPreferencia(getApplication());
        Call<ArrayList<Inmueble>> call=api.getMisInmuebles(token);
        call.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()){
                    Log.d("salida","sta ssssssssssss");
                }
                else if(response.code() == 204) {
                    Log.d("salida", "q caralio");
//respuesta cuando no se le pasa el token
                }


            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable throwable) {
                Log.d("salida", "Falla en vista de perfil");
            }
        });
    }
}