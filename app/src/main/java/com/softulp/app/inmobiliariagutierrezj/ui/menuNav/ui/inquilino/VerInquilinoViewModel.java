package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inquilino;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.models.Inquilino;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;

public class VerInquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Inquilino> mutableInquilino;
    public VerInquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getMutableInquilino(){
        if(mutableInquilino==null)
            mutableInquilino=new MutableLiveData<>();
        return mutableInquilino;
    }
    public void recibirInquilino(Bundle bundle){
        Inquilino inquilino=(Inquilino) bundle.get("inquilino");

        if(inquilino!=null){
            mutableInquilino.setValue(inquilino);


        }

    }
}