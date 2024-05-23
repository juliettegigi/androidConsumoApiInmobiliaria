package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;

import java.util.ArrayList;

public class MasIfoViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mutableInmueble;
    private MutableLiveData<String> mutableImagen;
    private int i;
    public MasIfoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getMutableInmueble(){
        if(mutableInmueble==null)
            mutableInmueble=new MutableLiveData<>();
        return mutableInmueble;
    }
    public LiveData<String> getMutableImagen(){
        if(mutableImagen==null){
            mutableImagen=new MutableLiveData<>();
            i=0;
        }
        return mutableImagen;
    }

    public void recibirInmueble(Bundle bundle){

        Inmueble inmueble=(Inmueble) bundle.get("inmueble");

        if(inmueble!=null){
            mutableInmueble.setValue(inmueble);
            if(!inmueble.getImagenes().isEmpty())
                mutableImagen.setValue(ApiClient.getURL() +inmueble.getImagenes().get(i).getImagen());
            else mutableImagen.setValue(null);

        }

    }

    public void getImg(int num){
        Log.d("valori",i+"");
        if(mutableInmueble.getValue().getImagenes().isEmpty()) {
            mutableImagen.setValue(null);
            return;
        }
        if(i+num==-1)
            i=mutableInmueble.getValue().getImagenes().size()-1;
        else
            i=(i+num)%mutableInmueble.getValue().getImagenes().size();

        mutableImagen.setValue(ApiClient.getURL() +mutableInmueble.getValue().getImagenes().get(i).getImagen());
    }
}