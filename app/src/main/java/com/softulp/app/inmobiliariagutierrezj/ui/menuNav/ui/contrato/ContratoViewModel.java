package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {
    private List<Inmueble> inmuebleList;
    private MutableLiveData<RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>> mutableAdapter;

    public ContratoViewModel(@NonNull Application application) {

        super(application);
        inmuebleList=new ArrayList<>();
    }

    public LiveData<RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>> getMutableAdapter(){
        if(mutableAdapter==null) {
            mutableAdapter = new MutableLiveData<RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>>();
            cargarInmuebles();
            RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>  adapter=new InmuebleAdapter3(inmuebleList,getApplication());
            mutableAdapter.setValue(adapter);

        }
        return mutableAdapter;
    }

    public void cargarInmuebles(){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        String token="Bearer "+ Archivos.leerTokenArchivoPreferencia(getApplication());
        Call<ArrayList<Inmueble>> call=api.getInmueblesConContrato(token);
        call.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()){
                    inmuebleList=response.body();
                    RecyclerView.Adapter<InmuebleAdapter3.ViewHolder3>  adapter=new InmuebleAdapter3(inmuebleList,getApplication());

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