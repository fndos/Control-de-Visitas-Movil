package com.example.root.educateappcontrolvisitas.ui;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.support.v4.app.*;

import com.example.root.educateappcontrolvisitas.R;

public class TutorFormulario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_formulario2);

        FormTutorPartA formTutorPartA = new FormTutorPartA();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
    }
}
