package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Inquilino implements Serializable {

        private int id;
        private String dni;
        private String nombre;
        private String apellido;
        private String telefono;

        private String email;
        private String domicilio;

        public Inquilino(int id, String dni, String nombre, String apellido, String telefono, String email, String domicilio) {
                this.id = id;
                this.dni = dni;
                this.nombre = nombre;
                this.apellido = apellido;
                this.telefono = telefono;
                this.email = email;
                this.domicilio = domicilio;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
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

        public String getTelefono() {
                return telefono;
        }

        public void setTelefono(String telefono) {
                this.telefono = telefono;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getDomicilio() {
                return domicilio;
        }

        public void setDomicilio(String domicilio) {
                this.domicilio = domicilio;
        }
}
