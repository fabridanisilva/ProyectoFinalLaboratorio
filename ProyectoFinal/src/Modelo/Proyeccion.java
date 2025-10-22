/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author fabri
 */
public class Proyeccion {
    private Pelicula pelicula;
    private int idFuncion;
    private String idioma;
    private boolean es3D;
    private boolean subtitulada;
    private LocalTime horInicio;
    private LocalTime horaFin;
    private ArrayList<Integer> lugaresDisponibles;
    private Sala sala;
    private double precio;

    public Proyeccion() {
    }
    
    
    public Proyeccion(Pelicula pelicula, String idioma, boolean es3D, boolean subtitulada, LocalTime horInicio, LocalTime horaFin, ArrayList<Integer> lugaresDisponibles, Sala sala, double precio) {
        this.pelicula = pelicula;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.horInicio = horInicio;
        this.horaFin = horaFin;
        this.lugaresDisponibles = lugaresDisponibles;
        this.sala = sala;
        this.precio = precio;
    }

    public Proyeccion(Pelicula pelicula, int idFuncion, String idioma, boolean es3D, boolean subtitulada, LocalTime horInicio, LocalTime horaFin, ArrayList<Integer> lugaresDisponibles, Sala sala, double precio) {
        this.pelicula = pelicula;
        this.idFuncion = idFuncion;
        this.idioma = idioma;
        this.es3D = es3D;
        this.subtitulada = subtitulada;
        this.horInicio = horInicio;
        this.horaFin = horaFin;
        this.lugaresDisponibles = lugaresDisponibles;
        this.sala = sala;
        this.precio = precio;
    }
    
    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isEs3D() {
        return es3D;
    }

    public void setEs3D(boolean es3D) {
        this.es3D = es3D;
    }

    public boolean isSubtitulada() {
        return subtitulada;
    }

    public void setSubtitulada(boolean subtitulada) {
        this.subtitulada = subtitulada;
    }

    public LocalTime getHorInicio() {
        return horInicio;
    }

    public void setHorInicio(LocalTime horInicio) {
        this.horInicio = horInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public ArrayList<Integer> getLugaresDisponibles() {
        return lugaresDisponibles;
    }

    public void setLugaresDisponibles(ArrayList<Integer> lugaresDisponibles) {
        this.lugaresDisponibles = lugaresDisponibles;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Proyeccion{" + "pelicula=" + pelicula + ", idioma=" + idioma + ", es3D=" + es3D + ", subtitulada=" + subtitulada + ", horInicio=" + horInicio + ", horaFin=" + horaFin + ", lugaresDisponibles=" + lugaresDisponibles + ", sala=" + sala + ", precio=" + precio + '}';
    }
    
    
}
