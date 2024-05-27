package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.models.Contrato;
import com.softulp.app.inmobiliariagutierrezj.models.Pago;

import java.util.ArrayList;

public class VerPagosFragmentViewModel extends AndroidViewModel {
    private ArrayList<Pago> pagos;
    private MutableLiveData<RecyclerView.Adapter<PagosAdapter.ViewHolder>> mutableAdapter;
    public VerPagosFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<RecyclerView.Adapter<PagosAdapter.ViewHolder>> getMutableAdapter(){
        if(mutableAdapter==null){
            mutableAdapter=new MutableLiveData<>();
            Log.d("salida  tamaño de la lista",pagos.size()+"");
            Log.d("salida ",pagos.get(0).toString());
            Log.d("salida ",pagos.get(1).toString());
            RecyclerView.Adapter<PagosAdapter.ViewHolder> adapter=new PagosAdapter(pagos,getApplication());
            mutableAdapter.setValue(adapter);
        }
        return mutableAdapter;
    }




    public void  recibirPagos(Bundle bundle){
         pagos= (ArrayList<Pago>) bundle.get("pagos");


    }
}