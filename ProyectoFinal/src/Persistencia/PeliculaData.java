/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Conexion;
import Modelo.Conexion;
import Modelo.Pelicula;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author chich
 */
public class PeliculaData {
    
    private Connection  con =null ;
    
    public PeliculaData() {
        con = Conexion.getConexion();
    }  
    
    public void GuardarPelicula(Pelicula pelicula) {
      String sql= "INSERT INTO pelicula(titulo, director, actores, origen, genero, estreno, encartelera)"
              +"VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getActores());
            ps.setString(4, pelicula.getOrigen());
            ps.setString(5, pelicula.getGenero());
            ps.setDate(6, Date.valueOf(pelicula.getEstreno()));
            ps.setBoolean(7, pelicula.isEnCartelera());
            
            ps.executeUpdate();
            
            ResultSet rs =ps.getGeneratedKeys();
            if (rs.next()) {
                pelicula.setidPelicula(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se agrego pelicula");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo agregar pelicula: " + ex.getMessage());
        }
        }
    public Pelicula BuscarPelicula(int id){
        String sql = "SELECT `idPelicula`, `Titulo`, `director`, `actores`, `origen`, `genero`, `estreno`, `encartelera` FROM `pelicula` WHERE `idPelicula`=? AND `encartelera`=1";
        
        Pelicula pelicula = null ;
        
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                pelicula = new Pelicula();
                pelicula.setidPelicula(id);
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
               pelicula.setActores(rs.getString("actores"));
               pelicula.setOrigen(rs.getString("origen"));
               pelicula.setGenero(rs.getString("genero"));
               pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
               pelicula.setEnCartelera(true);
                
                
                
                
            }else {
                JOptionPane.showMessageDialog(null, "No existe la Pelicula");}
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ ex.getMessage());
        }
 return pelicula;
    }
    public Pelicula buscarPeliculaPorTitulo(String titulo) {
    Pelicula pelicula = null;
    String sql = "SELECT * FROM pelicula WHERE titulo = ?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, titulo);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            pelicula = new Pelicula();
            
               pelicula.setidPelicula(rs.getInt("idpelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
               pelicula.setActores(rs.getString("actores"));
               pelicula.setOrigen(rs.getString("origen"));
               pelicula.setGenero(rs.getString("genero"));
               pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
               pelicula.setEnCartelera(true);
               
            }
            ps.close();
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return pelicula;
}

    
    public void ActualizarPelicula(Pelicula pelicula){
        String sql = "UPDATE pelicula SET titulo = ?, director = ?, actores = ?, origen = ?,"
                +  "genero = ?, estreno = ?, encartelera = ? WHERE idPelicula=?" ;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getActores());
            ps.setString(4, pelicula.getOrigen());
            ps.setString(5, pelicula.getGenero());
            ps.setDate(6, Date.valueOf(pelicula.getEstreno()));
            ps.setBoolean(7, pelicula.isEnCartelera());
            ps.setInt(8, pelicula.getIdPelicula());
            int exito =ps.executeUpdate();
            if (exito ==1) {
                JOptionPane.showMessageDialog(null, "Pelicula Modifcada");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo modificar pelicula"+ ex);
        }
    }
    
    public void EliminarPelicula (int id) {
        String sql = "UPDATE pelicula SET estado = 0 WHERE idpelicula = ?";
        try {
            PreparedStatement  ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            int exito = ps.executeUpdate();
            if (exito ==1) {
                JOptionPane.showMessageDialog(null, "Pelicula Eliminada");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Pelicula "+ ex.getMessage());
        }
    }
    public ArrayList<Pelicula> ListarPeliculas(){
       
        String sql = "SELECT idpelicula, titulo, director, actores, origen, genero, estreno, encartelera FROM pelicula WHERE encartelera = 1";
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try {
            PreparedStatement ps =con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pelicula pelicula= new Pelicula();
                pelicula.setidPelicula(rs.getInt("idpelicula"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
               pelicula.setActores(rs.getString("actores"));
               pelicula.setOrigen(rs.getString("origen"));
               pelicula.setGenero(rs.getString("genero"));
               pelicula.setEstreno(rs.getDate("estreno").toLocalDate());
               pelicula.setEnCartelera(true);
               
               peliculas.add(pelicula);
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ ex.getMessage());
        }
        return peliculas;
    }
}
