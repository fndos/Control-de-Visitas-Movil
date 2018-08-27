package com.example.root.educateappcontrolvisitas.api.service;

import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuariosClient {
    @GET("api/v1/usuario/?format=json")
    Call<JsonObject> obtenerNombreUsuario(@Query("user_name") String user_name);

    @GET("api/v1/usuario/?format=json")
    Call<JsonObject> obtenerUsuarioId(@Query("key_usuario") String key_usuario,
            @Query("user_name") String user_name);
}
