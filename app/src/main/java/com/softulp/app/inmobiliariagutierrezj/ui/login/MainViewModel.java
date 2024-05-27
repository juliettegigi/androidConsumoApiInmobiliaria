package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    MutableLiveData<String> mutableMsgError;
    private MutableLiveData<Boolean> mutableLoginStatus;
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMutableMsgError(){
        if(mutableMsgError==null)
            mutableMsgError=new MutableLiveData<>();
        return  mutableMsgError;
    }

    public LiveData<Boolean> getMutableLoginStatus() {
        if(mutableLoginStatus==null)
            mutableLoginStatus=new MutableLiveData<>();
        return mutableLoginStatus;
    }
    public void validar(String email,String pass){

        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.login(email,pass);
        Log.d("salida",pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Archivos.guardarTokenArchivoPreferencia(getApplication(),response.body());
                    mutableLoginStatus.setValue(true);
                    Intent intent=new Intent(getApplication(), MenuNavegable.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                } else {
                    Log.d("salida", "Incorrecto");
                     mutableMsgError.setValue("🚫Correo o contraseña incorrecta");
                    mutableLoginStatus.setValue(false);

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                Log.d("salida", "Falla");
            }
        });
    }
}
