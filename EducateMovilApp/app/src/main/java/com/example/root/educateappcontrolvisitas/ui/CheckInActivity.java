package com.example.root.educateappcontrolvisitas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.educateappcontrolvisitas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CheckInActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    private RelativeLayout visitaTextContainer;
    private TextView visitaHora;
    private TextView visitaNumero;
    private TextView escuelaMIE;
    private TextView escuelaJornada;
    private TextView escuelaNombre;
    private TextView escuelaDireccion;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);

        visitaTextContainer = (RelativeLayout) findViewById(R.id.visita_text_container);
        visitaHora = (TextView) findViewById(R.id.visita_hora);
        visitaNumero = (TextView) findViewById(R.id.visita_numero);
        escuelaMIE = (TextView) findViewById(R.id.escuela_MIE);
        escuelaJornada = (TextView) findViewById(R.id.escuela_jornada);
        escuelaNombre = (TextView) findViewById(R.id.escuela_nombre);
        escuelaDireccion = (TextView) findViewById(R.id.escuela_direccion);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EscogerFormularioFragment escogerFormularioFragment = new EscogerFormularioFragment();
                
            }
        });
       

    }

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

    }
}
