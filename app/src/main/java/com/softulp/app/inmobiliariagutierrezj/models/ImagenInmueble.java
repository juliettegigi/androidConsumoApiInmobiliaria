package com.softulp.app.inmobiliariagutierrezj.models;

public class ImagenInmueble {
    private int id;
    private int inmuebleId;
    private String imagen;

    private  Inmueble inmueble;

    public ImagenInmueble(int id, int inmuebleId, String imagen, Inmueble inmueble) {
        this.id = id;
        this.inmuebleId = inmuebleId;
        this.imagen = imagen;
        this.inmueble = inmueble;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public String toString() {
        return "ImagenInmueble{" +
                "id=" + id +
                ", inmuebleId=" + inmuebleId +
                ", imagen='" + imagen + '\'' +
                ", inmueble=" + inmueble +
                '}';
    }
}
