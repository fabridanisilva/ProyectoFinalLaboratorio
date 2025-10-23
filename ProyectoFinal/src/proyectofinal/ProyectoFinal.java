/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import Persistencia.PeliculaData;
import Persistencia.ProyeccionData;
import Persistencia.SalaData;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author fabri
 */
public class ProyectoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // hacemos la pelicula
        Pelicula pelicula = new Pelicula(1,"Terminator","James Cameron","Arnold Schwarzenegger,Michael Biehn,Linda Hamilton,Paul Winfield,Lance Henriksen,Earl Boen,Bess Motta,Rick Rossovich","EE.UU","Ciencia ficción Acción",LocalDate.of(1984,10,26),true);
        
        PeliculaData pd = new PeliculaData();
        
        //pd.GuardarPelicula(pelicula);
        
        
        // hacemos la sala
        Sala sala = new Sala(1,true,50,true);
        SalaData sd = new SalaData();
        
        //sd.ingresarSala(sala);
        
        //hacemos la proyeccion
        
        Proyeccion proyeccion1 = new Proyeccion(pelicula,"español",true,true,LocalTime.of(13, 00),LocalTime.of(14, 50),50,sala,3000);
        ProyeccionData proyecciond = new ProyeccionData();
        proyecciond.ingresarProyeccion(proyeccion1);
        
        
        
    }
    
}
