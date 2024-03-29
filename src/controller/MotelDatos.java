package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Connection;
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
    
    public boolean isOcupada(Habitacion ha){
        boolean flag = false;
        Connection cn = new Connection();

        try {
            int id = ha.getId();

            String sql = "SELECT * FROM habitacion WHERE huesped LIKE 'habitacion%' AND idhabitacion=?";
            PreparedStatement ps = cn.getConnection().prepareStatement(sql);
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
        Connection cn = new Connection();
        try {
            String sql = "UPDATE habitacion SET huesped=? WHERE idhabitacion=?";
            PreparedStatement ps = cn.getConnection().prepareStatement(sql);
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
    
    public boolean desocupar(Habitacion ha) {
        Connection cn = new Connection();
        try {
            int id = ha.getId();

            String sql = "UPDATE habitacion SET huesped=? WHERE idhabitacion=?";
            PreparedStatement ps = cn.getConnection().prepareStatement(sql);
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
        Connection cn = new Connection();
        try {
            String sql = "INSERT INTO historial(huesped, habitacion_id) VALUES (?,?)";
            PreparedStatement ps = cn.getConnection().prepareStatement(sql);
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
        Connection con = new Connection();
        String sql  = "SELECT * FROM historial";
        
        try {
            Statement st = con.getConnection().createStatement();
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
        Connection cn = new Connection();
        Habitacion habitacion;
        ArrayList<Habitacion> habitaciones = new ArrayList();
        String sql = "SELECT * FROM habitacion";

        try {
            Statement st = cn.getConnection().createStatement();
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

    public static ArrayList<String> getTitles(){
        Connection cn = new Connection();
        ArrayList<String> titles = new ArrayList();

        String sql = "SELECT * FROM historial LIMIT 1";

        try{
            Statement st = cn.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                titles.add(rs.getMetaData().getColumnName(i));
            }

            return titles;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        finally {
            cn.close();
        }
    }
}
