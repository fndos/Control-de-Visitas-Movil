package com.example.root.educateappcontrolvisitas.api.service;

import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuariosClient {
    @GET("serviceweb/api/v1/usuario/?format=json")
    Call<JsonObject> obtenerNombreUsuario(@Query("username") String username);


    @POST("serviceweb/api/v1/usuario/login/?format=json")
    @FormUrlEncoded
    Call<JsonObject> login(@Field("us") String us,
                       @Field("ps") String ps

   );

    @GET("api/v1/usuario/?format=json")
    Call<JsonObject> obtenerUsuarioId(@Query("username") String username);
}
