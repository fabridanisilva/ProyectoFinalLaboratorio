/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author fabri
 */
public class TicketCompra {
    private int idTicketCompra;
    private int detD;
    private LocalDate fechaCompra;
    private LocalDate fechaFuncion;
    private double monto;
    private Comprador comprador;

    public TicketCompra() {
    }

    public TicketCompra(int detD, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador) {
        this.detD = detD;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
    }

    public TicketCompra(int idTicketCompra, int detD, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador) {
        this.idTicketCompra = idTicketCompra;
        this.detD = detD;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
    }

    public int getIdTicketCompra() {
        return idTicketCompra;
    }

    public void setIdTicketCompra(int idTicketCompra) {
        this.idTicketCompra = idTicketCompra;
    }

    public int getDetD() {
        return detD;
    }

    public void setDetD(int detD) {
        this.detD = detD;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public LocalDate getFechaFuncion() {
        return fechaFuncion;
    }

    public void setFechaFuncion(LocalDate fechaFuncion) {
        this.fechaFuncion = fechaFuncion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @Override
    public String toString() {
        return "TicketCompra{" + "idTicketCompra=" + idTicketCompra + ", detD=" + detD + ", fechaCompra=" + fechaCompra + ", fechaFuncion=" + fechaFuncion + ", monto=" + monto + ", comprador=" + comprador + '}';
    }

  
    
    
}
