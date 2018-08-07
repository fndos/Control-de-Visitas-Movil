package com.example.root.educateappcontrolvisitas.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
//&import android.support.v13.app.Fragment;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.educateappcontrolvisitas.R;
import com.example.root.educateappcontrolvisitas.api.model.Visita;
import com.example.root.educateappcontrolvisitas.api.service.VisitasClient;
import com.example.root.educateappcontrolvisitas.ui.adapter.VisitaAdapter;

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


    private String usuario;
    private ListView listView;
    private String fechaEscogida;
    private TextView errorTextview;
    private View rootView;

    ImageButton selectDate;
    TextView dateToActivate;

    VisitaAdapter visitaAdapter;

    public OtrasVisitasFragment() {
        // Required empty public constructor
    }

    public static OtrasVisitasFragment newInstance(String usuario) {
        OtrasVisitasFragment otrasVisitasFragment = new OtrasVisitasFragment();
        Bundle args = new Bundle();
        args.putString("usuario", usuario);
        otrasVisitasFragment.setArguments(args);
        return otrasVisitasFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usuario = getArguments().getString("usuario");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.otras_visitas_lista, container, false);


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

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        fechaEscogida = formatter.format(todayDate);
        //dateToActivate.setText(fechaEscogida);




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

        StringBuilder sb = new StringBuilder().append(dia).append("-").append(mes).append("-").append(year);
        String formattedDate = sb.toString();
        dateToActivate.setText(formattedDate);
        fechaEscogida = formattedDate;

        listView = (ListView) rootView.findViewById(R.id.otrasVisitasList);

        errorTextview = (TextView) rootView.findViewById(R.id.errorViewOtras);


        String baseUrl;
        baseUrl = "https://educatemovilapp.firebaseio.com/users/"+this.usuario+"/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        VisitasClient client = retrofit.create(VisitasClient.class);
        Call<List<Visita>> call = client.obtenerVisitasFecha(fechaEscogida);

        call.enqueue(new Callback<List<Visita>>() {
            @Override
            public void onResponse(Call<List<Visita>> call, Response<List<Visita>> response) {
                if(response.body() != null){

                    List<Visita> visitas = response.body();
                    visitaAdapter = new VisitaAdapter(getActivity(), visitas,R.color.tan_background);

                    listView.setAdapter(visitaAdapter);
                    listView.setVisibility(View.VISIBLE);

                }
                else {
                    errorTextview.setText("No hay visitas que mostrar");
                    listView.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<Visita>> call, Throwable t) {
                errorTextview.setText("No hay visitas que mostrar");
                Toast.makeText(getActivity(), "error :(", Toast.LENGTH_SHORT).show();

            }
        });





    }
}
