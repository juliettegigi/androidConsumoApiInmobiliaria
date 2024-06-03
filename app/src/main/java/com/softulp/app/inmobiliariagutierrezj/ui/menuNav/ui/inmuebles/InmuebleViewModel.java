package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private List<Inmueble> inmuebleList;
   private MutableLiveData<RecyclerView.Adapter<InmuebleAdapter.ViewHolder>> mutableAdapter;

    public InmuebleViewModel(@NonNull Application application) {

        super(application);
        inmuebleList=new ArrayList<>();
    }

    public LiveData<RecyclerView.Adapter<InmuebleAdapter.ViewHolder>> getMutableAdapter(){
        if(mutableAdapter==null) {
            mutableAdapter = new MutableLiveData<RecyclerView.Adapter<InmuebleAdapter.ViewHolder>>();
            cargarInmuebles();
            RecyclerView.Adapter<InmuebleAdapter.ViewHolder>  adapter=new InmuebleAdapter(inmuebleList,getApplication());
            mutableAdapter.setValue(adapter);

        }
        return mutableAdapter;
    }

    public void cargarInmuebles(){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        String token="Bearer "+ Archivos.leerTokenArchivoPreferencia(getApplication());
        Call<ArrayList<Inmueble>> call=api.getMisInmuebles(token);
        call.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmuebleList=response.body();
                    RecyclerView.Adapter<InmuebleAdapter.ViewHolder>  adapter=new InmuebleAdapter(inmuebleList,getApplication());
                    mutableAdapter.setValue(adapter);

                }
                else if(response.code() == 204) {
                    Log.d("salida", "q caralio");

                }


            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable throwable) {
                Log.d("salida", "Falla en vista de perfil");
            }
        });
    }
}