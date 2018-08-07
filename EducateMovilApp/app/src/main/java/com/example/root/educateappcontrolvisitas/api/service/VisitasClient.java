package com.example.root.educateappcontrolvisitas.api.service;


import com.example.root.educateappcontrolvisitas.api.model.Visita;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface VisitasClient {
    @GET("visitas/{date}.json")
    Call<List<Visita>> obtenerVisitasFecha(@Path("date") String date);
}

