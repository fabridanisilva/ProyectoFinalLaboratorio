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
import Persistencia.AsientoData;
import Persistencia.ProyeccionData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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

    
   
     
    public boolean eliminarDetalleCompra(int idCompra) {

    // 1. Buscar la compra antes de eliminarla
    DetalleCompra compra = buscarDetalleCompra(idCompra);
    if (compra == null) {
        JOptionPane.showMessageDialog(null, "No existe la compra con ese ID");
        return false; // ← NECESARIO
    }

    // 2. Liberar asientos usando tu método REAL
    AsientoData asientoData = new AsientoData();
    asientoData.desocuparAsiento(compra.getCodLugar());

    if (compra.getCantidadtickets() == 2) {
        asientoData.desocuparAsiento(compra.getCodLugar2());
    }

    // 3. Sumar los asientos a la función
    ProyeccionData proyData = new ProyeccionData();
    proyData.sumarAsientoPorFuncion(compra.getProyeccion().getIdFuncion(), compra.getCantidadtickets());

    // 4. Eliminar la compra
    String sql = "DELETE FROM detallecompra WHERE idTicketCompra = ?";

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idCompra);

        int filas = ps.executeUpdate();
        ps.close();

        if (filas > 0) {
            JOptionPane.showMessageDialog(null, "Compra cancelada. Asientos liberados correctamente.");
            return true;  // ← NECESARIO
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la compra.");
            return false;
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al eliminar compra: " + ex.getMessage());
        return false;  // ← NECESARIO
    }
}
public DetalleCompra buscarPorDatos(String dniStr, String asientoStr, LocalDate fecha, LocalTime hora) {
    try {
        if (dniStr == null || asientoStr == null || fecha == null || hora == null) return null;

        int dni = Integer.parseInt(dniStr.trim());
        int asiento = Integer.parseInt(asientoStr.trim());

        // ✔ usa tu método REAL
        ArrayList<DetalleCompra> compras = listarComprasPorComprador(dni);

        for (DetalleCompra dc : compras) {
            if (dc == null) continue;

            // validar que haya proyección
            if (dc.getProyeccion() == null) continue;

            // validar asiento
            boolean asientoOK =
                    dc.getCodLugar() == asiento ||
                    (dc.getCodLugar2() > 0 && dc.getCodLugar2() == asiento);

            if (!asientoOK) continue;

            // FECHA ✔ viene de DetalleCompra (fechaFuncion)
            if (!dc.getFechaFuncion().isEqual(fecha)) continue;

            // HORA ✔ viene de Proyeccion (getHorInicio)
            if (!dc.getProyeccion().getHorInicio().equals(hora)) continue;

            return dc; // ENCONTRADA
        }

        return null;

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "DNI o asiento inválidos.");
        return null;
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al buscar compra: " + ex.getMessage());
        return null;
    }
}


public boolean eliminarPorDatos(String dniStr, String asientoStr, LocalDate fecha, LocalTime hora) {
    try {
        DetalleCompra compra = buscarPorDatos(dniStr, asientoStr, fecha, hora);

        if (compra == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la compra con esos datos.");
            return false;
        }

        // ✔ liberar asientos
        AsientoData asientoData = new AsientoData();
        if (compra.getCodLugar() > 0) {
            asientoData.desocuparAsiento(compra.getCodLugar());
        }
        if (compra.getCantidadtickets() == 2 && compra.getCodLugar2() > 0) {
            asientoData.desocuparAsiento(compra.getCodLugar2());
        }

        // ✔ sumar asientos a la función
        ProyeccionData proyData = new ProyeccionData();
        if (compra.getProyeccion() != null) {
            proyData.sumarAsientoPorFuncion(
                    compra.getProyeccion().getIdFuncion(),
                    compra.getCantidadtickets()
            );
        }

        // ✔ eliminar del detalle
        eliminarDetalleCompra(compra.getIdTicketCompra());

        return true;

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al eliminar compra: " + ex.getMessage());
        return false;
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
