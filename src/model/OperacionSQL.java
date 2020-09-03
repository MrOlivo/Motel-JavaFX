/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;

/**
 *
 * @author olivo
 */
public interface OperacionSQL {
    Connection Conectar();
    boolean isOcupada();
    boolean registrar();
    boolean desocupar();
    boolean registrarHistorial();
}
