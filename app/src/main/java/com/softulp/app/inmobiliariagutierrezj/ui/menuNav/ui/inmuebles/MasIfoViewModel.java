package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.models.ImagenInmueble;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasIfoViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mutableInmueble;
    private MutableLiveData<String> mutableTexto;
    private MutableLiveData<RecyclerView.Adapter<ImagenesAdapter.ViewHolder>> mutableAdapter;
    private ArrayList<String> arrStringUris;
    private int i;
    public MasIfoViewModel(@NonNull Application application) {

        super(application);
        arrStringUris=new ArrayList<>();
    }

    public LiveData<Inmueble> getMutableInmueble(){
        if(mutableInmueble==null)
            mutableInmueble=new MutableLiveData<>();
        return mutableInmueble;
    }
    public LiveData<String> getMutableTexto(){
        if(mutableTexto==null)
            mutableTexto=new MutableLiveData<>();
        return mutableTexto;
    }

    public LiveData<RecyclerView.Adapter<ImagenesAdapter.ViewHolder>> getMutableAdapter(){
        if(mutableAdapter==null) {
            mutableAdapter = new MutableLiveData<RecyclerView.Adapter<ImagenesAdapter.ViewHolder>>();

        }
        return mutableAdapter;
    }

    public void recibirInmueble(Bundle bundle){

        Inmueble inmueble=(Inmueble) bundle.get("inmueble");

        if(inmueble!=null){
            mutableInmueble.postValue(inmueble);
            for (ImagenInmueble imagen : inmueble.getImagenes()) {

                    String url = ApiClient.getURL() + imagen.getImagen();
                    arrStringUris.add(url);
                }
            RecyclerView.Adapter<ImagenesAdapter.ViewHolder>  adapter=new ImagenesAdapter(arrStringUris,getApplication());

            mutableTexto.postValue(inmueble.isSuspendido()?"Habilitar":"Deshabilitar");

            mutableAdapter.postValue(adapter);
            }

        }

public void cambiarSuspendido(){
    ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
    String token="Bearer "+ Archivos.leerTokenArchivoPreferencia(getApplication());
    Call<Inmueble> call= api.cambiarSuspendido(token,mutableInmueble.getValue().getId());
    call.enqueue(new Callback<Inmueble>() {
        @Override
        public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
            if(response.isSuccessful()){
                Log.d("salida","entroooddd111");
                if(mutableTexto.getValue().equals("Habilitar"))
                    mutableTexto.setValue("Deshabilitar");
                else mutableTexto.setValue("Habilitar");
                mutableInmueble.setValue(response.body());
            } else {
                Log.d("salida", "Falla en else");
            }

        }

        @Override
        public void onFailure(@NonNull Call<Inmueble> call, @NonNull Throwable throwable) {
            Log.d("salida", "Falla");
        }
    });
}

}