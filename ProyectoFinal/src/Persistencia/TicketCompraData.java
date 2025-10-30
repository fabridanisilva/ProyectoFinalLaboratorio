/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Asiento;
import Modelo.Comprador;
import Modelo.Conexion;
import Modelo.DetalleTicket;
import Modelo.TicketCompra;
import Persistencia.AsientoData;
import Persistencia.DetalleTicketData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author chich
 */
public class TicketCompraData {
    private Connection con = null;

    public TicketCompraData() {
        con = Conexion.getConexion();
    
}
    public void GuardarTicketCompra(TicketCompra ticketcompra){
         String sql = "INSERT INTO ticketcompra (fechacompra, fechafuncion, monto, comprador, cantidadtickets, descuento) VALUES ( ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(ticketcompra.getFechaCompra()));
            ps.setDate(2, Date.valueOf(ticketcompra.getFechaFuncion()));
            ps.setDouble(3, ticketcompra.getMonto());
            ps.setInt(4, ticketcompra.getComprador().getDni());
            ps.setInt(5, ticketcompra.getCantidadtickets());
            ps.setDouble(6, ticketcompra.getDescuento());
            
            ps.executeUpdate();

            
            ResultSet rs =ps.getGeneratedKeys();
            if (rs.next()) {
                ticketcompra.setIdTicketCompra(rs.getInt(1));
                
            }
            rs.close();
            ps.close();
            JOptionPane.showMessageDialog(null, "se agrego ticket!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"no se pudo guardar ticket"+ ex);
        }

          


    }
    public boolean comprarTickets(TicketCompra ticketcompra, ArrayList<Asiento> asientos){
        // esto es para Validar cantidad máxima permitida
        if(ticketcompra.getCantidadtickets()> ticketcompra.getComprador().getMaxticketpermitidos()){
            JOptionPane.showMessageDialog(null, "cantidad máxima de tickets permitida excedida.");
            return false;
        }
        // esto es para  Aplicar descuentos por cantidad
        if (ticketcompra.getCantidadtickets()>= 2) {
    ticketcompra.setDescuento(10); // 10% por pareja
    ticketcompra.setTipodeofertas("pareja");
} else {
    ticketcompra.setDescuento(0);
    ticketcompra.setTipodeofertas("ninguna");
}

        // esto es para Calcular monto total
        double montoTotal = ticketcompra.getCantidadtickets()* asientos.get(0).getProyeccion().getPrecio();
        montoTotal -= montoTotal * (ticketcompra.getDescuento() / 100.0);
        ticketcompra.setMonto(montoTotal);
        
        // esto es para Guardar TicketCompra
        GuardarTicketCompra(ticketcompra);
        
        // 5. Guardar DetalleTicket
        DetalleTicket detalle = new DetalleTicket();
        detalle.setCodD(ticketcompra.getIdTicketCompra());
        detalle.setProyeccion(asientos.get(0).getProyeccion());
        detalle.setCodLugar(2);
        
        detalle.setSubTotal(montoTotal);

        DetalleTicketData dtd = new DetalleTicketData();
        dtd.guardarDetalle(detalle);

        // esto es para Marcar asientos como ocupados
        AsientoData ad = new AsientoData();
        for(Asiento a : asientos){
            a.setEstado(false);
            ad.modificarAsiento(a);
        }

        JOptionPane.showMessageDialog(null, "Compra realizada exitosamente!");
        
    
        return true;
    }   
        
            
    public TicketCompra BuscarTicketCompra(int id){
        String sql = "SELECT fechacompra, fechafuncion, monto, comprador FROM ticketcompra WHERE idTicket = ?";
        
        TicketCompra ticketcompra = null;
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ticketcompra = new TicketCompra ();
                ticketcompra.setIdTicketCompra(rs.getInt("idTicket"));
                ticketcompra.setFechaCompra(rs.getDate("fechacompra").toLocalDate());
                ticketcompra.setFechaFuncion(rs.getDate("fechafuncion").toLocalDate());
                ticketcompra.setMonto(rs.getDouble("monto"));
                Comprador c = new Comprador();
                c.setDni(rs.getInt("comprador"));
                ticketcompra.setComprador(c);
            }else  {
            JOptionPane.showMessageDialog(null, "No existe el ticket");}
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"no se encontro ticket"+ ex);
        }
        return ticketcompra;
    }
    public ArrayList<TicketCompra> MostrarTicketComprados(){
        String sql ="SELECT idTicket, fechacompra, fechafuncion, monto FROM ticketcompra WHERE comprador = ?";
        
        ArrayList<TicketCompra> ticketcomprados = new ArrayList<>();
        
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TicketCompra ticketcompra = new TicketCompra();
                ticketcompra = new TicketCompra ();
                ticketcompra.setIdTicketCompra(rs.getInt("idTicket"));
                ticketcompra.setFechaCompra(rs.getDate("fechacompra").toLocalDate());
                ticketcompra.setFechaFuncion(rs.getDate("fechafuncion").toLocalDate());
                ticketcompra.setMonto(rs.getDouble("monto"));
                Comprador c = new Comprador();
                c.setDni(rs.getInt("comprador"));
                ticketcompra.setComprador(c);
                
                ticketcomprados.add(ticketcompra);
            }
            rs.close();
            ps.close();
            JOptionPane.showMessageDialog(null, "ticket encontrado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"no se encontraron ticket"+ ex);
        }
        return ticketcomprados;
    }
}
