/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author fabri
 */
public class Asiento {
    private int codLugar;
    private String fila;
    private int numero;
    private boolean estado;
    private Proyeccion proyeccion;

    public Asiento() {
    }

    
    
    public Asiento(String fila, int numero, boolean estado, Proyeccion proyeccion) {
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
        this.proyeccion = proyeccion;
    }
    
    
    
    public Asiento(int codLugar, String fila, int numero,boolean estado ,Proyeccion proyeccion) {
        this.codLugar = codLugar;
        this.fila = fila;
        this.numero = numero;
        this.proyeccion = proyeccion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCodLugar() {
        return codLugar;
    }

    public void setCodLugar(int codLugar) {
        this.codLugar = codLugar;
    }

    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Proyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(Proyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    @Override
    public String toString() {
        return " fila=" + fila + ", numero=" + numero + ", estado=" + estado + ", proyeccion=" + proyeccion.getPelicula();
    }

   
    
    
}
