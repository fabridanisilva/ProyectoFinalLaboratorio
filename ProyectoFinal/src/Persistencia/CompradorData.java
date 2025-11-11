/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import java.sql.*;
import Modelo.Comprador;
import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author chich
 */
public class CompradorData {
    
    private Connection con = null;

    public CompradorData() {
        con = Conexion.getConexion();
    
}

public void AgregarComprador(Comprador comprador){
    
    String sql = "INSERT INTO `comprador`(`dni`, `nombre`, `fechaNacimiento`, `email`, `mediodepago`) VALUES (?,?,?,?,?,?)";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1,comprador.getDni());
            ps.setString(2, comprador.getNombre());
            ps.setDate(3,Date.valueOf(comprador.getFechaNac()));
            
            ps.setString(4, comprador.getEmail());
            ps.setString(5, comprador.getMedioDePago());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                comprador.setDni(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "comprador agregado!!");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"comprador no encontrado"+ ex);
        }
    }

 public void ActualizarComprador(Comprador comprador){
     
     String sql = "UPDATE `comprador` SET `nombre`=?,`fechaNacimiento`=?,`email`=?,`mediodepago`=? WHERE dni=?";
     
     
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            
            
            ps.setString(1, comprador.getNombre());
            ps.setDate(2, Date.valueOf(comprador.getFechaNac()));
            
            ps.setString(3, comprador.getEmail());
            ps.setString(4, comprador.getMedioDePago());
            ps.setInt(5, comprador.getDni());
            int exito = ps.executeUpdate();
            
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Se actualizo comprador!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al actualizar comprador"+ ex);
        }
     
    
}
 public void EliminarComprador (int dni){
     
     String sql = "DELETE FROM `comprador` WHERE dni=?";
     
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se elimino correctamente!!");
            }    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error al eliminar comprador"+ ex);
        }
     
     
     
 }
 public Comprador BuscarComprador (int dni){
        String sql = "SELECT `dni`, `nombre`, `fechaNacimiento`, `email`, `mediodepago` FROM `comprador` WHERE dni=?";
            
            Comprador comprador = null;
        try {
            
            
            PreparedStatement ps= con.prepareStatement(sql);
            
             ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                comprador = new Comprador();
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNac(rs.getDate("fechanacimiento").toLocalDate());
                comprador.setEmail(rs.getString("email"));
                comprador.setMedioDePago(rs.getString("mediodepago"));
                
            }else{
            JOptionPane.showMessageDialog(null, "No existe el comprador");}
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"comprador no encontrado"+ ex);
        }
        return comprador;
       
     
 }
 public ArrayList<Comprador> MostrarCompradores(){
     
     String sql = "SELECT `dni`, `nombre`, `fechaNacimiento`, `email`, `mediodepago` FROM `comprador` ";
     
     ArrayList<Comprador> compradores = new ArrayList<>();
     
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Comprador comprador = new Comprador();
                comprador = new Comprador();
                comprador.setDni(rs.getInt("dni"));
                comprador.setNombre(rs.getString("nombre"));
                comprador.setFechaNac(rs.getDate("fechanacimiento").toLocalDate());
                comprador.setEmail(rs.getString("email"));
                comprador.setMedioDePago(rs.getString("mediodepago"));
                
                
                compradores.add(comprador);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"error al mostrar compradores"+ ex);
        }
     
        
     return compradores;
     
 }
}
//hola