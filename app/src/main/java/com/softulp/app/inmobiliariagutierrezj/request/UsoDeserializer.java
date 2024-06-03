package com.softulp.app.inmobiliariagutierrezj.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.softulp.app.inmobiliariagutierrezj.models.Inmueble;

import java.lang.reflect.Type;

public class UsoDeserializer implements JsonDeserializer<Inmueble.Uso> {

    @Override
    public Inmueble.Uso deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int usoValue = json.getAsInt();
        switch (usoValue) {
            case 1:
                return Inmueble.Uso.Comercial;
            case 2:
                return Inmueble.Uso.Residencial;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + usoValue);
        }
    }
}
