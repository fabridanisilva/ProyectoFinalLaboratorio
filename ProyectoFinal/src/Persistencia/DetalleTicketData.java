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
    /*
    if(detalle.getAsientos().isEmpty()) {
        JOptionPane.showMessageDialog(null, "No se puede guardar detalle porque no hay asientos.");
        return;
    }

   Asiento asiento = detalle.getAsientos().get(0);
*/
    String sql = "INSERT INTO detalleticket(funcion, codLugar, subtotal) VALUES (?, ?, ?)";
    AsientoData ad = new AsientoData();
    try {
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // Tomamos el primer asiento (si tenés varios, hay que iterar)
        
        ps.setInt(1, detalle.getProyeccion().getIdFuncion());
        ps.setInt(2, detalle.getCodLugar());
        ps.setDouble(3, detalle.getSubTotal());
        ad.seleccionarAsiento(detalle.getCodLugar());  // esto va amodificar el estado del asiento a false cuando se realize el detalleticket
        
        
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            detalle.setCodD(rs.getInt(1)); // codD generado correctamente
            JOptionPane.showMessageDialog(null, "DetalleTicket guardado correctamente, codD=" + detalle.getCodD());
        }

        rs.close();
        ps.close();

        
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al guardar detalle: " + ex.getMessage());
    }
}



 public DetalleTicket buscarDetallePorTicket(int codD) {
       String sql = "SELECT codD,funcion, codLugar, subtotal FROM detalleticket WHERE codD = ?";
        DetalleTicket detalle = new DetalleTicket();
        /*
        ArrayList<Asiento> asientos = new ArrayList<>();
        double total = 0;
        int cantidad = 0;
        */
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codD);
            ResultSet rs = ps.executeQuery();

            ProyeccionData pd = new ProyeccionData();
            AsientoData ad = new AsientoData();
            /*
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
            */
            while (rs.next()) {                
                detalle.setCodD(codD);
                Proyeccion pro = pd.buscarProyeccion(rs.getInt("funcion"));
                detalle.setProyeccion(pro);
                detalle.setCodLugar(rs.getInt("codLugar"));
                detalle.setSubTotal(rs.getDouble("subtotal"));
            }
            
            //detalle.setCodLugar(cantidad);
            //detalle.setSubTotal(total);
              
               ps.close();
               JOptionPane.showMessageDialog(null, "detalle ticket encontrado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "no se pudo generar detalle de ticket: " + ex.getMessage());
        }

        return detalle;
    }


public ArrayList<DetalleTicket> listarDetallesIndividuales(int idTicket) {
        ArrayList<DetalleTicket> detalles = new ArrayList<>();
        String sql = "SELECT codD, funcion, codlugar, subtotal FROM detalleticket WHERE codD = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idTicket);
            ResultSet rs = ps.executeQuery();

            ProyeccionData pd = new ProyeccionData();
            AsientoData ad = new AsientoData();

            while (rs.next()) {
                DetalleTicket detalle = new DetalleTicket();
                detalle.setCodD(rs.getInt("codD"));
                
                detalle.setProyeccion(pd.buscarProyeccion(rs.getInt("funcion")));

                ArrayList<Asiento> asientoUnico = new ArrayList<>();
                //asientoUnico.add(ad.buscarAsientoPorcodLugar(rs.getInt("codlugar")));
                detalle.setCodLugar(rs.getInt("codlugar"));
               // detalle.setAsientos(asientoUnico); // ← esto falta

                //detalle.setCodD(idTicket);
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


//hola de nuevo