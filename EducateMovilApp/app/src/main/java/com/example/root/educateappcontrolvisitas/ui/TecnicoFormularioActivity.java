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
import com.example.root.educateappcontrolvisitas.api.model.Visita;

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
        final Visita visita = getIntent().getParcelableExtra("visitaActual");


        /*

        TextView visita_escuela_hora = (TextView) findViewById(R.id.visita_hora);
        visita_escuela_hora.setText("00h00");
        TextView visita_escuela_numero = (TextView) findViewById(R.id.visita_numero);
        visita_escuela_numero.setText("N°000000293");
        TextView visita_escuela_MIE = (TextView) findViewById(R.id.escuela_MIE);
        visita_escuela_MIE.setText(visita.getSchool_amie());
        TextView visita_escuela_nombre = (TextView) findViewById(R.id.nombre);
        visita_escuela_nombre.setText(visita.getSchool_name());
        TextView visita_escuela_direccion = (TextView) findViewById(R.id.direccion);
        visita_escuela_direccion.setText(visita.getSchool_address());
        TextView visita_escuela_jornada = (TextView) findViewById(R.id.escuela_jornada);
        visita_escuela_jornada.setText("VESPERTINA");
        TextView visita_escuela_internet = (TextView) findViewById(R.id.tieneInternet);
        visita_escuela_internet.setText("SI");
        TextView visita_escuela_parroquia = (TextView) findViewById(R.id.parroquia);
        visita_escuela_parroquia.setText(visita.getSchool_parish());
        TextView visita_escuela_asesor = (TextView) findViewById(R.id.asesor);
        visita_escuela_asesor.setText("N/A");
        TextView visita_escuela_motivo = (TextView) findViewById(R.id.motivo);
        visita_escuela_motivo.setText(visita.getRequirement_reason());
        TextView visita_escuela_embajadorIN = (TextView) findViewById(R.id.embajadorIN);
        visita_escuela_embajadorIN.setText(visita.getSchool_ambassador_in());
        TextView visita_escuela_extracurricular = (TextView) findViewById(R.id.extracurricular);
        visita_escuela_extracurricular.setText("N/A");
        TextView visita_escuela_conTecnico = (TextView) findViewById(R.id.visitaConTecnico);
        visita_escuela_conTecnico.setText("N/A");
        TextView visita_escuela_accionTomada = (TextView) findViewById(R.id.accionTomada);
        visita_escuela_accionTomada.setText("N/A");



         */



        scrollEscuela = (ScrollView) findViewById(R.id.scrollEscuela);
        visitaHora = (TextView) findViewById(R.id.visita_hora);
        visitaHora.setText("00H00");
        visitaNumero = (TextView) findViewById(R.id.visita_numero);
        visitaNumero.setText("N°000000293");
        escuelaMIE = (TextView) findViewById(R.id.escuela_MIE);
        escuelaMIE.setText(visita.getSchool_amie());
        nombre = (TextView) findViewById(R.id.nombre);
        nombre.setText(visita.getSchool_name());
        direccion = (TextView) findViewById(R.id.direccion);
        direccion.setText(visita.getSchool_address());
        labelEscuelaJornada = (TextView) findViewById(R.id.label_escuela_jornada);
        escuelaJornada = (TextView) findViewById(R.id.escuela_jornada);
        escuelaJornada.setText("N/A");
        labelTieneInternet = (TextView) findViewById(R.id.labelTieneInternet);
        tieneInternet = (TextView) findViewById(R.id.tieneInternet);
        tieneInternet.setText("N/A");
        labelParroquia = (TextView) findViewById(R.id.labelParroquia);
        parroquia = (TextView) findViewById(R.id.parroquia);
        parroquia.setText(visita.getSchool_parish());
        labelAsesor = (TextView) findViewById(R.id.labelAsesor);
        asesor = (TextView) findViewById(R.id.asesor);
        asesor.setText("N/A");
        labelMotivo = (TextView) findViewById(R.id.labelMotivo);
        motivo = (TextView) findViewById(R.id.motivo);
        motivo.setText(visita.getRequirement_reason());
        labelEmbajadorIN = (TextView) findViewById(R.id.labelEmbajadorIN);
        embajadorIN = (TextView) findViewById(R.id.embajadorIN);
        embajadorIN.setText(visita.getSchool_ambassador_in());
        labelExtracurricular = (TextView) findViewById(R.id.labelExtracurricular);
        extracurricular = (TextView) findViewById(R.id.extracurricular);
        extracurricular.setText("N/A");
        labelVisitaConTecnico = (TextView) findViewById(R.id.labelVisitaConTecnico);
        visitaConTecnico = (TextView) findViewById(R.id.visitaConTecnico);
        visitaConTecnico.setText("N/A");
        labelAccionTomada = (TextView) findViewById(R.id.labelAccionTomada);
        accionTomada = (TextView) findViewById(R.id.accionTomada);
        accionTomada.setText("N/A");
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
                checkOutIntent.putExtra("visitaActual",visita);
                startActivity(checkOutIntent);
            }
        });

    }


}
