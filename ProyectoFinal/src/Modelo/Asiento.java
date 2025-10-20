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
    private int fila;
    private int numero;
    private Proyeccion proyeccion;

    public Asiento(int codLugar, int fila, int numero, Proyeccion proyeccion) {
        this.codLugar = codLugar;
        this.fila = fila;
        this.numero = numero;
        this.proyeccion = proyeccion;
    }

    public int getCodLugar() {
        return codLugar;
    }

    public void setCodLugar(int codLugar) {
        this.codLugar = codLugar;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
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
        return "Asiento{" + "codLugar=" + codLugar + ", fila=" + fila + ", numero=" + numero + ", proyeccion=" + proyeccion + '}';
    }
    
    
}
