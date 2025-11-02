/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import Modelo.Asiento;
import Modelo.Comprador;
import Modelo.DetalleTicket;
import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import Modelo.TicketCompra;
import Persistencia.AsientoData;
import Persistencia.CompradorData;
import Persistencia.DetalleTicketData;
import Persistencia.PeliculaData;
import Persistencia.ProyeccionData;
import Persistencia.SalaData;
import Persistencia.TicketCompraData;
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
        
        Proyeccion proyeccion1 = new Proyeccion(2,pelicula,"español",true,true,LocalTime.of(13, 00),LocalTime.of(14, 50),50,sala,3000);
        ProyeccionData proyecciond = new ProyeccionData();
        //proyecciond.ingresarProyeccion(proyeccion1);
        
        AsientoData ad = new AsientoData();
        
        //generamos los asientos de la funcion
        //ad.generarAsientosParaFuncion(proyeccion1);
        
        
        //recuperar la lista de asientos libres
        
        for (Asiento listarAsiento : ad.listarAsientos(2)) {
            System.out.println(listarAsiento);
        }
        
        
        //hacemos el comprador
        
        Comprador comprador = new Comprador(33444555,"Julian Alvarez",LocalDate.of(2000, 01, 31),"AtleticoMadrid","Efectivo",4);
        //Comprador comprador2 = new Comprador(24350998,"Emmanuel Vasquez",LocalDate.of(2025,10,30),"123","tarjeta",2);
        //CompradorData cd = new CompradorData();
        //cd.AgregarComprador(comprador);
        //cd.ActualizarComprador(comprador);
        //cd.EliminarComprador(33444555);
       // System.out.println(cd.BuscarComprador(33444555));
       
       /*listamos compradores
        for (Comprador MostrarCompradore : cd.MostrarCompradores()) {
            System.out.println(MostrarCompradore);
        }
        */
       
       //hacemos detalle ticket
       /*
        Asiento asiento = ad.buscarAsientoPorcodLugar(204);
        DetalleTicket detalleTicket = new DetalleTicket(proyeccion1,asiento.getCodLugar(),3000);
        DetalleTicketData dtd = new DetalleTicketData();
        dtd.guardarDetalle(detalleTicket);
        //System.out.println(dtd.buscarDetallePorTicket(1));
        
        for (DetalleTicket listarDetallesIndividuale : dtd.listarDetallesIndividuales(1)) {
            System.out.println(listarDetallesIndividuale);
        }
       */
         TicketCompra ticketcompra = new TicketCompra(1,LocalDate.of(2025,10,30),LocalDate.of(2025,10,31),3000,comprador,3,1500);
         TicketCompraData ticket = new TicketCompraData();
       //  ticket.GuardarTicketCompra(ticketcompra);

         
         
       

        


        
    }
    
}
