package com.softulp.app.inmobiliariagutierrezj.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Pago implements Serializable {

    private int id ;
    private int numeroPago ;
    private int contratoId ;
    private Contrato contrato ;
  //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
   private LocalDateTime fecha;
   //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime fechaPago ;
   private Float importe ;

    public Pago(int id, int numeroPago, int contratoId, Contrato contrato, LocalDateTime fecha, LocalDateTime fechaPago, Float importe) {
        this.id = id;
        this.numeroPago = numeroPago;
        this.contratoId = contratoId;
        this.contrato = contrato;
        this.fecha = fecha;
        this.fechaPago = fechaPago;
        this.importe = importe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "id=" + id +
                ", numeroPago=" + numeroPago +
                ", contratoId=" + contratoId +
                ", contrato=" + contrato +
                ", fecha=" + fecha +
                ", fechaPago=" + fechaPago +
                ", importe=" + importe +
                '}';
    }
}
