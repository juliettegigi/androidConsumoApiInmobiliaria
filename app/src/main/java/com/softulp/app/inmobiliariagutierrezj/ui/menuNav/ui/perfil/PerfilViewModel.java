package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.perfil;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;
import com.softulp.app.inmobiliariagutierrezj.ui.menuNav.MenuNavegable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> mutableUsuario;
    private MutableLiveData<String> mutableTextoBoton;
    private MutableLiveData<Boolean> mutableEsGuardar;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public  LiveData<String> getMutableTextoBoton(){
        if(mutableTextoBoton==null) {
            mutableTextoBoton = new MutableLiveData<>();
            mutableTextoBoton.setValue("Editar");
        }
        return mutableTextoBoton;
    }
    public LiveData<Usuario> getMutableUsuario(){
        if(mutableUsuario==null){
            mutableUsuario=new MutableLiveData<>();
        }
        return mutableUsuario;
    }
    public  LiveData<Boolean> getMutableEsGuardar(){
        if(mutableEsGuardar==null){
            mutableEsGuardar=new MutableLiveData<>();
            mutableEsGuardar.setValue(false);
        }
        return mutableEsGuardar;
    }

    public void setMutableUsuario() {
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();//clase q creó el retrofit
        String token="Bearer "+Archivos.leerTokenArchivoPreferencia(getApplication());
        Call<Usuario> call= api.getUsuarioLogueado(token);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    mutableUsuario.setValue(response.body());
                }
                else if(response.code() == 204) {
                    Log.d("salida", "q caralio");
//respuesta cuando no se le pasa el token
                }


            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable throwable) {
                Log.d("salida", "Falla en vista de perfil");
            }
        });



    }

    public void editarDatos(String textoBoton,String nombre,String apellido,String dni,String telefono,String email){
          if(textoBoton.equals("Editar")){
              mutableTextoBoton.setValue("Guardar");
              mutableEsGuardar.setValue(true);


          }
          else {
              ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();//clase q creó el retrofit
              String token="Bearer "+Archivos.leerTokenArchivoPreferencia(getApplication());
              Usuario user=new Usuario(nombre,apellido,dni,telefono,email);
              Call<Usuario> call= api.editarUsuario(token,user);
              Log.d("salida",call.request().toString());
              call.enqueue(new Callback<Usuario>() {
                  @Override
                  public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                      if(response.isSuccessful()){
                          mutableUsuario.setValue(response.body());
                          mutableEsGuardar.setValue(false);
                          mutableTextoBoton.setValue("Editar");

                      }
                      else {
                          Log.d("salida",response.message());
                      }


                  }

                  @Override
                  public void onFailure(Call<Usuario> call, Throwable throwable) {
                      Log.d("salida", "Falla en vista de perfil");
                  }
              });
          }
    }
}