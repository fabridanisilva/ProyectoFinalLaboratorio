/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import Modelo.Conexion;
import Modelo.Pelicula;
import Modelo.Proyeccion;
import Modelo.Sala;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author fabri
 */
public class ProyeccionData {
    Connection con = null;

    public ProyeccionData() {
        con = Conexion.getConexion();
    }
    
    
    public void ingresarProyeccion(Proyeccion proyeccion){
    
        String sql = "INSERT INTO `proyeccion`(`idPelicula`, `idioma`, `es3d`, `subtitulada`, `horainicio`, `horafin`, `cantidadLugaresDisponibles`, `salaProyeccion`, `precioLugar`)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, proyeccion.getPelicula().getIdPelicula());
            ps.setString(2, proyeccion.getIdioma());
            ps.setBoolean(3, proyeccion.isEs3D());
            ps.setBoolean(4, proyeccion.isSubtitulada());
            ps.setTime(5, Time.valueOf(proyeccion.getHorInicio()));
            ps.setTime(6, Time.valueOf(proyeccion.getHoraFin()));
            ps.setInt(7, proyeccion.getCantidadLugaresDisponibles());
            ps.setInt(8, proyeccion.getSala().getNroSala());
            ps.setDouble(9, proyeccion.getPrecio());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                proyeccion.setIdFuncion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Se agrego la funcion!!");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
    }
    
    
    public void modificarProyeccion(Proyeccion proyeccion){
    
        String sql = "UPDATE proyeccion SET idPelicula=?,idioma=?,es3d=?,"
                + "subtitulada=?,horainicio=?,horafin=?,cantidadLugaresDisponibles=?,salaProyeccion=?,precioLugar=? WHERE idFuncion=?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, proyeccion.getPelicula().getIdPelicula());
            ps.setString(2, proyeccion.getIdioma());
            ps.setBoolean(3, proyeccion.isEs3D());
            ps.setBoolean(4,proyeccion.isSubtitulada());
            ps.setTime(5, Time.valueOf(proyeccion.getHorInicio()));
            ps.setTime(6, Time.valueOf(proyeccion.getHoraFin()));
            ps.setInt(7, proyeccion.getCantidadLugaresDisponibles());
            ps.setInt(8, proyeccion.getSala().getNroSala());
            ps.setDouble(9, proyeccion.getPrecio());
            ps.setInt(10, proyeccion.getIdFuncion());
            
            int exito = ps.executeUpdate();
            
            if (exito==1) {
                JOptionPane.showMessageDialog(null, "Funcion modificada!!");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    
    }
    
    public void eliminarProyeccion(int idFuncion){
    
        String sql = "DELETE FROM `proyeccion` WHERE idFuncion = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, idFuncion);
            
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Se borro exitosamente.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
    
    }
    
    
    public Proyeccion buscarProyeccion(int idFuncion){
    Proyeccion proyeccion = null;
        String sql = "SELECT idPelicula, idFuncion, idioma, es3d, subtitulada, horainicio, horafin, cantidadLugaresDisponibles, salaProyeccion, precioLugar FROM proyeccion WHERE idFuncion=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            
            ps.setInt(1, idFuncion);
            
            ResultSet rs = ps.executeQuery();
        
        SalaData sd = new SalaData();
        PeliculaData pd = new PeliculaData();
            
            if (rs.next()) {     
                proyeccion = new Proyeccion();
                
                Pelicula pelicula = null;
                Sala sala = null;
                
                pelicula = pd.BuscarPelicula(rs.getInt("idPelicula")); // la funcion BuscarPelicula de la clase PeliculaData va retornar la pelicua en base el id que mandamos por parametro
                proyeccion.setPelicula(pelicula);
                proyeccion.setIdFuncion(rs.getInt("idFuncion"));
                proyeccion.setIdioma(rs.getString("idioma"));
                proyeccion.setEs3D(rs.getBoolean("es3d"));
                proyeccion.setSubtitulada(rs.getBoolean("subtitulada"));
                proyeccion.setHorInicio(rs.getTime("horainicio").toLocalTime());
                proyeccion.setHoraFin(rs.getTime("horafin").toLocalTime());
                proyeccion.setCantidadLugaresDisponibles(rs.getInt("cantidadLugaresDisponibles"));
                sala = sd.buscarSala(rs.getInt("salaProyeccion")); //la funcion buscarSala de la calse SalaData va aretornar la sala en base el numero de sala
                proyeccion.setSala(sala);
                proyeccion.setPrecio(rs.getDouble("precioLugar"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe la funcion.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return proyeccion;
    }
    
        public Proyeccion buscarProyeccionprPelicula(int idPelicula){
    Proyeccion proyeccion = null;
        String sql = "SELECT idPelicula, idFuncion, idioma, es3d, subtitulada, horainicio, horafin, cantidadLugaresDisponibles, salaProyeccion, precioLugar FROM proyeccion WHERE idPelicula=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            
            ps.setInt(1, idPelicula);
            
            ResultSet rs = ps.executeQuery();
        
        SalaData sd = new SalaData();
        PeliculaData pd = new PeliculaData();
            
            if (rs.next()) {     
                proyeccion = new Proyeccion();
                
                Pelicula pelicula = null;
                Sala sala = null;
                
                pelicula = pd.BuscarPelicula(rs.getInt("idPelicula")); // la funcion BuscarPelicula de la clase PeliculaData va retornar la pelicua en base el id que mandamos por parametro
                proyeccion.setPelicula(pelicula);
                proyeccion.setIdFuncion(rs.getInt("idFuncion"));
                proyeccion.setIdioma(rs.getString("idioma"));
                proyeccion.setEs3D(rs.getBoolean("es3d"));
                proyeccion.setSubtitulada(rs.getBoolean("subtitulada"));
                proyeccion.setHorInicio(rs.getTime("horainicio").toLocalTime());
                proyeccion.setHoraFin(rs.getTime("horafin").toLocalTime());
                proyeccion.setCantidadLugaresDisponibles(rs.getInt("cantidadLugaresDisponibles"));
                sala = sd.buscarSala(rs.getInt("salaProyeccion")); //la funcion buscarSala de la calse SalaData va aretornar la sala en base el numero de sala
                proyeccion.setSala(sala);
                proyeccion.setPrecio(rs.getDouble("precioLugar"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe la funcion.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return proyeccion;
    }
    
                public Proyeccion buscarProyeccionprSala(int NROsala){
    Proyeccion proyeccion = null;
        String sql = "SELECT idPelicula, idFuncion, idioma, es3d, subtitulada, horainicio, horafin, cantidadLugaresDisponibles, salaProyeccion, precioLugar FROM proyeccion WHERE salaProyeccion=?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            
            
            ps.setInt(1, NROsala);
            
            ResultSet rs = ps.executeQuery();
        
        SalaData sd = new SalaData();
        PeliculaData pd = new PeliculaData();
            
            if (rs.next()) {     
                proyeccion = new Proyeccion();
                
                Pelicula pelicula = null;
                Sala sala = null;
                
                pelicula = pd.BuscarPelicula(rs.getInt("idPelicula")); // la funcion BuscarPelicula de la clase PeliculaData va retornar la pelicua en base el id que mandamos por parametro
                proyeccion.setPelicula(pelicula);
                proyeccion.setIdFuncion(rs.getInt("idFuncion"));
                proyeccion.setIdioma(rs.getString("idioma"));
                proyeccion.setEs3D(rs.getBoolean("es3d"));
                proyeccion.setSubtitulada(rs.getBoolean("subtitulada"));
                proyeccion.setHorInicio(rs.getTime("horainicio").toLocalTime());
                proyeccion.setHoraFin(rs.getTime("horafin").toLocalTime());
                proyeccion.setCantidadLugaresDisponibles(rs.getInt("cantidadLugaresDisponibles"));
                sala = sd.buscarSala(rs.getInt("salaProyeccion")); //la funcion buscarSala de la calse SalaData va aretornar la sala en base el numero de sala
                proyeccion.setSala(sala);
                proyeccion.setPrecio(rs.getDouble("precioLugar"));
            }else{
                JOptionPane.showMessageDialog(null, "No existe la funcion.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return proyeccion;
    }
        
    public ArrayList<Proyeccion> listarProyecciones(){
        ArrayList<Proyeccion> proyecciones = new ArrayList<>();
        
        
        
        SalaData sd = new SalaData();
        PeliculaData pd = new PeliculaData();
        String sql = "SELECT idPelicula, idFuncion, idioma, es3d, subtitulada, horainicio, horafin, cantidadLugaresDisponibles, salaProyeccion, precioLugar FROM proyeccion ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {     
                Proyeccion proyeccion = new Proyeccion();
                
                Pelicula pelicula = null;
                Sala sala = null;
                
                pelicula = pd.BuscarPelicula(rs.getInt("idPelicula")); // la funcion BuscarPelicula de la clase PeliculaData va retornar la pelicua en base el id que mandamos por parametro
                proyeccion.setPelicula(pelicula);
                proyeccion.setIdFuncion(rs.getInt("idFuncion"));
                proyeccion.setIdioma(rs.getString("idioma"));
                proyeccion.setEs3D(rs.getBoolean("es3d"));
                proyeccion.setSubtitulada(rs.getBoolean("subtitulada"));
                proyeccion.setHorInicio(rs.getTime("horainicio").toLocalTime());
                proyeccion.setHoraFin(rs.getTime("horafin").toLocalTime());
                proyeccion.setCantidadLugaresDisponibles(rs.getInt("cantidadLugaresDisponibles"));
                sala = sd.buscarSala(rs.getInt("salaProyeccion")); //la funcion buscarSala de la calse SalaData va aretornar la sala en base el numero de sala
                proyeccion.setSala(sala);
                proyeccion.setPrecio(rs.getDouble("precioLugar"));
                
                
                proyecciones.add(proyeccion);
            }
            ps.close();
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        
        
        
        
        return proyecciones;
    
    
    }
    
    public void restarAsientoPorFuncion(int idFuncion){
        
        String sql = "UPDATE `proyeccion` SET `cantidadLugaresDisponibles` = `cantidadLugaresDisponibles` - 1 WHERE `idFuncion` = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, idFuncion);
            
            int exito = ps.executeUpdate();
            
            if (exito ==1) {
                JOptionPane.showMessageDialog(null, "Se resto un lugar");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        
        
    
    }

    
    
    
    
}
