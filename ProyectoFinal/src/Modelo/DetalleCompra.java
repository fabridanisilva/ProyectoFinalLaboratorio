/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author chich
 */
public class DetalleCompra {
     private int idTicketCompra;
    
    private Proyeccion proyeccion;
    private int codLugar;
    private List<Asiento> asientos;
    private double subTotal;
    
    
    private LocalDate fechaCompra;
    private LocalDate fechaFuncion;
    private double monto;
    private Comprador comprador;
    private int cantidadtickets;
    private double descuento;

    public DetalleCompra() {
    }

    public DetalleCompra(Proyeccion proyeccion, int codLugar, List<Asiento> asientos, double subTotal, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador, int cantidadtickets, double descuento) {
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
        this.asientos = asientos;
        this.subTotal = subTotal;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
        this.cantidadtickets = cantidadtickets;
        this.descuento = descuento;
    }

    public DetalleCompra(int idTicketCompra, Proyeccion proyeccion, int codLugar, List<Asiento> asientos, double subTotal, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador, int cantidadtickets, double descuento) {
        this.idTicketCompra = idTicketCompra;
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
        this.asientos = asientos;
        this.subTotal = subTotal;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
        this.cantidadtickets = cantidadtickets;
        this.descuento = descuento;
    }

    public int getIdTicketCompra() {
        return idTicketCompra;
    }

    public void setIdTicketCompra(int idTicketCompra) {
        this.idTicketCompra = idTicketCompra;
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public int getCodLugar() {
        return codLugar;
    }

    public void setCodLugar(int codLugar) {
        this.codLugar = codLugar;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
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

    public int getCantidadtickets() {
        return cantidadtickets;
    }

    public void setCantidadtickets(int cantidadtickets) {
        this.cantidadtickets = cantidadtickets;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "idTicketCompra=" + idTicketCompra + ", proyeccion=" + proyeccion + ", codLugar=" + codLugar + ", asientos=" + asientos + ", subTotal=" + subTotal + ", fechaCompra=" + fechaCompra + ", fechaFuncion=" + fechaFuncion + ", monto=" + monto + ", comprador=" + comprador + ", cantidadtickets=" + cantidadtickets + ", descuento=" + descuento + '}';
    }
    
    
    
    
    
}
