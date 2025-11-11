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
    private String email;
    private String medioDePago;
    
    public Comprador() {
    }

    public Comprador(int dni, String nombre, LocalDate fechaNac, String email, String medioDePago) {
        this.dni = dni;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.email = email;
        this.medioDePago = medioDePago;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    @Override
    public String toString() {
        return  "dni=" + dni + ", " + nombre + ", fechaNac=" + fechaNac + ", email=" + email + ", medioDePago=" + medioDePago ;
    }

    
    
    
}
