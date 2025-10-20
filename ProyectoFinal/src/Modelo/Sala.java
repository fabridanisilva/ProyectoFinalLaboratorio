/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author fabri
 */
public class Sala {
    private int NroSala;
    private boolean apta3D;
    private int capacidad;
    private boolean estado;

    public Sala(int NroSala, boolean apta3D, int capacidad, boolean estado) {
        this.NroSala = NroSala;
        this.apta3D = apta3D;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getNroSala() {
        return NroSala;
    }

    public void setNroSala(int NroSala) {
        this.NroSala = NroSala;
    }

    public boolean isApta3D() {
        return apta3D;
    }

    public void setApta3D(boolean apta3D) {
        this.apta3D = apta3D;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sala{" + "NroSala=" + NroSala + ", apta3D=" + apta3D + ", capacidad=" + capacidad + ", estado=" + estado + '}';
    }
    
    
}
