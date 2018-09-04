package com.example.root.educateappcontrolvisitas.ui;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.example.root.educateappcontrolvisitas.api.service.VisitasClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckOutActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        return;
    }

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

        botonCHECKout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuarioFK = "/serviceweb/api/v1/usuario/" + visita.getUser_id() + "/";
                String requerimientoFK = "/serviceweb/api/v1/requirement/"+visita.getRequirement_id()+ "/";
                Date todayDate = Calendar.getInstance().getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final String fechaHoy = formatter.format(todayDate);
                getLocation();

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://deveducate.pythonanywhere.com/")
                        .addConverterFactory(GsonConverterFactory.create());
                Retrofit retrofit = builder.build();

                VisitasClient visitasClient = retrofit.create(VisitasClient.class);
                Call<Void> call =  visitasClient.checkOut("system","ABC123456789",1000,usuarioFK,requerimientoFK,visita.getDate_planned()
                        ,fechaHoy,latitud,longitud,2,visita.getType(),visita.getCheck_in(),visita.getCoordinates_lat_in(),visita.getCoordinates_lon_in(),visita.getId());

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        startActivity(new Intent(CheckOutActivity.this,MainActivity.class));
                        finish();

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {



                    }
                });
            }
        });
    }

    public void getLocation(){
        gpsTracker = new GpsTracker(CheckOutActivity.this);
        if(gpsTracker.canGetLocation()){
            this.latitud = gpsTracker.getLatitude();
            this.longitud = gpsTracker.getLongitude();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }


}
