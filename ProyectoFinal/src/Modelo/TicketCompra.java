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
    private int codD;
    private LocalDate fechaCompra;
    private LocalDate fechaFuncion;
    private double monto;
    private Comprador comprador;
    private int cantidadtickets;
    private double descuento;
    

    public TicketCompra() {
    }

    public TicketCompra(int codD, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador, int cantidadtickets, double descuento) {
        this.codD = codD;
        this.fechaCompra = fechaCompra;
        this.fechaFuncion = fechaFuncion;
        this.monto = monto;
        this.comprador = comprador;
        this.cantidadtickets = cantidadtickets;
        this.descuento = descuento;
    }

    

    public TicketCompra(int idTicketCompra, int codD, LocalDate fechaCompra, LocalDate fechaFuncion, double monto, Comprador comprador, int cantidadtickets, double descuento) {
        this.idTicketCompra = idTicketCompra;
        this.codD = codD;
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

    public int getCantidadtickets() {
        return cantidadtickets;
    }

    public void setCantidadtickets(int cantidadticket) {
        this.cantidadtickets = cantidadticket;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

   
    
    public void setIdTicketCompra(int idTicketCompra) {
        this.idTicketCompra = idTicketCompra;
    }

    public int getcodD() {
        return codD;
    }

    public void setcodD(int detD) {
        this.codD = codD;
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
        return "TicketCompra{" + "idTicketCompra=" + idTicketCompra + ", codD=" + codD + ", fechaCompra=" + fechaCompra + ", fechaFuncion=" + fechaFuncion + ", monto=" + monto + ", comprador=" + comprador + ", cantidadtickets=" + cantidadtickets + ", descuento=" + descuento + '}';
    }


}

    

  
    
 //hello

