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

public class RestablecerPassViewModel extends AndroidViewModel {
    public RestablecerPassViewModel(@NonNull Application application) {
        super(application);
    }


    public void enviarEmail(String email){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.enviarCorreo(email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){

                    Intent intent=new Intent(getApplication(), RecibirEmailActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Log.d("salida","whatt1");
                    getApplication().startActivity(intent);
                    Log.d("salida","whatt2");
                } else {
                    Log.d("salida", "Incorrecto");
                  //  mutableMsgError.setValue("🚫Correo o contraseña incorrecta");

                }

            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                Log.d("salida", "Falla");
            }
        });
    }

}
