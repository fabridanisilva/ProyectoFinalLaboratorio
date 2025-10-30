/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Conexion;
import Modelo.DetalleTicket;
import Modelo.TicketCompra;
import Modelo.Proyeccion;
import Modelo.Asiento;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 *
 * @author chich
 */
public class DetalleTicketData {
     private Connection con = null;

    public DetalleTicketData() {
        con = Conexion.getConexion();
    }

public void guardarDetalle(DetalleTicket detalle) {
        String sql = "INSERT INTO `detalleticket`(`codD`, `funcion`, `codLugar`, `subtotal`) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            double precioPorAsiento = detalle.getSubTotal() / detalle.getLugar();

            for (Asiento asiento : detalle.getAsientos()) {

                ps.setInt(1, detalle.getCodD());
                ps.setInt(2, detalle.getProyeccion().getIdFuncion());
                ps.setInt(3, asiento.getCodLugar());
                ps.setDouble(4, precioPorAsiento);
                ps.executeUpdate();
            }

            ps.close();
            JOptionPane.showMessageDialog(null, "Se guardaron " + detalle.getLugar()+ " asientos en detalleticket.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar detalle: " + ex.getMessage());
        }
    
}
 public DetalleTicket buscarDetallePorTicket(int codD) {
        String sql = "SELECT codD, funcion, codlugar, subtotal FROM detalleticket WHERE idticket = ?";
        DetalleTicket detalle = new DetalleTicket();
        ArrayList<Asiento> asientos = new ArrayList<>();
        double total = 0;
        int cantidad = 0;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codD);
            ResultSet rs = ps.executeQuery();

            ProyeccionData pd = new ProyeccionData();
            AsientoData ad = new AsientoData();

            boolean primeraFila = true;

            while (rs.next()) {
                if (primeraFila) {
                    detalle.setCodD(codD);
                    detalle.setProyeccion(pd.buscarProyeccion(rs.getInt("funcion")));
                    primeraFila = false;
                }

                Asiento asiento = ad.buscarAsientoPorcodLugar(rs.getInt("codlugar"));
                asientos.add(asiento);
                total += rs.getDouble("subtotal");
                cantidad++;
            }

            
            detalle.setLugar(cantidad);
            detalle.setSubTotal(total);
              
               ps.close();
               JOptionPane.showMessageDialog(null, "se genero detalle ticket");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo generar detalle de ticket: " + ex.getMessage());
        }

        return detalle;
    }


public ArrayList<DetalleTicket> listarDetallesIndividuales(int idTicket) {
        ArrayList<DetalleTicket> detalles = new ArrayList<>();
        String sql = "SELECT codd, funcion, codlugar, subtotal FROM detalleticket WHERE idticket = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();

            ProyeccionData pd = new ProyeccionData();
            AsientoData ad = new AsientoData();

            while (rs.next()) {
                DetalleTicket detalle = new DetalleTicket();
                detalle.setCodD(rs.getInt("codd"));
                detalle.setCodD(idTicket);
                detalle.setProyeccion(pd.buscarProyeccion(rs.getInt("funcion")));

                ArrayList<Asiento> asientoUnico = new ArrayList<>();
                asientoUnico.add(ad.buscarAsientoPorcodLugar(rs.getInt("codlugar")));
                detalle.setLugar(1);

                detalle.setCodD(1);
                detalle.setSubTotal(rs.getDouble("subtotal"));

                detalles.add(detalle);
            }

            ps.close();
            JOptionPane.showMessageDialog(null, "generado lista detalles");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al listar detalles: " + ex.getMessage());
        }

        return detalles;
}
    




}
