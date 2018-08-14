package com.example.root.educateappcontrolvisitas.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Date;

public class Visita implements Parcelable {


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Visita createFromParcel(Parcel in) {
            return new Visita(in);
        }

        public Visita[] newArray(int size) {
            return new Visita[size];
        }
    };

    private String nombreUsuario;
    private String apellidoUsuario;
    private String usuarioID;


    private String amie;
    private String direccion;
    private String embajador_in;
    private String nombreEscuela;
    private String parroquia;
    private String fecha;
    private String fecha_visita;
    private String hora_fin_visita;
    private String hora_inicio_visita;
    private String motivo_visita;
    private String tipo_incidencia_descripcion;
    private String tipo_seguimiento_descripcion;
    private String tipo_seguimiento_tipo_estado;

    public Visita() {
    }

    public Visita (Parcel in){
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {

        nombreUsuario = in.readString();
        apellidoUsuario = in.readString();
        usuarioID = in.readString();
        amie = in.readString();
        direccion = in.readString();
        embajador_in = in.readString();
        nombreEscuela = in.readString();
        parroquia = in.readString();
        fecha = in.readString();
        fecha_visita = in.readString();
        hora_fin_visita = in.readString();
        hora_inicio_visita = in.readString();
        motivo_visita = in.readString();
        tipo_incidencia_descripcion = in.readString();
        tipo_seguimiento_descripcion = in.readString();
        tipo_seguimiento_tipo_estado = in.readString();


    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }



    public String getAmie() {
        return amie;
    }

    public void setAmie(String amie) {
        this.amie = amie;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmbajador_in() {
        return embajador_in;
    }

    public void setEmbajador_in(String embajador_in) {
        this.embajador_in = embajador_in;
    }

    public String getNombreEscuela() {
        return nombreEscuela;
    }

    public void setNombreEscuela(String nombreEscuela) {
        this.nombreEscuela = nombreEscuela;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha_visita() {
        return fecha_visita;
    }

    public void setFecha_visita(String fecha_visita) {
        this.fecha_visita = fecha_visita;
    }

    public String getHora_fin_visita() {
        return hora_fin_visita;
    }

    public void setHora_fin_visita(String hora_fin_visita) {
        this.hora_fin_visita = hora_fin_visita;
    }

    public String getHora_inicio_visita() {
        return hora_inicio_visita;
    }

    public void setHora_inicio_visita(String hora_inicio_visita) {
        this.hora_inicio_visita = hora_inicio_visita;
    }

    public String getMotivo_visita() {
        return motivo_visita;
    }

    public void setMotivo_visita(String motivo_visita) {
        this.motivo_visita = motivo_visita;
    }

    public String getTipo_incidencia_descripcion() {
        return tipo_incidencia_descripcion;
    }

    public void setTipo_incidencia_descripcion(String tipo_incidencia_descripcion) {
        this.tipo_incidencia_descripcion = tipo_incidencia_descripcion;
    }

    public String getTipo_seguimiento_descripcion() {
        return tipo_seguimiento_descripcion;
    }

    public void setTipo_seguimiento_descripcion(String tipo_seguimiento_descripcion) {
        this.tipo_seguimiento_descripcion = tipo_seguimiento_descripcion;
    }

    public String getTipo_seguimiento_tipo_estado() {
        return tipo_seguimiento_tipo_estado;
    }

    public void setTipo_seguimiento_tipo_estado(String tipo_seguimiento_tipo_estado) {
        this.tipo_seguimiento_tipo_estado = tipo_seguimiento_tipo_estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeString(nombreUsuario);
        parcel.writeString(apellidoUsuario);
        parcel.writeString(usuarioID);
        parcel.writeString(amie);
        parcel.writeString(direccion);
        parcel.writeString(embajador_in);
        parcel.writeString(nombreEscuela);
        parcel.writeString(parroquia);
        parcel.writeString(fecha);
        parcel.writeString(fecha_visita);
        parcel.writeString(hora_fin_visita);
        parcel.writeString(hora_inicio_visita);
        parcel.writeString(motivo_visita);
        parcel.writeString(tipo_incidencia_descripcion);
        parcel.writeString(tipo_seguimiento_descripcion);
        parcel.writeString(tipo_seguimiento_tipo_estado);






    }
}
