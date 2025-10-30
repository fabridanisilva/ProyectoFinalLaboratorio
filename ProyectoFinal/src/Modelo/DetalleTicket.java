/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author fabri
 */
public class DetalleTicket {
    private int codD;
    
    private Proyeccion proyeccion;
    private int lugar;
    
    private double subTotal;

    public DetalleTicket() {
    }

    public DetalleTicket(int codD, Proyeccion proyeccion, int lugar,  double subTotal) {
        this.codD = codD;
        this.proyeccion = proyeccion;
        this.lugar = lugar;
        
        this.subTotal = subTotal;
    }

    public DetalleTicket(Proyeccion proyeccion, int lugar,  double subTotal) {
        this.proyeccion = proyeccion;
        this.lugar = lugar;
        
        this.subTotal = subTotal;
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

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

   

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleTicket{" + "codD=" + codD + ", proyeccion=" + proyeccion + ", lugar=" + lugar + ", subTotal=" + subTotal + '}';
    }
    
    
}
