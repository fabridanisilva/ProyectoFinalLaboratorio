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
    private int codLugar1;
    private int codLugar2;
    private int codLugar3;
    private int codLugar4;
    private List<Asiento> asientos;
    private double subTotal;

    public DetalleTicket() {
    }

    public DetalleTicket(int codD, Proyeccion proyeccion, int codLugar, int codLugar1, int codLugar2, int codLugar3, int codLugar4, List<Asiento> asientos, double subTotal) {
        this.codD = codD;
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
        this.codLugar1 = codLugar1;
        this.codLugar2 = codLugar2;
        this.codLugar3 = codLugar3;
        this.codLugar4 = codLugar4;
        this.asientos = asientos;
        this.subTotal = subTotal;
    }

    public DetalleTicket(Proyeccion proyeccion, int codLugar, int codLugar1, int codLugar2, int codLugar3, int codLugar4, List<Asiento> asientos, double subTotal) {
        this.proyeccion = proyeccion;
        this.codLugar = codLugar;
        this.codLugar1 = codLugar1;
        this.codLugar2 = codLugar2;
        this.codLugar3 = codLugar3;
        this.codLugar4 = codLugar4;
        this.asientos = asientos;
        this.subTotal = subTotal;
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

    public int getCodLugar1() {
        return codLugar1;
    }

    public void setCodLugar1(int codLugar1) {
        this.codLugar1 = codLugar1;
    }

    public int getCodLugar2() {
        return codLugar2;
    }

    public void setCodLugar2(int codLugar2) {
        this.codLugar2 = codLugar2;
    }

    public int getCodLugar3() {
        return codLugar3;
    }

    public void setCodLugar3(int codLugar3) {
        this.codLugar3 = codLugar3;
    }

    public int getCodLugar4() {
        return codLugar4;
    }

    public void setCodLugar4(int codLugar4) {
        this.codLugar4 = codLugar4;
    }

  

   

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleTicket{" + "codD=" + codD + ", proyeccion=" + proyeccion + ", codLugar=" + codLugar + ", codLugar1=" + codLugar1 + ", codLugar2=" + codLugar2 + ", codLugar3=" + codLugar3 + ", codLugar4=" + codLugar4 + ", asientos=" + asientos + ", subTotal=" + subTotal + '}';
    }

    
    
    
}
