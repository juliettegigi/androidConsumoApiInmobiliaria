package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestablecerPassViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> mutableLoginStatus;
    MutableLiveData<String> mutableMsgError;
    public RestablecerPassViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Boolean> getMutableLoginStatus() {
        if(mutableLoginStatus==null)
            mutableLoginStatus=new MutableLiveData<>();
        return mutableLoginStatus;
    }

    public LiveData<String> getMutableMsgError(){
        if(mutableMsgError==null)
            mutableMsgError=new MutableLiveData<>();
        return  mutableMsgError;
    }

    public void enviarEmail(String email){

        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.enviarCorreo(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    mutableLoginStatus.setValue(true);
                    Intent intent=new Intent(getApplication(), EsperandoCorreoActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                } else {
                    Log.d("salida", "Incorrecto");
                    mutableLoginStatus.setValue(false);
                   mutableMsgError.setValue("🚫 El email ingresado no existe.");

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                Log.d("salida Retrofit", "Error: " + throwable.getMessage());
                Log.d("salida", "Fallazz");
            }
        });
    }

}
