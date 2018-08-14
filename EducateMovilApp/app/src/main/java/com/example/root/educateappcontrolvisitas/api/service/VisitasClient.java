package com.example.root.educateappcontrolvisitas.api.service;


import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.google.gson.JsonObject;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface VisitasClient {
    @GET("visitas/{date}.json")
    Call<List<Visita>> obtenerVisitasFecha(@Path("date") String date);

    @GET("api/v1/controlvisita/?format=json")
    Call<JsonObject> obtenerVisitas(@Query("usuario") String usuario);
}

