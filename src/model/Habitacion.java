/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author olivo
 */
public class Habitacion {
    private int id;
    private String huesped;
    
    public Habitacion(){
    }

    public Habitacion(int id, String huesped) {
        this.id = id;
        this.huesped = huesped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHuesped() {
        return huesped;
    }

    public void setHuesped(String huesped) {
        this.huesped = huesped;
    }
}
