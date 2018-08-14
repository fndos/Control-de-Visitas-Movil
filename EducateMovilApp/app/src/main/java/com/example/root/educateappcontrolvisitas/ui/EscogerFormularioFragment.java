package com.example.root.educateappcontrolvisitas.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
///import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.root.educateappcontrolvisitas.R;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EscogerFormularioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EscogerFormularioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EscogerFormularioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String nombreUsuario;
    private String usuarioID;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView;
    private Button btnPedagogico;
    private Button btnTecnico;
    private Button btnAmbos;


    private OnFragmentInteractionListener mListener;

    public EscogerFormularioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param usuario_id Parameter 1.
     *
     * @return A new instance of fragment EscogerFormularioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EscogerFormularioFragment newInstance(String usuario_id) {
        EscogerFormularioFragment fragment = new EscogerFormularioFragment();
        Bundle args = new Bundle();
        args.putString("usuario_id", usuario_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.usuarioID = getArguments().getString("usuario_id");
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_escoger_formulario, container, false);

        //textView = (TextView) findViewById(R.id.textView);
        btnPedagogico = (Button) view.findViewById(R.id.btn_pedagogico);
        btnTecnico = (Button) view.findViewById(R.id.btn_tecnico);
        btnAmbos = (Button) view.findViewById(R.id.btn_ambos);


        btnTecnico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formularioTecnico = new Intent(getContext(),TecnicoFormularioActivity.class);
                startActivity(formularioTecnico);
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
