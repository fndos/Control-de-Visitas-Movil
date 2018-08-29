package com.example.root.educateappcontrolvisitas.ui;

//import android.support.v7.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CheckOutActivity extends AppCompatActivity {

    private Button botonCHECKout;
    private double latitud, longitud;
    private GpsTracker gpsTracker;
    private boolean noSeHizoCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        final Visita visita = getIntent().getParcelableExtra("visitaActual");
        botonCHECKout = (Button) findViewById(R.id.btn_checkout);
        noSeHizoCheckOut = visita.getCoordinates_lat_out() == 0.0 && visita.getCoordinates_lon_out() == 0.0;
        if(noSeHizoCheckOut){
            botonCHECKout.setText("NO, CHECK OUT");
            try {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            botonCHECKout.setText("NO, SALIR");
        }










    }
}
