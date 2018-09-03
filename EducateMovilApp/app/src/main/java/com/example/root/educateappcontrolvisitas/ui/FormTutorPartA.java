package com.example.root.educateappcontrolvisitas.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.educateappcontrolvisitas.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormTutorPartA extends Fragment {


    public FormTutorPartA() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_form_tutor_part, container, false);

        return rootView;
    }

}
