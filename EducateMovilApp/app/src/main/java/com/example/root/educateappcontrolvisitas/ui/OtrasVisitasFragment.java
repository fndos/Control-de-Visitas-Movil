package com.example.root.educateappcontrolvisitas.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
//&import android.support.v13.app.Fragment;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.example.root.educateappcontrolvisitas.api.service.VisitasClient;
import com.example.root.educateappcontrolvisitas.ui.adapter.VisitaAdapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.DatePickerDialog;

import androidx.fragment.app.DialogFragment;





/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * create an instance of this fragment.
 */
public class OtrasVisitasFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private String nombreUsuario;
    private String usuarioID;
    private ListView listView;
    private String fechaEscogida;
    private TextView errorTextview;
    private View rootView;
    private List<Visita> visitasDeFecha;
    private View barraCargando;



    ImageButton selectDate;
    TextView dateToActivate;

    VisitaAdapter visitaAdapter;

    public OtrasVisitasFragment() {
        // Required empty public constructor
    }

    public static OtrasVisitasFragment newInstance(String usuario_id) {
        OtrasVisitasFragment otrasVisitasFragment = new OtrasVisitasFragment();
        Bundle args = new Bundle();
        args.putString("usuario_id", usuario_id);
        otrasVisitasFragment.setArguments(args);
        return otrasVisitasFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usuarioID = getArguments().getString("usuario_id");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.otras_visitas_lista, container, false);


        MainActivity activity = (MainActivity) getActivity();
        nombreUsuario = activity.getmUsername();
        usuarioID = activity.getIdentificadorUsuario();
        selectDate = (ImageButton)rootView.findViewById(R.id.ib_obtener_fecha); // getting the image button in fragment_blank.xml
        dateToActivate = (TextView) rootView.findViewById(R.id.et_mostrar_fecha_picker); // getting the TextView in fragment_blank.xml
        selectDate.setOnClickListener(new View.OnClickListener() {  // setting listener for user click event
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment(); // creating DialogFragment which creates DatePickerDialog
                newFragment.setTargetFragment(OtrasVisitasFragment.this,0);  // Passing this fragment DatePickerFragment.
                // As i figured out this is the best way to keep the reference to calling activity when using FRAGMENT.
                newFragment.show(getFragmentManager(),"datePicker");
            }
        });

        barraCargando = (ProgressBar)rootView.findViewById(R.id.busqueda_otras_visitas_progreso);






        return rootView;
    }




    @Override
    public void onStop() {
        super.onStop();


    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        String dia,mes;
        if(dayOfMonth < 10){
            Integer helper = new Integer(dayOfMonth);
            dia = "0"+ helper.toString();
        }
        else{
            Integer helper = new Integer(dayOfMonth);
            dia = helper.toString();
        }

        monthOfYear = monthOfYear + 1;
        if(monthOfYear < 10){
            Integer helper = new Integer(monthOfYear);
            mes = "0"+ helper.toString();
        }
        else{
            Integer helper = new Integer(monthOfYear);
            mes = helper.toString();
        }


        StringBuilder fechasb = new StringBuilder().append(year).append("-").append(mes).append("-").append(dia);
        String formattedDate = fechasb.toString();
        dateToActivate.setText(formattedDate);
        fechaEscogida = formattedDate;

        listView = (ListView) rootView.findViewById(R.id.otrasVisitasList);

        errorTextview = (TextView) rootView.findViewById(R.id.errorViewOtras);
        errorTextview.setText("");
        visitasDeFecha = new ArrayList<Visita>();
        barraCargando.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);



        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://deveducate.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        VisitasClient visitasClient = retrofit.create(VisitasClient.class);
        Call<JsonObject> call =  visitasClient.obtenerVisitas("system","ABC123456789",100);

        ///////////////////////////////////////

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.body() != null){

                    if(response.body() != null && response.body().getAsJsonArray("objects").size() > 0){
                        JsonElement todasLasVisitasdeBD = response.body().getAsJsonArray("objects");

                        int totalVisitas = ((JsonArray) todasLasVisitasdeBD).size();



                        System.out.println("Hay " + totalVisitas + " visitas en la BD");
                        barraCargando.setVisibility(View.VISIBLE);




                        for(int i = 0;  i < totalVisitas ; i++){
                            JsonElement visita = ((JsonArray) todasLasVisitasdeBD).get(i);
                            //System.out.println("----------------------------------------------------" );
                            //System.out.println("Visita N° " + (i+1));
                            //System.out.println(visita);
                            JsonElement visita_requerimiento = visita.getAsJsonObject().get("requirement");
                            JsonElement visita_user = visita.getAsJsonObject().get("user");

                            JsonElement check_in =  visita.getAsJsonObject().get("check_in");
                            String visita_checkIn;
                            if(check_in.isJsonNull()){
                                visita_checkIn = "null";

                            }
                            else{
                                visita_checkIn = visita.getAsJsonObject().get("check_in").getAsString();
                            }
                            JsonElement checkout = visita.getAsJsonObject().get("check_out");
                            String visita_checkOut;
                            if(checkout.isJsonNull()){
                                visita_checkOut = "null";
                            }
                            else{

                                visita_checkOut = visita.getAsJsonObject().get("check_out").getAsString();
                            }

                            JsonElement lat_in = visita.getAsJsonObject().get("coordinates_lat_in");
                            double visita_lat_in;
                            if(lat_in.isJsonNull()){
                                visita_lat_in = 0.0;
                            }
                            else {
                                visita_lat_in = visita.getAsJsonObject().get("coordinates_lat_in").getAsDouble();
                            }

                            JsonElement lat_out = visita.getAsJsonObject().get("coordinates_lat_out");
                            double visita_lat_out;
                            if(lat_out.isJsonNull()){
                                visita_lat_out = 0.0;
                            }
                            else{
                                visita_lat_out = visita.getAsJsonObject().get("coordinates_lat_out").getAsDouble();
                            }
                            JsonElement lon_in = visita.getAsJsonObject().get("coordinates_lon_in");
                            double visita_lon_in;
                            if(lon_in.isJsonNull()){
                                visita_lon_in = 0.0;
                            }
                            else {
                                visita_lon_in = visita.getAsJsonObject().get("coordinates_lon_in").getAsDouble();
                            }
                            JsonElement lon_out = visita.getAsJsonObject().get("coordinates_lon_out");
                            double visita_lon_out;
                            if(lon_out.isJsonNull()){
                                visita_lon_out = 0.0;
                            }
                            else{

                                visita_lon_out = visita.getAsJsonObject().get("coordinates_lon_out").getAsDouble();
                            }

                            String visita_date_planned = visita.getAsJsonObject().get("date_planned").getAsString();
                            int visita_id = visita.getAsJsonObject().get("id").getAsInt();
                            int requerimiento_id = visita_requerimiento.getAsJsonObject().get("id").getAsInt();
                            String requerimiento_reason = visita_requerimiento.getAsJsonObject().get("reason").getAsString();
                            JsonElement visita_Escuela = visita_requerimiento.getAsJsonObject().get("school");
                            String escuela_direccion = visita_Escuela.getAsJsonObject().get("address").getAsString();
                            String escuela_embajador_in = visita_Escuela.getAsJsonObject().get("ambassador_in").getAsString();
                            String escuela_amie = visita_Escuela.getAsJsonObject().get("amie").getAsString();
                            String escuela_nombre = visita_Escuela.getAsJsonObject().get("name").getAsString();
                            String escuela_parroquia = visita_Escuela.getAsJsonObject().get("parish").getAsString();
                            String escuela_referencia = visita_Escuela.getAsJsonObject().get("reference").getAsString();
                            JsonElement estado_visita = visita.getAsJsonObject().get("state");
                            int visita_estado;
                            if(estado_visita.isJsonNull()){
                                visita_estado = 1;
                            }
                            else{
                                visita_estado = visita.getAsJsonObject().get("state").getAsInt();
                            }

                            JsonElement visita_tipo_json = visita.getAsJsonObject().get("type");
                            int visita_tipo;
                            if(visita_tipo_json.isJsonNull()){
                                visita_tipo = 0;

                            }
                            else {
                                visita_tipo = visita.getAsJsonObject().get("type").getAsInt();
                            }
                            int tipo_usuario = visita_user.getAsJsonObject().get("user_type").getAsInt();

                            int usuario_id = visita_user.getAsJsonObject().get("id").getAsInt();
                            String usuario_username = visita_user.getAsJsonObject().get("username").getAsString();


                            int idUsuario = Integer.parseInt(usuarioID);
                            System.out.println("-------------------------------------------------------------------------------------");
                            System.out.println("Visita N° " + i);
                            System.out.println("Datos de visita buscada " + fechaEscogida +","+nombreUsuario + ","+idUsuario);
                            System.out.println("Datos de visita actual " + visita_date_planned + ","+ usuario_username + ","+usuario_id);
                            boolean esDeEstaFecha = visita_date_planned.contains(fechaEscogida) && usuario_username.equals(nombreUsuario) && usuario_id == idUsuario;
                            System.out.println("Es de esta fecha? " + esDeEstaFecha);
                            if(esDeEstaFecha){

                                Visita nuevaVisita = new Visita();

                                Date todayDate = Calendar.getInstance().getTime();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaHoy = formatter.format(todayDate);

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                                Date date1 = null;
                                Date date2 = null;
                                boolean fechaExpirada = false;
                                try {
                                    date1 = sdf.parse(fechaEscogida);
                                    date2 = sdf.parse(fechaHoy);
                                    fechaExpirada = date1.compareTo(date2) < 0;


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }




                                nuevaVisita.setCheck_in(visita_checkIn);
                                nuevaVisita.setCheck_out(visita_checkOut);
                                nuevaVisita.setCoordinates_lat_in(visita_lat_in);
                                nuevaVisita.setCoordinates_lon_in(visita_lon_in);
                                nuevaVisita.setCoordinates_lat_out(visita_lat_out);
                                nuevaVisita.setCoordinates_lon_out(visita_lon_out);
                                nuevaVisita.setDate_planned(visita_date_planned);
                                nuevaVisita.setId(visita_id);
                                nuevaVisita.setRequirement_id(requerimiento_id);
                                nuevaVisita.setRequirement_reason(requerimiento_reason);
                                nuevaVisita.setSchool_address(escuela_direccion);
                                nuevaVisita.setSchool_ambassador_in(escuela_embajador_in);
                                nuevaVisita.setSchool_amie(escuela_amie);
                                nuevaVisita.setSchool_name(escuela_nombre);
                                nuevaVisita.setSchool_parish(escuela_parroquia);
                                nuevaVisita.setSchool_reference(escuela_referencia);
                                if(fechaExpirada){
                                    nuevaVisita.setState(3);

                                }
                                else {
                                    nuevaVisita.setState(visita_estado);

                                }
                                nuevaVisita.setType(visita_tipo);
                                nuevaVisita.setUser_type(tipo_usuario);
                                nuevaVisita.setUser_id(usuario_id);
                                nuevaVisita.setUsername(usuario_username);
                                visitasDeFecha.add(nuevaVisita);
                            }




                        }
                        if(visitasDeFecha == null){
                            System.out.println("No hay nada");
                            listView.setVisibility(View.GONE);
                            errorTextview.setText("NO HAY VISITAS QUE MOSTRAR");
                            barraCargando.setVisibility(View.GONE);
                            //errorTextview.setVisibility(View.VISIBLE);

                        }
                        else{
                            System.out.println("Hay " + visitasDeFecha.size() + " visitas de esta fecha " + fechaEscogida);
                           visitaAdapter = new VisitaAdapter(getActivity(), visitasDeFecha);

                            listView.setAdapter(visitaAdapter);
                            errorTextview.setText("NO HAY VISITAS QUE MOSTRAR");
                            barraCargando.setVisibility(View.GONE);
                            listView.setVisibility(View.VISIBLE);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                    Visita visita = visitasDeFecha.get(position);

                                    if(visita.getState() !=3){
                                        Intent mainIntent = new Intent(getActivity(), DetallesEscuela.class);
                                        mainIntent.putExtra("visitaActual",visita);

                                        startActivity(mainIntent);
                                    }



                                }
                            });


                        }



                    }
                    else{
                        System.out.println("No hay nada");
                        //errorTextview.setText("No hay visitas que mostrar");
                        listView.setVisibility(View.GONE);
                        errorTextview.setText("NO HAY VISITAS QUE MOSTRAR");
                        barraCargando.setVisibility(View.GONE);
                        //errorTextview.setVisibility(View.VISIBLE);


                    }

                }
                else {
                    errorTextview.setText("No hay visitas que mostrar");
                    //errorTextview.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                    barraCargando.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                errorTextview.setText("No hay visitas que mostrar");
                barraCargando.setVisibility(View.GONE);
                //errorTextview.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();
            }
        });



        //Conseguir visitas del API
/*



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
                        System.out.println("La fecha de hoy es:  " +  fechaEscogida);

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


                            boolean esVisitaDeFecha = requerimiento_visitaActual_fecha_visita.contains(fechaEscogida);
                            if(esVisitaDeFecha){
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
                                visitasDeFecha.add(nuevaVisita);



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
                        if(visitasDeFecha == null){
                            System.out.println("No hay nada");
                            errorTextview.setText("No hay visitas que mostrar");
                            listView.setVisibility(View.GONE);

                        }
                        else {
                            //Llamo al VisitaAdapter
                            System.out.println("Hay "+ visitasDeFecha.size() + " visitas de hoy");
                            visitaAdapter = new VisitaAdapter(getActivity(), visitasDeFecha,R.color.tan_background);

                            listView.setAdapter(visitaAdapter);
                            listView.setVisibility(View.VISIBLE);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                    Visita visita = visitasDeFecha.get(position);

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





*/




    }
}
