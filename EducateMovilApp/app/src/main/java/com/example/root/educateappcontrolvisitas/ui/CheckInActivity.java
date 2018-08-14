package com.example.root.educateappcontrolvisitas.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.root.educateappcontrolvisitas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CheckInActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
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
