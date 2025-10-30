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
public class Comprador {
    private int dni;
    private String nombre;
    private LocalDate fechaNac;
    private String password;
    private String medioDePago;
    private int maxticketpermitidos;
    public Comprador() {
    }
    
    
    public Comprador(int dni, String nombre, LocalDate fechaNac, String password, String medioDePago, int maticketpermitidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.password = password;
        this.medioDePago = medioDePago;
        this.maxticketpermitidos = maxticketpermitidos;
    }

    public int getMaxticketpermitidos() {
        return maxticketpermitidos;
    }

    public void setMaxticketpermitidos(int maxticketpermitidos) {
        this.maxticketpermitidos = maxticketpermitidos;
    }
    

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    @Override
    public String toString() {
        return "Comprador{" + "dni=" + dni + ", nombre=" + nombre + ", fechaNac=" + fechaNac + ", password=" + password + ", medioDePago=" + medioDePago + ", maxticketpermitidos=" + maxticketpermitidos + '}';
    }

    
    
    
}
