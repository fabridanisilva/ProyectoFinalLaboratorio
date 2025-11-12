/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Asiento;
import Modelo.Comprador;
import Modelo.Conexion;
import Modelo.DetalleCompra;
import Modelo.Proyeccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author chich
 */
public class DetalleCompraData {
    private Connection con = null;

    public DetalleCompraData() {
        con = Conexion.getConexion();
    }
    
     public void guardarDetalleCompra(DetalleCompra compra) {
        String sql = "INSERT INTO `detallecompra`(`funcion`, `codLugar`, `codLugar2`, `fechacompra`, `fechafuncion`, `cantidadtickets`, `descuento`, `monto`, `comprador`) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            ps.setInt(1, compra.getProyeccion().getIdFuncion());
            ps.setInt(2, compra.getCodLugar());
            if (compra.getCantidadtickets() == 2) {
            // Si son 2, se setea el ID del segundo asiento
            ps.setInt(3, compra.getCodLugar2());
        } else {
            // Si es 1, se manda NULL a la base de datos
            ps.setNull(3, java.sql.Types.INTEGER);
        }         
            
            ps.setDate(4, Date.valueOf(compra.getFechaCompra()));
            ps.setDate(5, Date.valueOf(compra.getFechaFuncion()));
            ps.setInt(6, compra.getCantidadtickets());
            ps.setDouble(7, compra.getDescuento());
            ps.setDouble(8, compra.getMonto());
            ps.setInt(9, compra.getComprador().getDni());
            

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                compra.setIdTicketCompra(rs.getInt(1));
            }

            ps.close();

            // Actualizar asientos y función
            ProyeccionData proyData = new ProyeccionData();
            AsientoData asientoData = new AsientoData();
            
                asientoData.seleccionarAsiento(compra.getCodLugar());
                if (compra.getCantidadtickets()==2) {
                asientoData.seleccionarAsiento(compra.getCodLugar2());
                proyData.restarAsientoPorFuncion(compra.getProyeccion().getIdFuncion());
            }
            

            
            proyData.restarAsientoPorFuncion(compra.getProyeccion().getIdFuncion());

            JOptionPane.showMessageDialog(null, "Compra registrada correctamente.\nTotal: $" + compra.getMonto());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar la compra: " + ex.getMessage());
        }
    }

    
    //  Busca una compra por su ID
     
    public DetalleCompra buscarDetalleCompra(int idCompra) {
        String sql = "SELECT * FROM detallecompra WHERE idTicketCompra = ?";
        DetalleCompra compra = null;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            ProyeccionData proyData = new ProyeccionData();

            if (rs.next()) {
                compra = new DetalleCompra();
                compra.setIdTicketCompra(rs.getInt("idTicketCompra"));
                compra.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                compra.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                compra.setCantidadtickets(rs.getInt("cantidadtickets"));
                compra.setDescuento(rs.getDouble("descuento"));
                compra.setMonto(rs.getDouble("monto"));
                compra.setCodLugar(rs.getInt("codLugar"));
                compra.setCodLugar2(rs.getInt("codLugar2"));
                CompradorData cd = new CompradorData();
                Comprador comprador = cd.BuscarComprador(rs.getInt("comprador"));
                compra.setComprador(comprador);
                

                Proyeccion proy = proyData.buscarProyeccion(rs.getInt("funcion"));
                compra.setProyeccion(proy);
            }

            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar compra: " + ex.getMessage());
        }

        return compra;
    }

    
    //  Lista todas las compras realizadas por un comprador
     
    public ArrayList<DetalleCompra> listarComprasPorComprador(int dniComprador) {
        ArrayList<DetalleCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra WHERE comprador = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dniComprador);
            ResultSet rs = ps.executeQuery();

            ProyeccionData proyData = new ProyeccionData();

            while (rs.next()) {
                DetalleCompra compra = new DetalleCompra();
                compra.setIdTicketCompra(rs.getInt("idTicketCompra"));
                compra.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                compra.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                compra.setCantidadtickets(rs.getInt("cantidadtickets"));
                compra.setDescuento(rs.getDouble("descuento"));
                compra.setMonto(rs.getDouble("monto"));
                compra.setCodLugar(rs.getInt("codLugar"));
                compra.setCodLugar2(rs.getInt("codLugar2"));
                CompradorData cd = new CompradorData();
                Comprador comprador = cd.BuscarComprador(rs.getInt("comprador"));
                compra.setComprador(comprador);
                

                Proyeccion proy = proyData.buscarProyeccion(rs.getInt("funcion"));
                compra.setProyeccion(proy);

                lista.add(compra);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar compras: " + ex.getMessage());
        }

        return lista;
    }

    
    //  Modifica una compra existente
     
    public void modificarDetalleCompra(DetalleCompra compra) {
        String sql = """
                     UPDATE detallecompra
                     SET fechaCompra=?, fechaFuncion=?, cantidadtickets=?, descuento=?, monto=?, comprador=?, funcion=?, codLugar=?,codLugar2=?
                     WHERE idTicketCompra=?
                     """;
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, Date.valueOf(compra.getFechaCompra()));
            ps.setDate(2, Date.valueOf(compra.getFechaFuncion()));
            ps.setInt(3, compra.getCantidadtickets());
            ps.setDouble(4, compra.getDescuento());
            ps.setDouble(5, compra.getMonto());
            ps.setInt(6, compra.getComprador().getDni());
            ps.setInt(7, compra.getProyeccion().getIdFuncion());
            ps.setInt(8, compra.getCodLugar());
            ps.setInt(9, compra.getCodLugar2());
            ps.setInt(10, compra.getIdTicketCompra());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Compra actualizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la compra para actualizar.");
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar compra: " + ex.getMessage());
        }
    }

    
    //  Elimina una compra (libera asientos opcionalmente)
     
    public void eliminarDetalleCompra(int idCompra) {
        String sql = "DELETE FROM detallecompra WHERE idTicketCompra = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCompra);
            int filas = ps.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Compra eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la compra.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar compra: " + ex.getMessage());
        }
    }
    
    
    
    public ArrayList<DetalleCompra> listarTodasLasCompras(){
    
        ArrayList<DetalleCompra> lista = new ArrayList<>();
        String sql = "SELECT * FROM detallecompra ";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            ProyeccionData proyData = new ProyeccionData();

            while (rs.next()) {
                DetalleCompra compra = new DetalleCompra();
                CompradorData cd = new CompradorData();
                compra.setIdTicketCompra(rs.getInt("idTicketCompra"));
                Comprador comprador = cd.BuscarComprador(rs.getInt("comprador"));
                compra.setComprador(comprador);
                compra.setFechaCompra(rs.getDate("fechaCompra").toLocalDate());
                compra.setFechaFuncion(rs.getDate("fechaFuncion").toLocalDate());
                compra.setCantidadtickets(rs.getInt("cantidadtickets"));
                compra.setDescuento(rs.getDouble("descuento"));
                compra.setMonto(rs.getDouble("monto"));
                compra.setCodLugar(rs.getInt("codLugar"));
                compra.setCodLugar2(rs.getInt("codLugar2"));

                

                Proyeccion proy = proyData.buscarProyeccion(rs.getInt("funcion"));
                compra.setProyeccion(proy);

                lista.add(compra);
            }

            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar compras: " + ex.getMessage());
        }

        return lista;
    
    
    }
}