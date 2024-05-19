package com.softulp.app.inmobiliariagutierrezj.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;
import com.softulp.app.inmobiliariagutierrezj.models.InmuebleTipo;
import com.softulp.app.inmobiliariagutierrezj.models.Usuario;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClient {
    private static final String URL="http://192.168.16.100:5000/";
    private static final String URL2="http://192.168.0.91:5000/";
    private static MisEndpoints misEndpoints;

    public interface MisEndpoints{
        @FormUrlEncoded
        @POST("Propietario/login")
        Call<String> login(@Field("Usuario") String usuario,@Field("Pass") String pass);

        @GET("Propietario")
        Call<Usuario> getUsuarioLogueado(@Header("Authorization") String token);
        /*Call<Usuario> call= api.editarUsuario(token,nombre,apellido,dni,telefono,email);*/


        @PUT("Propietario")
        Call<Usuario> editarUsuario(@Header("Authorization") String token,
                                     @Body Usuario usuario);

        @GET("Propietario/misInmuebles")
        Call<ArrayList<Inmueble>> getMisInmuebles(@Header("Authorization") String token);

        @GET("InmuebleTipo")
        Call<ArrayList<InmuebleTipo>> getInmuebleTipos(@Header("Authorization") String token);

        @Multipart
        @POST("api/inmuebles")
        Call<Void> crearInmueble(
                @Part("InmuebleTipoId") RequestBody tipoId,
                @Part("Direccion") RequestBody direccion,
                @Part("CantidadAmbientes") RequestBody cantidadAmbientes,
                @Part("Uso") RequestBody uso,
                @Part("PrecioBase") RequestBody precioBase,
                @Part("CLatitud") RequestBody cLatitud,
                @Part("CLongitud") RequestBody cLongitud,
                @Part("Suspendido") RequestBody suspendido,
                @Part("Disponible") RequestBody disponible,
                @Part List<MultipartBody.Part> imagenes
        );
    


    }

    //parsear json a objeto java
    public static MisEndpoints getMisEndpoints(){  //retorno un objeto q implementa la interfaz q la implementa el retrofit
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL2)
                .addConverterFactory(GsonConverterFactory.create(gson)) //parsear json a objeto java
                .build();
         misEndpoints=retrofit.create(MisEndpoints.class);
        return misEndpoints;
    }
}
