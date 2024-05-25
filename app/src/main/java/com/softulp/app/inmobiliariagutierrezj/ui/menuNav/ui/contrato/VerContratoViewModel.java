package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.contrato;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.models.Contrato;

public class VerContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mutableContrato;
    public VerContratoViewModel(@NonNull Application application) {

        super(application);
    }

    public LiveData<Contrato> getMutableContrato(){
        if(mutableContrato==null)
            mutableContrato=new MutableLiveData<>();
        return mutableContrato;
    }
    public void recibirContrato(Bundle bundle){
        Contrato contrato=(Contrato) bundle.get("contrato");

        if(contrato!=null){
            mutableContrato.setValue(contrato);


        }

    }
}