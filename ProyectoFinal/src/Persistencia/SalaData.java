/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Conexion;
import Modelo.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author fabri
 */
public class SalaData {
    private Connection con = null;

    public SalaData() {
        con =  (Connection) Conexion.getConexion();
    }
    
    public void ingresarSala(Sala sala){
    
        String sql = "INSERT INTO sala( apta3D, capacidad, estado) VALUES (?,?,?)";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setBoolean(1, sala.isApta3D());
            ps.setInt(2, sala.getCapacidad());
            ps.setBoolean(3, sala.isEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                sala.setNroSala(rs.getInt(1));
                JOptionPane.showInternalMessageDialog(null, "Sala agregada exitosamente!!!");
            }
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ ex);
        }
        
        
    }
    
    public void modificar(Sala sala){
    
        String sql = "UPDATE sala SET apta3D=?,capacidad=?,estado=? WHERE NROsala = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setBoolean(1, sala.isApta3D());
            ps.setInt(2, sala.getCapacidad());
            ps.setBoolean(3, sala.isEstado());
            ps.setInt(4, sala.getNroSala());
            
            int exito = ps.executeUpdate();
            
            if (exito ==1) {
                JOptionPane.showMessageDialog(null, "Modificacion exitosa!!!");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        
    }
    
    public void darBajaSala(Sala sala){
    
    String sql = "UPDATE sala SET estado=false WHERE NROsala = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, sala.getNroSala());
            
            int exito = ps.executeUpdate();
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Sala dada de baja.");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    
    
    
    }
    
    public Sala buscarSala(int NROsala){
    
        String sql = "SELECT NROsala, apta3D, capacidad, estado FROM sala WHERE NROsala=? ";
        Sala sala = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            ps.setInt(1, NROsala);
            
            ResultSet rs = ps.executeQuery();
            sala = new Sala();
            
            if (rs.next()) {
                
            
                sala.setNroSala(rs.getInt("NROsala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getBoolean("estado"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe esa sala");
                ps.close();
            }
            
            
            
            
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        
        return sala;
        
        
    
    }
    
    public ArrayList<Sala> listarSalas(){
    
        ArrayList<Sala> salas = new ArrayList<>();
       
        
        String sql = "SELECT `NROsala`, `apta3D`, `capacidad`, `estado` FROM `sala` ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rs = ps.executeQuery();
            
            
            
            while (rs.next()) {    
                Sala sala = new Sala();
                sala.setNroSala(rs.getInt("NROsala"));
                sala.setApta3D(rs.getBoolean("apta3D"));
                sala.setCapacidad(rs.getInt("capacidad"));
                sala.setEstado(rs.getBoolean("estado"));
                
                salas.add(sala);
            }
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        return salas;
    
    }
    
    
    
}
