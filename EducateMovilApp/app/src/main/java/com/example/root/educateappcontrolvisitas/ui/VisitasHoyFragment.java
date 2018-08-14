package com.example.root.educateappcontrolvisitas.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.example.root.educateappcontrolvisitas.api.service.VisitasClient;
import com.example.root.educateappcontrolvisitas.ui.adapter.VisitaAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.widget.AdapterView;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * create an instance of this fragment.
 */
public class VisitasHoyFragment extends Fragment {

    private ListView listView;
    private String fechaHoy;
    private String nombreUsuario;
    private String usuarioID;
    private List<Visita> visitasDeHoy;



    public VisitasHoyFragment() {
        // Required empty public constructor
    }

    public static VisitasHoyFragment newInstance(String usuario_id) {
        VisitasHoyFragment visitasHoyFragment = new VisitasHoyFragment();
        Bundle args = new Bundle();
        args.putString("usuario_id", usuario_id);
        visitasHoyFragment.setArguments(args);
        return visitasHoyFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usuarioID = getArguments().getString("usuario_id");
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visitas_hoy_lista, container, false);


        listView = (ListView) rootView.findViewById(R.id.visitaList);

        final TextView errorTextview = (TextView) rootView.findViewById(R.id.errorViewHoy);


        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        fechaHoy = formatter.format(todayDate);
        MainActivity activity = (MainActivity) getActivity();
        nombreUsuario = activity.getmUsername();
        usuarioID = activity.getIdentificadorUsuario();
        visitasDeHoy = new ArrayList<Visita>();





        //Conseguir visitas del API




        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://deveducate.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        VisitasClient client = retrofit.create(VisitasClient.class);
        Call<JsonObject> call = client.obtenerVisitas(usuarioID);

        ///////////////////////////////////////

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body() != null){

                    if(response.body() != null && response.body().getAsJsonArray("objects").size() > 0){
                        JsonElement visitasKeyUsuario = response.body().getAsJsonArray("objects");
                        int numeroVisitasTotal = ((JsonArray) visitasKeyUsuario).size();
                        System.out.println("La fecha de hoy es:  " +  fechaHoy);

                        System.out.println("---------------------------------BUscando las visitas de " + nombreUsuario + " con usuario_id = " + usuarioID + " -------------------------------------------");

                        System.out.println(nombreUsuario + " tiene: "+ numeroVisitasTotal + " visitas");
                        for(int i = 0; i< numeroVisitasTotal; i++){
                            System.out.println("Visita N° " + (i +1) );
                            JsonElement visitaActual = ((JsonArray) visitasKeyUsuario).get(i);
                            JsonElement requerimiento_visitaActual = visitaActual.getAsJsonObject().get("requerimiento");
                            JsonElement requerimiento_visitaActual_Escuela = requerimiento_visitaActual.getAsJsonObject().get("escuela");
                            JsonElement requerimiento_visitaActual_tipo_incidencia = requerimiento_visitaActual.getAsJsonObject().get("tipo_incidencia");
                            JsonElement requerimiento_visitaActual_tipo_seguimiento = requerimiento_visitaActual.getAsJsonObject().get("tipo_seguimiento");
                            JsonElement visitaActual_usuario = visitaActual.getAsJsonObject().get("usuario");


                            String requerimiento_visitaActual_Escuela_nombre = requerimiento_visitaActual_Escuela.getAsJsonObject().get("nombre").getAsString();
                            String requerimiento_visitaActual_Escuela_direccion = requerimiento_visitaActual_Escuela.getAsJsonObject().get("direccion").getAsString();
                            String requerimiento_visitaActual_Escuela_mie = requerimiento_visitaActual_Escuela.getAsJsonObject().get("amie").getAsString();
                            String requerimiento_visitaActual_Escuela_embajador_in = requerimiento_visitaActual_Escuela.getAsJsonObject().get("embajador_in").getAsString();
                            String requerimiento_visitaActual_Escuela_parroquia = requerimiento_visitaActual_Escuela.getAsJsonObject().get("parroquia").getAsString();
                            String requerimiento_visitaActual_fecha = requerimiento_visitaActual.getAsJsonObject().get("fecha").getAsString();
                            String requerimiento_visitaActual_fecha_visita = requerimiento_visitaActual.getAsJsonObject().get("fecha_visita").getAsString();
                            String requerimiento_visitaActual_hora_inicio_visita = requerimiento_visitaActual.getAsJsonObject().get("hora_inicio_visita").getAsString();
                            String requerimiento_visitaActual_hora_fin_visita = requerimiento_visitaActual.getAsJsonObject().get("hora_fin_visita").getAsString();
                            String requerimiento_visitaActual_motivo = requerimiento_visitaActual.getAsJsonObject().get("motivo").getAsString();
                            String requerimiento_visitaActual_tipo_incidencia_descripcion = requerimiento_visitaActual_tipo_incidencia.getAsJsonObject().get("descripcion").getAsString();
                            String requerimiento_visitaActual_tipo_seguimiento_descripcion = requerimiento_visitaActual_tipo_seguimiento.getAsJsonObject().get("descripcion").getAsString();
                            String requerimiento_visitaActual_tipo_seguimiento_tipo_estado = requerimiento_visitaActual_tipo_seguimiento.getAsJsonObject().get("tipo").getAsString();
                            String visitaActual_usuario_nombres = visitaActual_usuario.getAsJsonObject().get("nombres").getAsString();
                            String visitaActual_usuario_apellidos = visitaActual_usuario.getAsJsonObject().get("apellidos").getAsString();

                            boolean esVisitaDeHoy = requerimiento_visitaActual_fecha_visita.contains(fechaHoy);
                            if(esVisitaDeHoy){
                                System.out.println("----ESTA VISITA ES DE HOY. POR LO TANTO SE AGREGA A LA LISTA");
                                //Aqui creo una nueva visita y la agrego a la lista
                                Visita nuevaVisita = new Visita();
                                nuevaVisita.setNombreEscuela(requerimiento_visitaActual_Escuela_nombre);
                                nuevaVisita.setDireccion(requerimiento_visitaActual_Escuela_direccion);
                                nuevaVisita.setAmie(requerimiento_visitaActual_Escuela_mie);
                                nuevaVisita.setEmbajador_in(requerimiento_visitaActual_Escuela_embajador_in);
                                nuevaVisita.setParroquia(requerimiento_visitaActual_Escuela_parroquia);
                                nuevaVisita.setFecha(requerimiento_visitaActual_fecha);
                                nuevaVisita.setFecha_visita(requerimiento_visitaActual_fecha_visita);
                                nuevaVisita.setHora_inicio_visita(requerimiento_visitaActual_hora_inicio_visita);
                                nuevaVisita.setHora_fin_visita(requerimiento_visitaActual_hora_fin_visita);
                                nuevaVisita.setMotivo_visita(requerimiento_visitaActual_motivo);
                                nuevaVisita.setTipo_incidencia_descripcion(requerimiento_visitaActual_tipo_incidencia_descripcion);
                                nuevaVisita.setTipo_seguimiento_descripcion(requerimiento_visitaActual_tipo_seguimiento_descripcion);
                                nuevaVisita.setTipo_seguimiento_tipo_estado(requerimiento_visitaActual_tipo_seguimiento_tipo_estado);
                                nuevaVisita.setNombreUsuario(visitaActual_usuario_nombres);
                                nuevaVisita.setApellidoUsuario(visitaActual_usuario_apellidos);
                                nuevaVisita.setUsuarioID(usuarioID);
                                visitasDeHoy.add(nuevaVisita);



                            }


                            System.out.println("Nombre de la escuela: " + requerimiento_visitaActual_Escuela_nombre);
                            System.out.println("Direccion de la escuela: " + requerimiento_visitaActual_Escuela_direccion);
                            System.out.println("MIE de la escuela: " + requerimiento_visitaActual_Escuela_mie);
                            System.out.println("Embajador_IN de la escuela: " + requerimiento_visitaActual_Escuela_embajador_in);
                            System.out.println("Parroquia de la escuela: " + requerimiento_visitaActual_Escuela_parroquia);
                            System.out.println("Fecha: " + requerimiento_visitaActual_fecha);
                            System.out.println("Fecha de visita: " + requerimiento_visitaActual_fecha_visita);
                            System.out.println("Hora de inicio de visita: " + requerimiento_visitaActual_hora_inicio_visita);
                            System.out.println("Hora de fin de visita: " + requerimiento_visitaActual_hora_fin_visita);
                            System.out.println("Motivo de visita: " + requerimiento_visitaActual_motivo);
                            System.out.println("Incidencia de visita: " + requerimiento_visitaActual_tipo_incidencia_descripcion);
                            System.out.println("Incidencia de visita: " + requerimiento_visitaActual_tipo_seguimiento_descripcion);
                            System.out.println("Seguimiento de visita: " + requerimiento_visitaActual_tipo_incidencia_descripcion);
                            System.out.println("Estado de seguimiento de visita: " + requerimiento_visitaActual_tipo_seguimiento_tipo_estado);
                            System.out.println("-----------------------------------------------");


                        }
                        if(visitasDeHoy == null){
                            System.out.println("No hay nada");
                            errorTextview.setText("No hay visitas que mostrar");
                            listView.setVisibility(View.GONE);
                        }
                        else {
                            //Llamo al VisitaAdapter
                            System.out.println("Hay "+ visitasDeHoy.size() + " visitas de hoy");
                            listView.setAdapter(new VisitaAdapter(getActivity(), visitasDeHoy,R.color.tan_background));
                            listView.setVisibility(View.VISIBLE);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                    Visita visita = visitasDeHoy.get(position);

                                    Intent mainIntent = new Intent(getActivity(), DetallesEscuela.class);
                                    mainIntent.putExtra("visitaActual",visita);

                                    startActivity(mainIntent);



                                }
                            });

                        }


                    }
                    else{


                        System.out.println("---------------------------------BUscando las visitas de " + nombreUsuario + " con usuario_id = " + usuarioID + " -------------------------------------------");
                        System.out.println("----------------------------------Esto devuelve la API 2-------------------------------------------");
                        System.out.println("No hay nada");
                        errorTextview.setText("No hay visitas que mostrar");
                        listView.setVisibility(View.GONE);

                    }

                }
                else {
                    errorTextview.setText("No hay visitas que mostrar");
                    listView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                errorTextview.setText("No hay visitas que mostrar");
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();

            }
        });










        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();


    }



}
