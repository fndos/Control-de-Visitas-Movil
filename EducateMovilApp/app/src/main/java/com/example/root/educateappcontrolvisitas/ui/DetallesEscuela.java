package com.example.root.educateappcontrolvisitas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.example.root.educateappcontrolvisitas.api.service.VisitasClient;
import com.example.root.educateappcontrolvisitas.ui.adapter.VisitaAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.ui.WorkaroundMapFragment;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DetallesEscuela extends AppCompatActivity{// implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ScrollView mScrollView;
    private String nombresUsuario;
    private String apellidosUsuario;
    private int usuarioID;

    private Button botonCHECKIN;
    private double latitud, longitud;
    private GpsTracker gpsTracker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escuela_item);

        final Visita visita = getIntent().getParcelableExtra("visitaActual");
        nombresUsuario = visita.getUsername();
        apellidosUsuario = visita.getUsername();
        usuarioID = visita.getUser_id();
        TextView visita_escuela_hora = (TextView) findViewById(R.id.visita_hora);
        visita_escuela_hora.setText("00h00");
        TextView visita_escuela_numero = (TextView) findViewById(R.id.visita_numero);
        visita_escuela_numero.setText("N°000000293");
        TextView visita_escuela_MIE = (TextView) findViewById(R.id.escuela_MIE);
        visita_escuela_MIE.setText(visita.getSchool_amie());
        TextView visita_escuela_nombre = (TextView) findViewById(R.id.nombre);
        visita_escuela_nombre.setText(visita.getSchool_name());
        TextView visita_escuela_direccion = (TextView) findViewById(R.id.direccion);
        visita_escuela_direccion.setText(visita.getSchool_address());
        TextView visita_escuela_jornada = (TextView) findViewById(R.id.escuela_jornada);
        visita_escuela_jornada.setText("VESPERTINA");
        TextView visita_escuela_internet = (TextView) findViewById(R.id.tieneInternet);
        visita_escuela_internet.setText("SI");
        TextView visita_escuela_parroquia = (TextView) findViewById(R.id.parroquia);
        visita_escuela_parroquia.setText(visita.getSchool_parish());
        TextView visita_escuela_asesor = (TextView) findViewById(R.id.asesor);
        visita_escuela_asesor.setText("N/A");
        TextView visita_escuela_motivo = (TextView) findViewById(R.id.motivo);
        visita_escuela_motivo.setText(visita.getRequirement_reason());
        TextView visita_escuela_embajadorIN = (TextView) findViewById(R.id.embajadorIN);
        visita_escuela_embajadorIN.setText(visita.getSchool_ambassador_in());
        TextView visita_escuela_extracurricular = (TextView) findViewById(R.id.extracurricular);
        visita_escuela_extracurricular.setText("N/A");
        TextView visita_escuela_conTecnico = (TextView) findViewById(R.id.visitaConTecnico);
        visita_escuela_conTecnico.setText("N/A");
        TextView visita_escuela_accionTomada = (TextView) findViewById(R.id.accionTomada);
        visita_escuela_accionTomada.setText("N/A");

        botonCHECKIN = (Button) findViewById(R.id.botonCHECKIN);

        //final boolean noSeHaHechoCheckIn= visita.getCheck_in().contains("null");
        final boolean noSeHaHechoCheckIn= visita.getCoordinates_lat_in() == 0.0 && visita.getCoordinates_lon_in() == 0.0;
        if(noSeHaHechoCheckIn){
            botonCHECKIN.setText("CHECK IN");
            try {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            botonCHECKIN.setText("VER FORMULARIOS");
        }


        botonCHECKIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(noSeHaHechoCheckIn){
                    //Aqui va el post
                    String usuarioFK = "/serviceweb/api/v1/usuario/" + visita.getUser_id() + "/";
                    String requerimientoFK = "/serviceweb/api/v1/requirement/"+visita.getRequirement_id()+ "/";
                    Date todayDate = Calendar.getInstance().getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String fechaHoy = formatter.format(todayDate);
                    getLocation();

                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://deveducate.pythonanywhere.com/")
                            .addConverterFactory(GsonConverterFactory.create());
                    Retrofit retrofit = builder.build();

                    VisitasClient visitasClient = retrofit.create(VisitasClient.class);
                    Call<Void> call =  visitasClient.checkIn("system","ABC123456789",usuarioFK,requerimientoFK,visita.getDate_planned()
                    ,fechaHoy,latitud,longitud,visita.getState(),visita.getType(),visita.getId());

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            botonCHECKIN.setText("VER FORMULARIOS");
                            if(visita.getUser_type() == 1 || visita.getUser_type() == 3){
                                Intent escogerFormularios = new Intent(getApplicationContext(), EscogerFormularioActivity.class);
                                escogerFormularios.putExtra("visitaActual",visita);
                                startActivity(escogerFormularios);
                            }
                            else {
                                Intent formularioTecnico = new Intent(getApplicationContext(), EscogerFormularioActivity.class);
                                formularioTecnico.putExtra("visitaActual",visita);
                                startActivity(formularioTecnico);
                            }

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {



                        }
                    });






                }

                if(visita.getUser_type() == 1 || visita.getUser_type() == 3){
                    Intent escogerFormularios = new Intent(getApplicationContext(), EscogerFormularioActivity.class);
                    escogerFormularios.putExtra("visitaActual",visita);
                    startActivity(escogerFormularios);
                }
                else {
                    Intent formularioTecnico = new Intent(getApplicationContext(), TecnicoFormularioActivity.class);
                    formularioTecnico.putExtra("visitaActual",visita);
                    startActivity(formularioTecnico);
                }
            }
        });


        if (mMap == null) {
            SupportMapFragment mapFragment = (WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap)
                {
                    mMap = googleMap;
                    mMap.setMinZoomPreference(19);
                    //mMap.setMaxZoomPreference(20);
                    //escuelita coordinates
                    LatLng escuelita = getLocationFromAddress(visita.getSchool_address());//= new LatLng(-2.101717, -79.926966);
                    mMap.addMarker(new MarkerOptions().position(escuelita).title(visita.getSchool_name()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(escuelita));
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                    mScrollView = findViewById(R.id.scrollEscuela); //parent scrollview in xml, give your scrollview id value
                    ((WorkaroundMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                            .setListener(new WorkaroundMapFragment.OnTouchListener() {
                                @Override
                                public void onTouch()
                                {
                                    mScrollView.requestDisallowInterceptTouchEvent(true);
                                }

                            });
                }
            });
        }
    }

    public LatLng getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());

            return p1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }

    public void getLocation(){
        gpsTracker = new GpsTracker(DetallesEscuela.this);
        if(gpsTracker.canGetLocation()){
            this.latitud = gpsTracker.getLatitude();
            this.longitud = gpsTracker.getLongitude();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }




}

