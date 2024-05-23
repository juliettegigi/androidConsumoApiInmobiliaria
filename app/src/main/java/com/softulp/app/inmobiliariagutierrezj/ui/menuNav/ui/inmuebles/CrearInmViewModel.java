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
import androidx.recyclerview.widget.RecyclerView;

import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.models.InmuebleTipo;
import com.softulp.app.inmobiliariagutierrezj.models.Usuario;
import com.softulp.app.inmobiliariagutierrezj.request.ApiClient;
import com.softulp.app.inmobiliariagutierrezj.request.Archivos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmViewModel extends AndroidViewModel {

        private MutableLiveData<ArrayAdapter<InmuebleTipo>> mutableAdapterInmuebleTipos;
        private MutableLiveData<ArrayAdapter<String>> mutableAdapterUsos;
        private MutableLiveData<RecyclerView.Adapter<ImgInmAdapter.ViewHolder>> mutableAdapterRV;


    public CrearInmViewModel(@NonNull Application application) {

        super(application);
    }

    public MutableLiveData<ArrayAdapter<InmuebleTipo>> getMutableInmuebleTipos(){
        if(mutableAdapterInmuebleTipos==null)
            mutableAdapterInmuebleTipos=new MutableLiveData<>();
        return mutableAdapterInmuebleTipos;
    }

    public LiveData<RecyclerView.Adapter<ImgInmAdapter.ViewHolder>> getMutableAdapterRV(){
        if(mutableAdapterRV==null) {
            mutableAdapterRV = new MutableLiveData<RecyclerView.Adapter<ImgInmAdapter.ViewHolder>>();

        }
        return mutableAdapterRV;
    }
    public MutableLiveData<ArrayAdapter<String>> getMutableAdapterUsos(){
        if(mutableAdapterUsos==null)
            mutableAdapterUsos=new MutableLiveData<>();
        return mutableAdapterUsos;
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


                    ArrayAdapter<InmuebleTipo> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, inmuebleTipos);
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
        if (result.getResultCode() == RESULT_OK && result.getData() != null) { //result.getData() contiene el intent con los datos seleccionado
            Intent data = result.getData();
            if (data.getClipData() != null) {  //ClipData contiene una lista de ClipData.Item cada uno de los cuales representa un URI de una imagen seleccionada.
                int count = data.getClipData().getItemCount();
                List<Uri> imageUris = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    imageUris.add(imageUri);
                }
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


        RecyclerView.Adapter<ImgInmAdapter.ViewHolder>  adapter=new ImgInmAdapter(imageUris,getApplication());
        mutableAdapterRV.setValue(adapter);
    }


 public void AltaInmueble(int tipoInmuble,String direccion,String cantidadAmbientes,String uso,String precioBase){
        ApiClient.MisEndpoints api = ApiClient.getMisEndpoints();
        String token="Bearer "+Archivos.leerTokenArchivoPreferencia(getApplication());

        RequestBody InmuebleTipoId = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(tipoInmuble));
        RequestBody Direccion = RequestBody.create(MediaType.parse("text/plain"), direccion);
        RequestBody CantidadAmbientes = RequestBody.create(MediaType.parse("text/plain"), cantidadAmbientes);
        RequestBody Uso = RequestBody.create(MediaType.parse("text/plain"), uso);
        RequestBody PrecioBase = RequestBody.create(MediaType.parse("text/plain"), precioBase);
        RequestBody cLatitud = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody cLongitud = RequestBody.create(MediaType.parse("text/plain"), "0");
        RequestBody suspendido = RequestBody.create(MediaType.parse("text/plain"), "false");
        RequestBody disponible = RequestBody.create(MediaType.parse("text/plain"), "true");
     RecyclerView.Adapter<ImgInmAdapter.ViewHolder> adapter = mutableAdapterRV.getValue();
     ImgInmAdapter imgInmAdapter = (ImgInmAdapter) adapter;
     List<Bitmap> listaDeImagenes = imgInmAdapter.getLista(getApplication());

     List<MultipartBody.Part> partesDeImagenes = new ArrayList<>();

// Recorrer la lista de bitmaps y convertir cada uno en una parte multipart
     for (Bitmap bitmap : listaDeImagenes) {
         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
         bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
         RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), byteArrayOutputStream.toByteArray());
         MultipartBody.Part parteDeImagen = MultipartBody.Part.createFormData("imagenes", "nombre_de_archivo.jpg", requestBody);
         partesDeImagenes.add(parteDeImagen);
     }



     Log.d("salida", String.valueOf(listaDeImagenes.size()));

        Call<Inmueble> call= api.postInmueble(token,InmuebleTipoId,Direccion,CantidadAmbientes,Uso,PrecioBase,cLatitud,cLongitud,suspendido,disponible, partesDeImagenes);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                   Log.d("salida","weno");
                }
                else if(response.code() == 204) {
                    Log.d("salida", "q caralio");
//respuesta cuando no se le pasa el token
                }


            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable throwable) {
                Log.d("salida", "whaat"+throwable.getMessage());
            }
        });

    }
}