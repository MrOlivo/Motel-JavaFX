package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CargarConexion;
import model.Habitacion;
import model.Historial;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olivo
 */

public class MotelDatos{
    
    public MotelDatos(){
    }
    
    public boolean isOcupada(int id){
        boolean flag = false;
        CargarConexion cn = new CargarConexion();

        try {
            String sql = "SELECT * FROM habitacion WHERE huesped LIKE 'habitacion%' AND idhabitacion=?";
            PreparedStatement ps = cn.getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            flag = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(MotelDatos.class.getName()).log(Level.SEVERE, null, ex);
        }

        finally {
            cn.close();
        }
        return flag;
    }
    
    public boolean registrar(Habitacion ha) {
        CargarConexion cn = new CargarConexion();
        try {
            String sql = "UPDATE habitacion SET huesped=? WHERE idhabitacion=?";
            PreparedStatement ps = cn.getConexion().prepareStatement(sql);
            ps.setString(1, ha.getHuesped());
            ps.setInt(2, ha.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error: "+ ex.getMessage());
            return false;
        }

        finally{
            cn.close();
        }
        
        return true;
    }
    
    public boolean desocupar(int id) {
        CargarConexion cn = new CargarConexion();
        try {
            String sql = "UPDATE habitacion SET huesped=? WHERE idhabitacion=?";
            PreparedStatement ps = cn.getConexion().prepareStatement(sql);
            String a = "Habitacion "+ id;
            ps.setString(1, a);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error: "+ ex.getMessage());
            return false;
        }

        finally {
            cn.close();
        }
        
        return true;
    }
    
    public boolean registrarHistorial(Habitacion ha) {
        CargarConexion cn = new CargarConexion();
        try {
            String sql = "INSERT INTO historial(huesped, habitacion_id) VALUES (?,?)";
            PreparedStatement ps = cn.getConexion().prepareStatement(sql);
            ps.setString(1, ha.getHuesped());
            ps.setInt(2, ha.getId());
            return ps.execute();
        } catch (SQLException ex) {
            System.out.println("Error: "+ ex.getMessage());
            return false;
        }
        
        finally {
            cn.close();
        }
    }
    
    public static ArrayList<Historial> getHistorial(){
        ArrayList historial = new ArrayList();
        CargarConexion con = new CargarConexion();
        String sql;
        sql = "SELECT * FROM historial";
        
        try {
            Statement st = con.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            Historial registro;
            
            while(rs.next()){
                registro = new Historial();
                registro.setIdhistorial(rs.getInt("idhistorial"));
                registro.setHuesped(rs.getString("huesped"));
                registro.setFecha(rs.getTimestamp("fecha"));
                registro.setHabitacion_id(rs.getInt("habitacion_id"));

                historial.add(registro);
            }
            
            st.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MotelDatos.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Vector lis() error: "+ ex.getMessage());
            return null;
        } finally {
            con.close();
        }
        
        return historial;
    }
    
    public ArrayList<Habitacion> listarHabitaciones(){
        CargarConexion cn = new CargarConexion();
        Habitacion habitacion;
        ArrayList<Habitacion> habitaciones = new ArrayList();
        
        try {
            String sql = "SELECT * FROM habitacion";
            Statement st = cn.getConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                habitacion = new Habitacion();
                habitacion.setId(rs.getInt("idhabitacion"));
                habitacion.setHuesped(rs.getString("huesped"));

                habitaciones.add(habitacion);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error: "+ ex.getMessage());
            return null;
        }
        
        finally{
            cn.close();
        }
        
        return habitaciones;
    }
    public static Vector titles(){
        Vector titles = new Vector();
        titles.add("ID");
        titles.add("HUESPED");
        titles.add("FECHA");
        titles.add("HABITACIÓN");
        return titles;
    }
}
