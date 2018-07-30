package com.example.root.educateappcontrolvisitas;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * create an instance of this fragment.
 */
public class VisitasFragment extends Fragment {



    public VisitasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.visita_list, container, false);


        String[] escuelitas = {"UNIDAD EDUCATIVA AMERICO VESPUCIO",
                "ESCUELA DE EDUCACION BASICA PARTICULAR ANA MARIA IZA",
                "UNIDAD EDUCATIVA PARTICULAR BALTASARA CALDERON DE ROCAFUERTE",
                "ESCUELA DE EDUCACION BASICA PARTICULAR BEREA",
                "ESCUELA DE EDUCACION BASICA PARTICULAR CARMEN ACEVEDO",
                "ESCUELA DE EDUCACION BASICA PARTICULAR DIEGO NOBOA",
                "ESCUELA DE EDUCACIÓN BÁSICA PARTICULAR DOLORES SOPEÑA",
                "ESCUELA DE EDUCACION BASICA PARTICULAR DOLORES VEINTIMILLA DE GALINDO",
                "UNIDAD EDUCATIVA FISCOMISIONAL DOMINGO SAVIO",
                "ESCUELA DE EDUCACION BASICA PARTICULAR NELA MARTINEZ ESPINOZA"};

        String[] mieEscuelitas = {"09H02570","09H02033","09H02482","09H05244","09H02007","09H02064",
                "09H02573","09H05565","09H00486","09H02095"};

        String[] direccionEscuelitas = {"A dos cuadras del parque acuático COVIEM",
                "Ingresando por la escuela Padre Juan Velazco 5 cuadras al fondo y luego girar a la izquierda",
                "Detrás del colegio nacional Eloy Alfaro",
                "A dos cuadras del Parque Viernes Santo",
                "A 1 cuadra del Comisariato del Barrio del Seguro",
                "Frente al mercado trinitarias, por la estación de la línea 27",
                "Junto a la iglesia Nuestra Madre de Nazareth",
                "Dos Cuadras atrás de la Iglesia de la Dolorosa",
                "Junto a la Iglesia Domingo Savio",
                "Entrando por la calle Alberto Spencer"};

        String[] jornadaEscuelita = {"MATUTINA","VESPERTINA"};

        String[] horaVisitas = {"08H00","10H00","12H00","14H00","16H00","18H00"};

        String[] numeroVisitas = {"N°000000291","N°000000292","N°000000293","N°000000294","N°000000295","N°000000296",
                "N°000000297","N°000000298","N°000000299","N°000000300","N°000000301"};

        final ArrayList<Visita> visitas = new ArrayList<Visita>();
        for(int i = 0; i < 10; i++){


            visitas.add(new Visita(numeroVisitas[i], horaVisitas[i%2], mieEscuelitas[i],
                    escuelitas[i], jornadaEscuelita[i%2], direccionEscuelitas[i]));

        }


        VisitaAdapter adapter = new VisitaAdapter(getActivity(), visitas, R.color.tan_background);

        ListView listView = (ListView) rootView.findViewById(R.id.visitaList);

        listView.setAdapter(adapter);



        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();


    }



}
