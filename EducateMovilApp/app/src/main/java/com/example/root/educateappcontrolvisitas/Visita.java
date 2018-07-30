package com.example.root.educateappcontrolvisitas;

import java.sql.Time;
import java.util.Date;

public class Visita {

    private String mNumeroVisita;

    private String mFecha;

    private String mHora;

    private String mMIE;

    private String mNombreEscuela;

    private String mJornadaEscuela;

    private String mDireccionEscuela;

    public Visita(String mNumeroVisita, String mHora, String mMIE, String mNombreEscuela, String mJornadaEscuela, String mDireccionEscuela) {
        this.mNumeroVisita = mNumeroVisita;
        this.mHora = mHora;
        this.mMIE = mMIE;
        this.mNombreEscuela = mNombreEscuela;
        this.mJornadaEscuela = mJornadaEscuela;
        this.mDireccionEscuela = mDireccionEscuela;
    }

    public String getmNumeroVisita() {
        return mNumeroVisita;
    }

    public void setmNumeroVisita(String mNumeroVisita) {
        this.mNumeroVisita = mNumeroVisita;
    }

    public String getmFecha() {
        return mFecha;
    }

    public void setmFecha(String mFecha) {
        this.mFecha = mFecha;
    }

    public String getmHora() {
        return mHora;
    }

    public void setmHora(String mHora) {
        this.mHora = mHora;
    }

    public String getmMIE() {
        return mMIE;
    }

    public void setmMIE(String mMIE) {
        this.mMIE = mMIE;
    }

    public String getmNombreEscuela() {
        return mNombreEscuela;
    }

    public void setmNombreEscuela(String mNombreEscuela) {
        this.mNombreEscuela = mNombreEscuela;
    }

    public String getmJornadaEscuela() {
        return mJornadaEscuela;
    }

    public void setmJornadaEscuela(String mJornadaEscuela) {
        this.mJornadaEscuela = mJornadaEscuela;
    }

    public String getmDireccionEscuela() {
        return mDireccionEscuela;
    }

    public void setmDireccionEscuela(String mDireccionEscuela) {
        this.mDireccionEscuela = mDireccionEscuela;
    }
}
