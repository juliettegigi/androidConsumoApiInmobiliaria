package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirEmailViewModel extends AndroidViewModel {
    public RecibirEmailViewModel(@NonNull Application application) {
        super(application);
    }

    public void actualizarPass(String token,String nuevaPass){
        Log.d("salida------ ", token);
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.actualizarPass(token,nuevaPass);
        Log.d("salida------dd ", nuevaPass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("salida------ ", response.body());
                    Archivos.guardarTokenArchivoPreferencia(getApplication(),response.body());

                    Intent intent=new Intent(getApplication(), MenuNavegable.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                } else {
                    Log.d("salida----", "Incorrecto"+response.body()+" "+response.code());
                 //   mutableMsgError.setValue("🚫Correo o contraseña incorrecta");

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                Log.d("salida", "Falla");
            }
        });
    }
}
