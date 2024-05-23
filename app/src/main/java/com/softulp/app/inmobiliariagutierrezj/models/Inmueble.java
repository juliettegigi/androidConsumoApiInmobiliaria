package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.util.ArrayList;



public class Inmueble implements Serializable {
    private int id;
    private int propietarioId;
    private int inmuebleTipoId;
    private String direccion;
    private int cantidadAmbientes;



    private Uso uso;
    private float precioBase;
    private float cLatitud;
    private float cLongitud;
    private boolean suspendido;
    private boolean disponible;


    private Usuario propietario;
    private ArrayList<ImagenInmueble> imagenes;
    private InmuebleTipo inmuebleTipo;
    public enum Uso {
        Comercial,
        Residencial
    }

    public Inmueble(int id, int propietarioId, int inmuebleTipoId, String direccion, int cantidadAmbientes, Uso uso, float precioBase, float cLatitud, float cLongitud, boolean suspendido, boolean disponible, Usuario propietario, ArrayList<ImagenInmueble> imagenes, InmuebleTipo inmuebleTipo) {
        this.id = id;
        this.propietarioId = propietarioId;
        this.inmuebleTipoId = inmuebleTipoId;
        this.direccion = direccion;
        this.cantidadAmbientes = cantidadAmbientes;
        this.uso = uso;
        this.precioBase = precioBase;
        this.cLatitud = cLatitud;
        this.cLongitud = cLongitud;
        this.suspendido = suspendido;
        this.disponible = disponible;
        this.propietario = propietario;
        this.imagenes = imagenes;
        this.inmuebleTipo = inmuebleTipo;
    }



    public Uso getUso() {
        return uso;
    }

    public void setUso(Uso uso) {
        this.uso = uso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public int getInmuebleTipoId() {
        return inmuebleTipoId;
    }

    public void setInmuebleTipoId(int inmuebleTipoId) {
        this.inmuebleTipoId = inmuebleTipoId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(int cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }

    public float getcLatitud() {
        return cLatitud;
    }

    public void setcLatitud(float cLatitud) {
        this.cLatitud = cLatitud;
    }

    public float getcLongitud() {
        return cLongitud;
    }

    public void setcLongitud(float cLongitud) {
        this.cLongitud = cLongitud;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    public ArrayList<ImagenInmueble> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<ImagenInmueble> imagenes) {
        this.imagenes = imagenes;
    }

    public InmuebleTipo getInmuebleTipo() {
        return inmuebleTipo;
    }
    public boolean getDisponible() {
        return disponible;
    }
    public boolean getSuspendido() {
        return suspendido;
    }

    public void setInmuebleTipo(InmuebleTipo inmuebleTipo) {
        this.inmuebleTipo = inmuebleTipo;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "id=" + id +
                ", propietarioId=" + propietarioId +
                ", inmuebleTipoId=" + inmuebleTipoId +
                ", direccion='" + direccion + '\'' +
                ", cantidadAmbientes=" + cantidadAmbientes +
                ", precioBase=" + precioBase +
                ", cLatitud=" + cLatitud +
                ", cLongitud=" + cLongitud +
                ", suspendido=" + suspendido +
                ", disponible=" + disponible +
                ", propietario=" + propietario +
                ", imagenes=" + imagenes +
                ", inmuebleTipo=" + inmuebleTipo +
                '}';
    }
}
