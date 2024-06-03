package com.softulp.app.inmobiliariagutierrezj.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.R;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.dialogos.Dialogos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecibirEmailViewModel extends AndroidViewModel {

    MutableLiveData<Visible> mutableVisible1;
    MutableLiveData<Visible> mutableVisible2;
    private MutableLiveData<Integer> mutableVerProgressBar;
    public RecibirEmailViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<Visible> getMutableVisible1() {
        if(mutableVisible1==null){
            Visible visible=new Visible(false, R.drawable.visibility_offp, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD,0);
            mutableVisible1=new MutableLiveData<>();
            mutableVisible1.setValue(visible);

        }
        return mutableVisible1;
    }
    public LiveData<Visible> getMutableVisible2() {
        if(mutableVisible2==null){
            Visible visible=new Visible(false, R.drawable.visibility_offp, InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD,0);
            mutableVisible2=new MutableLiveData<>();
            mutableVisible2.setValue(visible);

        }
        return mutableVisible2;
    }

    public LiveData<Integer> getMutableVerProgressBar() {
        if(mutableVerProgressBar==null){
            mutableVerProgressBar=new MutableLiveData<>();
        }
        return mutableVerProgressBar;
    }

    public void setMutableVerProgressBar(Integer i){
        mutableVerProgressBar.setValue(i);
    }

    public void cambiarVisible(int position,int mutable){
        Boolean isVisible;
        if(mutable==1)
          isVisible=mutableVisible1.getValue().isVisible();
        else isVisible=mutableVisible2.getValue().isVisible();

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
        if(mutable==1)
           mutableVisible1.setValue(v);
        else mutableVisible2.setValue(v);
    }

    public void actualizarPass(String token, String nuevaPass, String nuevaPass2, Context context){
        mutableVerProgressBar.setValue(View.VISIBLE);
        if(!nuevaPass.equals(nuevaPass2)){
            mutableVerProgressBar.setValue(View.GONE);
            Dialogos.dialogoPassDistintas(context);
            return;
        }

        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        Call<String> call= api.actualizarPass(token,nuevaPass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
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
