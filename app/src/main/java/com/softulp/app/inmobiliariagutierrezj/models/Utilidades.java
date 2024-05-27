package com.softulp.app.inmobiliariagutierrezj.models;

import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilidades {
    public static String formatearFecha(LocalDateTime fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }
}
