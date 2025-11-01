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
         String sql = "INSERT INTO ticketcompra (codD, fechacompra, fechafuncion, monto, comprador, cantidadtickets, descuento) VALUES ( ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ticketcompra.getcodD());
            ps.setDate(2, Date.valueOf(ticketcompra.getFechaCompra()));
            ps.setDate(3, Date.valueOf(ticketcompra.getFechaFuncion()));
            ps.setDouble(4, ticketcompra.getMonto());
            ps.setInt(5, ticketcompra.getComprador().getDni());
            ps.setInt(6, ticketcompra.getCantidadtickets());
            ps.setDouble(7, ticketcompra.getDescuento());
            
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
        public void EliminarTicketCompra(int idTicketCompra) {
    String sql = "DELETE FROM ticketcompra WHERE idTicket = ?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idTicketCompra);
        int exito = ps.executeUpdate();
        if (exito > 0) {
            JOptionPane.showMessageDialog(null, "Ticket eliminado correctamente.");
        } 
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar ticket: " + ex.getMessage());
    }
}

        public void ModificarTicketCompra(TicketCompra ticket) {
    String sql = "UPDATE ticketcompra SET fechacompra=?, fechafuncion=?, monto=?, comprador=?, cantidadtickets=?, descuento=? WHERE idTicket=?";
    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDate(1, Date.valueOf(ticket.getFechaCompra()));
        ps.setDate(2, Date.valueOf(ticket.getFechaFuncion()));
        ps.setDouble(3, ticket.getMonto());
        ps.setInt(4, ticket.getComprador().getDni());
        ps.setInt(5, ticket.getCantidadtickets());
        ps.setDouble(6, ticket.getDescuento());
        ps.setInt(7, ticket.getIdTicketCompra());
        
        int exito = ps.executeUpdate();
        if (exito > 0) {
            JOptionPane.showMessageDialog(null, "Ticket actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el ticket para actualizar.");
        }
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al actualizar ticket: " + ex.getMessage());
    }
}


          


    
    public void comprarTicket(TicketCompra ticket, ArrayList<Asiento> asientos) {
    // esto es para Validar cantidad máxima permitida
    if(ticket.getCantidadtickets() > ticket.getComprador().getMaxticketpermitidos()) {
        JOptionPane.showMessageDialog(null, "Cantidad máxima de tickets permitida excedida.");
        return;
    }

    String sql = "INSERT INTO ticketcompra (codD, fechacompra, fechafuncion, cantidadtickets, descuento, monto, comprador) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // esto Calcula subtotal
        double subtotal = 0;
        for(Asiento a : asientos) {
            subtotal += a.getProyeccion().getPrecio();
        }

        // aca Aplica descuento
        double descuento = 0;
        if(ticket.getCantidadtickets() >= 2) {
            descuento = 10; // 10% de descuento por pareja por ejemplo
        }
        double total = subtotal - (subtotal * (descuento / 100.0));

        // para Setear valores del ticket
        ticket.setDescuento(descuento);
        ticket.setMonto(total);

       
        ps.setInt(1, ticket.getcodD()); 
        ps.setDate(2, Date.valueOf(ticket.getFechaCompra()));
        ps.setDate(3, Date.valueOf(ticket.getFechaFuncion()));
        ps.setInt(4, ticket.getCantidadtickets());
        ps.setDouble(5, ticket.getDescuento());
        ps.setDouble(6, ticket.getMonto());
        ps.setInt(7, ticket.getComprador().getDni());

        
        ps.executeUpdate();

        // para Obtener codD generado automáticamente si es necesario
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()) {
            ticket.setcodD(rs.getInt(1));
        }

        ps.close();

        // esto Guarda detalle por cada asiento
        DetalleTicket detalle = new DetalleTicket();
        detalle.setCodD(ticket.getcodD());
        detalle.setProyeccion(asientos.get(0).getProyeccion());
        detalle.setCodLugar(asientos.size());
        detalle.setSubTotal(total);
        detalle.setAsientos(asientos);

        DetalleTicketData detalleData = new DetalleTicketData();
        detalleData.guardarDetalle(detalle);

        //esto  Marca asientos como ocupados
        AsientoData asientoData = new AsientoData();
        for(Asiento a : asientos) {
            a.setEstado(false); // ocupado
            asientoData.modificarAsiento(a);
        }

        JOptionPane.showMessageDialog(null, "Compra realizada correctamente. Total: $" + total);

    } catch(SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al registrar la compra: " + ex.getMessage());
    }
}

 
        
            
    public TicketCompra BuscarTicketCompra(int id){
        String sql = "SELECT idTicketCompra fechacompra, fechafuncion, monto, comprador FROM ticketcompra WHERE idTicket = ?";
        
        TicketCompra ticketcompra = null;
        try {
            PreparedStatement ps= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                ticketcompra = new TicketCompra ();
                ticketcompra.setIdTicketCompra(rs.getInt("idTicketCompra"));
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
    public ArrayList<TicketCompra> MostrarTicketComprados(int dniComprador){
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
