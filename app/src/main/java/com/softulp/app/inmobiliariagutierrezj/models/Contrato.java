package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Contrato implements Serializable {
    private int id;
    private int inmuebleId ;
    private Inmueble inmueble;

    private int inquilinoId ;
    private Inquilino inquilino;


    private float precioXmes ;
    private boolean estado ;
    private ArrayList<Pago> pagos;

    public Contrato(int id, int inmuebleId, Inmueble inmueble, int inquilinoId, Inquilino inquilino, float precioXmes, boolean estado, ArrayList<Pago> pagos) {
        this.id = id;
        this.inmuebleId = inmuebleId;
        this.inmueble = inmueble;
        this.inquilinoId = inquilinoId;
        this.inquilino = inquilino;
        this.precioXmes = precioXmes;
        this.estado = estado;
        this.pagos = pagos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInmuebleId() {
        return inmuebleId;
    }

    public void setInmuebleId(int inmuebleId) {
        this.inmuebleId = inmuebleId;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public int getInquilinoId() {
        return inquilinoId;
    }

    public void setInquilinoId(int inquilinoId) {
        this.inquilinoId = inquilinoId;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public float getPrecioXmes() {
        return precioXmes;
    }

    public void setPrecioXmes(float precioXmes) {
        this.precioXmes = precioXmes;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }
}
