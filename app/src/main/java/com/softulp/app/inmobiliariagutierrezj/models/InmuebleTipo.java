package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.util.ArrayList;

public class InmuebleTipo implements Serializable {
    private int id;
    private String tipo;
    private ArrayList<Inmueble> inmuebles;


    public InmuebleTipo(int id, String tipo, ArrayList<Inmueble> inmuebles) {
        this.id = id;
        this.tipo = tipo;
        this.inmuebles = inmuebles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(ArrayList<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    @Override
    public String toString() {
        return  tipo ;
    }
}
