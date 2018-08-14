package com.example.root.educateappcontrolvisitas.ui;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.root.educateappcontrolvisitas.R;

import androidx.appcompat.app.AppCompatActivity;

public class TecnicoFormularioActivity extends AppCompatActivity {

    private ScrollView scrollEscuela;
    private TextView visitaHora;
    private TextView visitaNumero;
    private TextView escuelaMIE;
    private TextView nombre;
    private TextView direccion;
    private TextView labelEscuelaJornada;
    private TextView escuelaJornada;
    private TextView labelTieneInternet;
    private TextView tieneInternet;
    private TextView labelParroquia;
    private TextView parroquia;
    private TextView labelAsesor;
    private TextView asesor;
    private TextView labelMotivo;
    private TextView motivo;
    private TextView labelEmbajadorIN;
    private TextView embajadorIN;
    private TextView labelExtracurricular;
    private TextView extracurricular;
    private TextView labelVisitaConTecnico;
    private TextView visitaConTecnico;
    private TextView labelAccionTomada;
    private TextView accionTomada;
    private CheckBox chbocApci;
    private CheckBox chboxInternet;
    private CheckBox chboxSoftware;
    private CheckBox chboxHardware;
    private CheckBox chboxElectrico;
    private CheckBox chboxRedesycom;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico_formulario);


        scrollEscuela = (ScrollView) findViewById(R.id.scrollEscuela);
        visitaHora = (TextView) findViewById(R.id.visita_hora);
        visitaNumero = (TextView) findViewById(R.id.visita_numero);
        escuelaMIE = (TextView) findViewById(R.id.escuela_MIE);
        nombre = (TextView) findViewById(R.id.nombre);
        direccion = (TextView) findViewById(R.id.direccion);
        labelEscuelaJornada = (TextView) findViewById(R.id.label_escuela_jornada);
        escuelaJornada = (TextView) findViewById(R.id.escuela_jornada);
        labelTieneInternet = (TextView) findViewById(R.id.labelTieneInternet);
        tieneInternet = (TextView) findViewById(R.id.tieneInternet);
        labelParroquia = (TextView) findViewById(R.id.labelParroquia);
        parroquia = (TextView) findViewById(R.id.parroquia);
        labelAsesor = (TextView) findViewById(R.id.labelAsesor);
        asesor = (TextView) findViewById(R.id.asesor);
        labelMotivo = (TextView) findViewById(R.id.labelMotivo);
        motivo = (TextView) findViewById(R.id.motivo);
        labelEmbajadorIN = (TextView) findViewById(R.id.labelEmbajadorIN);
        embajadorIN = (TextView) findViewById(R.id.embajadorIN);
        labelExtracurricular = (TextView) findViewById(R.id.labelExtracurricular);
        extracurricular = (TextView) findViewById(R.id.extracurricular);
        labelVisitaConTecnico = (TextView) findViewById(R.id.labelVisitaConTecnico);
        visitaConTecnico = (TextView) findViewById(R.id.visitaConTecnico);
        labelAccionTomada = (TextView) findViewById(R.id.labelAccionTomada);
        accionTomada = (TextView) findViewById(R.id.accionTomada);
        chbocApci = (CheckBox) findViewById(R.id.chboc_apci);
        chboxInternet = (CheckBox) findViewById(R.id.chbox_internet);
        chboxSoftware = (CheckBox) findViewById(R.id.chbox_software);
        chboxHardware = (CheckBox) findViewById(R.id.chbox_hardware);
        chboxElectrico = (CheckBox) findViewById(R.id.chbox_electrico);
        chboxRedesycom = (CheckBox) findViewById(R.id.chbox_redesycom);


        btnSiguiente = (Button) findViewById(R.id.btn_siguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkOutIntent = new Intent(getApplicationContext(), CheckOutActivity.class);
                startActivity(checkOutIntent);
            }
        });

    }


}
