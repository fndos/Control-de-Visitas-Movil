package com.example.root.educateappcontrolvisitas.ui;

import android.app.Activity;
import android.os.Bundle;

import com.example.root.educateappcontrolvisitas.R;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class TutorFormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_formulario);

        FormTutorPartA formTutorPartA = new FormTutorPartA();

        //FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fr_body, formTutorPartA);
        fragmentTransaction.commit();

       // fragmentManager.beginTransaction().add(R.id.fr_body, formTutorPartA).commit();

    }
}
