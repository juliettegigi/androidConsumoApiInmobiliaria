package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;

    private String email;
    private String pass;
    private ArrayList<Inmueble> inmuebles;

    public Usuario(){

    }
    public Usuario(String nombre, String apellido,String dni,String telefono, String email){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public Usuario(int id, String nombre, String apellido,String dni,String telefono, String email, String pass) {
        this.id=id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.pass = pass;
    }

    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(ArrayList<Inmueble> inmuebles) {
        this.inmuebles = inmuebles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "id= "+id+
                "dni= " + dni +
                ", nombre= '" + nombre + '\'' +
                ", apellido= '" + apellido + '\'' +
                ", email= '" + email + '\'' +
                ", pass= '" + pass + '\'' +
                '}';
    }
}
