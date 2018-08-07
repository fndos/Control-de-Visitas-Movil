package com.example.root.educateappcontrolvisitas.ui;

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

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * create an instance of this fragment.
 */
public class VisitasHoyFragment extends Fragment {

    private String usuario;
    private ListView listView;
    private String fechaHoy;



    public VisitasHoyFragment() {
        // Required empty public constructor
    }

    public static VisitasHoyFragment newInstance(String usuario) {
        VisitasHoyFragment visitasHoyFragment = new VisitasHoyFragment();
        Bundle args = new Bundle();
        args.putString("usuario", usuario);
        visitasHoyFragment.setArguments(args);
        return visitasHoyFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.usuario = getArguments().getString("usuario");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visitas_hoy_lista, container, false);


        listView = (ListView) rootView.findViewById(R.id.visitaList);

        final TextView errorTextview = (TextView) rootView.findViewById(R.id.errorViewHoy);

        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        fechaHoy = formatter.format(todayDate);


        String baseUrl;
        baseUrl = "https://educatemovilapp.firebaseio.com/users/"+this.usuario+"/";
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        VisitasClient client = retrofit.create(VisitasClient.class);
        Call<List<Visita>> call = client.obtenerVisitasFecha(fechaHoy);

        call.enqueue(new Callback<List<Visita>>() {
            @Override
            public void onResponse(Call<List<Visita>> call, Response<List<Visita>> response) {
                if(response.body() != null){

                    List<Visita> visitas = response.body();

                    listView.setAdapter(new VisitaAdapter(getActivity(), visitas,R.color.tan_background));
                }
                else {
                    errorTextview.setText("No hay visitas que mostrar");

                }
            }

            @Override
            public void onFailure(Call<List<Visita>> call, Throwable t) {
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
