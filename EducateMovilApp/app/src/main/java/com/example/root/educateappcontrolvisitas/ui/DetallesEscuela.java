package com.example.root.educateappcontrolvisitas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.ui.WorkaroundMapFragment;

public class DetallesEscuela extends AppCompatActivity{// implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ScrollView mScrollView;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String usuarioID;

    private Button botonCHECKIN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escuela_item);

        final Visita visita = getIntent().getParcelableExtra("visitaActual");
        nombresUsuario = visita.getNombreUsuario();
        apellidosUsuario = visita.getApellidoUsuario();
        usuarioID = visita.getUsuarioID();
        TextView visita_escuela_hora = (TextView) findViewById(R.id.visita_hora);
        visita_escuela_hora.setText(visita.getHora_inicio_visita());
        TextView visita_escuela_numero = (TextView) findViewById(R.id.visita_numero);
        visita_escuela_numero.setText("N°000000293");
        TextView visita_escuela_MIE = (TextView) findViewById(R.id.escuela_MIE);
        visita_escuela_MIE.setText(visita.getAmie());
        TextView visita_escuela_nombre = (TextView) findViewById(R.id.nombre);
        visita_escuela_nombre.setText(visita.getNombreEscuela());
        TextView visita_escuela_direccion = (TextView) findViewById(R.id.direccion);
        visita_escuela_direccion.setText(visita.getDireccion());
        TextView visita_escuela_jornada = (TextView) findViewById(R.id.escuela_jornada);
        visita_escuela_jornada.setText("VESPERTINA");
        TextView visita_escuela_internet = (TextView) findViewById(R.id.tieneInternet);
        visita_escuela_internet.setText("SI");
        TextView visita_escuela_parroquia = (TextView) findViewById(R.id.parroquia);
        visita_escuela_parroquia.setText(visita.getParroquia());
        TextView visita_escuela_asesor = (TextView) findViewById(R.id.asesor);
        visita_escuela_asesor.setText("N/A");
        TextView visita_escuela_motivo = (TextView) findViewById(R.id.motivo);
        visita_escuela_motivo.setText(visita.getMotivo_visita());
        TextView visita_escuela_embajadorIN = (TextView) findViewById(R.id.embajadorIN);
        visita_escuela_embajadorIN.setText(visita.getEmbajador_in());
        TextView visita_escuela_extracurricular = (TextView) findViewById(R.id.extracurricular);
        visita_escuela_extracurricular.setText("N/A");
        TextView visita_escuela_conTecnico = (TextView) findViewById(R.id.visitaConTecnico);
        visita_escuela_conTecnico.setText("N/A");
        TextView visita_escuela_accionTomada = (TextView) findViewById(R.id.accionTomada);
        visita_escuela_accionTomada.setText("N/A");


        botonCHECKIN = (Button) findViewById(R.id.botonCHECKIN);
        botonCHECKIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Lanzando", "formulario");
                Intent escogerFormularios = new Intent(getApplicationContext(), EscogerFormularioActivity.class);
                startActivity(escogerFormularios);
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
                    LatLng escuelita = new LatLng(-2.101717, -79.926966);
                    mMap.addMarker(new MarkerOptions().position(escuelita).title(visita.getNombreEscuela()));
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
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(19);
        //mMap.setMaxZoomPreference(20);
        //escuelita coordinates
        LatLng escuelita = new LatLng(-2.101717, -79.926966);
        mMap.addMarker(new MarkerOptions().position(escuelita).title("Escuelita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(escuelita));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    }*/
}

