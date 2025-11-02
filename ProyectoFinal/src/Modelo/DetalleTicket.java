/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author fabri
 */
public class DetalleTicket {
    private int codD;
    
    private Proyeccion proyeccion;
    private int codLugar;
    private List<Asiento> asientos;
    private double subTotal;

    public DetalleTicket() {
        asientos = new ArrayList<>();
    }

    public DetalleTicket(int codD, Proyeccion proyeccion, int codLugar,  List<Asiento> asientos, double subTotal) {
        this.codD = codD;
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
       
        this.asientos = asientos;
        this.subTotal = subTotal;
    }

    public DetalleTicket(Proyeccion proyeccion, int codLugar,  List<Asiento> asientos, double subTotal) {
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
        
        this.asientos = asientos;
        this.subTotal = subTotal;
    }
public DetalleTicket(Proyeccion proyeccion, int codLugar, double subTotal) {
    this.proyeccion = proyeccion;
    this.codLugar = codLugar;
    this.subTotal = subTotal;
    this.asientos = new ArrayList<>(); // para evitar NullPointerException si luego agregás asientos
}

    
    
    public List<Asiento> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
    
    public void agregarAsiento(Asiento asiento) {
        this.asientos.add(asiento);
    }
   

 
    
    
    
    public int getCodD() {
        return codD;
    }

    public void setCodD(int codD) {
        this.codD = codD;
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



   

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleTicket{" + "codD=" + codD + ", proyeccion=" + proyeccion + ", codLugar=" + codLugar + ", asientos=" + asientos + ", subTotal=" + subTotal + '}';
    }

    
    
    
}
