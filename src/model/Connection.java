/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author olivo
 */
public class Connection {

    private java.sql.Connection conn = null;
    private final String user = "root";
    private final String pass = "lunanova";

    public Connection() {
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/motel?serverTimezone=PST", user, pass);
            
        } catch (ClassNotFoundException ex) {
            
            System.err.println("Error al cargar el Driver");
            dialogoError(ex.getMessage());
            
        } catch (SQLException ex) {
            
            System.err.println("Error al conectar a la base de datos.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            
            dialogoError(ex.getMessage());
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la base de datos:");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public java.sql.Connection getConnection() {
        return conn;
    }

    public static void dialogoError(String error) {
        JOptionPane.showMessageDialog(null, error, "ERROR BD", JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
}
