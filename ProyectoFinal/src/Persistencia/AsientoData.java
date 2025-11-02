/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Asiento;
import Modelo.Conexion;
import Modelo.Proyeccion;
import Modelo.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author fabri
 */
public class AsientoData {
    
    private Connection con = null;

    public AsientoData() {
        con = Conexion.getConexion();
    }
    
    
    public Asiento buscarAsientoPorcodLugar(int codLugar) {
    String sql = "SELECT codlugar, fila, numero, estado, funcion FROM asiento WHERE codlugar = ?";
    Asiento asiento = null;

    try {
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, codLugar);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            asiento = new Asiento();
            asiento.setCodLugar(rs.getInt("codlugar"));
            asiento.setFila(rs.getString("fila"));
            asiento.setNumero(rs.getInt("numero"));
            asiento.setEstado(rs.getBoolean("estado"));

            ProyeccionData pd = new ProyeccionData();
            asiento.setProyeccion(pd.buscarProyeccion(rs.getInt("funcion")));
        }

        rs.close();
        ps.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al buscar asiento: " + ex.getMessage());
    }

    return asiento;
}

    
    public void ingresaAsiento(Asiento asiento){
    
        String sql = "INSERT INTO asiento( fila, numero, estado, funcion) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, asiento.getFila());
            ps.setInt(2, asiento.getNumero());
            ps.setBoolean(3, asiento.isEstado());
            ps.setInt(4, asiento.getProyeccion().getIdFuncion());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                asiento.setCodLugar(rs.getInt(1));
                //JOptionPane.showMessageDialog(null, "Se agrego exitosamente!!");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }
    
    public void seleccionarAsiento(int cod){
        
        String sql = "UPDATE asiento estado=false WHERE codlugar=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,cod);
            int exito = ps.executeUpdate();
            if (exito ==1) {
                JOptionPane.showMessageDialog(null, "Se selecciono exitosamente!!!");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
        }
    }
    
    
    
       
    public void generarAsientosParaFuncion(Proyeccion proyeccion) {
        
        
        // 1. Obtenemos los datos que necesitamos de la Proyeccion
    
    int capacidadTotal = proyeccion.getSala().getCapacidad();
    
    // Define cuántos asientos hay por fila (puedes cambiar este número)
    final int ASIENTOS_POR_FILA = 10; 

    // 2. El Bucle (Itera desde 0 hasta capacidad-1)
    for (int i = 0; i < capacidadTotal; i++) {
        
        // 3. Crear un nuevo objeto Asiento en CADA vuelta
        Asiento asientoNuevo = new Asiento();
        
        // 4. Calcular Fila y Número
        // (char) ('A' + 0) -> 'A'
        // (char) ('A' + 1) -> 'B'
        String fila = String.valueOf((char) ('A' + (i / ASIENTOS_POR_FILA)));
        
        // (0 % 10) + 1 -> 1
        // (1 % 10) + 1 -> 2
        // ...
        // (10 % 10) + 1 -> 1
        int numero = (i % ASIENTOS_POR_FILA) + 1;

        // 5. Setear los valores en el objeto Asiento
        asientoNuevo.setFila(fila);          // Ej: "A"
        asientoNuevo.setNumero(numero);       // Ej: 1
        asientoNuevo.setEstado(true);       // 'true' = disponible
        asientoNuevo.setProyeccion(proyeccion); // Asigna la función completa

        // 6. Llamar a tu otro método para guardarlo en la BD
        // (Asumo que este método está en la misma clase 'AsientoData')
        ingresaAsiento(asientoNuevo);
    }
    
    JOptionPane.showMessageDialog(null, "Se generaron " + capacidadTotal + " asientos para la función.");
    }
    
    public ArrayList<Asiento> listarAsientos(int idFuncion){
        ArrayList<Asiento> asientos = new ArrayList<>();
       
        String sql = "SELECT `codlugar`, `fila`, `numero`, `estado`, `funcion` FROM `asiento` WHERE estado=1 AND funcion=?";
        ProyeccionData pd = new ProyeccionData();
        Proyeccion funcion = pd.buscarProyeccion(idFuncion);
        
        if (funcion == null) {
        JOptionPane.showMessageDialog(null, "La funcion con id:" + idFuncion + " no existe.");
        return asientos; // Devuelve la lista vacía
    }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idFuncion);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {             
                Asiento asiento = new Asiento();
                asiento.setCodLugar(rs.getInt("codlugar"));
                asiento.setFila(rs.getString("fila"));
                asiento.setNumero(rs.getInt("numero"));
                asiento.setEstado(rs.getBoolean("estado"));
                asiento.setProyeccion(funcion);
                
                asientos.add(asiento);
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    return asientos;
    }
    
    
    
    
    
    
    
    
    
    
    
}
