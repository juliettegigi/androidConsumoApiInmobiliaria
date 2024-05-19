package com.softulp.app.inmobiliariagutierrezj.ui.menuNav.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.app.inmobiliariagutierrezj.models.InmuebleTipo;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayAdapter<String>> mutableAdapterInmuebleTipos;
    private MutableLiveData<ArrayAdapter<String>> mutableAdapterUsos;
    private MutableLiveData<Bitmap> mutableFoto;
    public CrearInmViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<ArrayAdapter<String>> getMutableInmuebleTipos(){
        if(mutableAdapterInmuebleTipos==null)
            mutableAdapterInmuebleTipos=new MutableLiveData<>();
        return mutableAdapterInmuebleTipos;
    }
    public MutableLiveData<ArrayAdapter<String>> getMutableAdapterUsos(){
        if(mutableAdapterUsos==null)
            mutableAdapterUsos=new MutableLiveData<>();
        return mutableAdapterUsos;
    }

    public LiveData<Bitmap> getMutableFoto(){
        if(mutableFoto==null){
            mutableFoto=new MutableLiveData<>();
        }
        return mutableFoto;
    }

    void setAdapterUsos(){

        String[] usos = {"Comercial", "Residencial"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, usos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mutableAdapterUsos.setValue(adapter);

    }


    void setAdapterInmuebleTipos(){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        String token="Bearer "+ Archivos.leerTokenArchivoPreferencia(getApplication());
        Call<ArrayList<InmuebleTipo>> call= api.getInmuebleTipos(token);
        call.enqueue(new Callback<ArrayList<InmuebleTipo>>() {
            @Override
            public void onResponse(Call<ArrayList<InmuebleTipo>> call, Response<ArrayList<InmuebleTipo>> response) {
                if(response.isSuccessful()){
                    ArrayList<InmuebleTipo> inmuebleTipos = response.body();
                    Map<Integer, String> tiposMap = new HashMap<>();

                    for (InmuebleTipo tipo : inmuebleTipos) {
                        tiposMap.put(tipo.getId(), tipo.getTipo());
                    }

                    // Convertir los valores del mapa a un arreglo de strings
                    String[] tiposArray = tiposMap.values().toArray(new String[0]);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, tiposArray);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mutableAdapterInmuebleTipos.setValue(adapter);
                }
                else if(response.code() == 204) {
                    Log.d("salida", "q caralio");
//respuesta cuando no se le pasa el token
                }


            }

            @Override
            public void onFailure(Call<ArrayList<InmuebleTipo>> call, Throwable throwable) {
                Log.d("salida", "Falla en vista de perfil");
            }
        });

    }

    public void recibirFotos(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Intent data = result.getData();
            if (data.getClipData() != null) {
                // Selección múltiple
                int count = data.getClipData().getItemCount();
                List<Uri> imageUris = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
                // Procesar las imágenes seleccionadas
                procesarImagenes(imageUris);
            } else if (data.getData() != null) {
                // Selección única
                Uri imageUri = data.getData();
                List<Uri> imageUris = new ArrayList<>();
                imageUris.add(imageUri);
                // Procesar la imagen seleccionada
                procesarImagenes(imageUris);
            }
        }
    }

    private void procesarImagenes(List<Uri> imageUris) {
        // Aquí puedes manejar las imágenes seleccionadas, por ejemplo, mostrar en un RecyclerView, etc.
        for (Uri uri : imageUris) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), uri);
                // Aquí puedes hacer algo con el bitmap, por ejemplo, agregarlo a una lista y mostrarlo en un ImageView o RecyclerView
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}