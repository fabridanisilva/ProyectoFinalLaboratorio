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
    private int idTicket;
    private Proyeccion proyeccion;
    private ArrayList<Asiento> lugar;
    private int cant;
    private double subTotal;

    public DetalleTicket() {
    }
    
    
    
    public DetalleTicket(int codD, Proyeccion proyeccion, ArrayList<Asiento> lugar, int cant, double subTotal) {
        this.codD = codD;
        this.proyeccion = proyeccion;
        this.lugar = lugar;
        this.cant = cant;
        this.subTotal = subTotal;
    }

    public DetalleTicket(int codD, int idTicket, Proyeccion proyeccion, ArrayList<Asiento> lugar, int cant, double subTotal) {
        this.codD = codD;
        this.idTicket = idTicket;
        this.proyeccion = proyeccion;
        this.lugar = lugar;
        this.cant = cant;
        this.subTotal = subTotal;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
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

    public ArrayList<Asiento> getLugar() {
        return lugar;
    }

    public void setLugar(ArrayList<Asiento> lugar) {
        this.lugar = lugar;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleTicket{" + "codD=" + codD + ", proyeccion=" + proyeccion + ", lugar=" + lugar + ", cant=" + cant + ", subTotal=" + subTotal + '}';
    }
    
    
}
