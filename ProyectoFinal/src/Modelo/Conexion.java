/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author fabri
 */
public class Conexion {
    
    private static final String  url = "jdbc:mariadb://localhost:3306/";
    private static final String DB ="cineulp";
    private static final String usuario = "root";
    private static final String password = "";
    private static Connection conexion;
    
    public static Connection getConexion(){
         
         if (conexion == null) {
             try {
                 Class.forName("org.mariadb.jdbc.Driver");
                 conexion = DriverManager.getConnection(url+DB,usuario,password);
                  JOptionPane.showMessageDialog(null, "Conexion exitosa!");
             } catch (ClassNotFoundException ex) {
                 JOptionPane.showMessageDialog(null,"Error: "+ ex.getMessage());
             }catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null,"Error: "+ ex.getMessage());
                 }
             
         }
         return conexion;
    
    
    } 
    
    public static void main(String[] args) {
        Connection con = getConexion();
    }
}
