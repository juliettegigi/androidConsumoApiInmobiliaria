package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.app.Application;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    MutableLiveData<String> mutableMsgError;
    MutableLiveData<Visible> mutableVisible;
    private MutableLiveData<Integer> mutableLoginStatus;
    public MainViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMutableMsgError(){
        if(mutableMsgError==null)
            mutableMsgError=new MutableLiveData<>();
        return  mutableMsgError;
    }

    public LiveData<Integer> getMutableLoginStatus() {
        if(mutableLoginStatus==null){
            mutableLoginStatus=new MutableLiveData<>();
        }
        return mutableLoginStatus;
    }

    public void setMutableLoginStatus(int i) {
        this.mutableLoginStatus.setValue(i);
    }

    public LiveData<Visible> getMutableVisible() {
        if(mutableVisible==null){
            Visible visible=new Visible(false,R.drawable.visibility_offp,InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD,0);
            mutableVisible=new MutableLiveData<>();
           mutableVisible.setValue(visible);

        }
        return mutableVisible;
    }

    public void cambiarVisible(int position){
        Boolean isVisible=mutableVisible.getValue().isVisible();
        Visible v=new Visible();
          if(isVisible){
              v.setImagen(R.drawable.visibility_offp);
              v.setTipoInput(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);

          }
          else{
              v.setImagen(R.drawable.visibilityp);
              v.setTipoInput(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

          }
        v.setVisible(!isVisible);
          v.setCursorPosition(position);
        mutableVisible.setValue(v);
    }
    public void validar(String email,String pass){
        mutableLoginStatus.setValue(View.VISIBLE);
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.login(email,pass);
        Log.d("salida",pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Archivos.guardarTokenArchivoPreferencia(getApplication(),response.body());

                    Intent intent=new Intent(getApplication(), MenuNavegable.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    getApplication().startActivity(intent);
                } else {
                    Log.d("salida", "Incorrecto");
                     mutableMsgError.setValue("🚫Correo o contraseña incorrecta");

                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable throwable) {
                Log.d("salida", "Falla");
            }
        });
    }
}
