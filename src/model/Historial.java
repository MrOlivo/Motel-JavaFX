/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author olivo
 */
public class Historial {
    private Integer idhistorial;
    private String huesped;
    private java.util.Date fecha;
    private Integer habitacion_id;

    public Historial() {
    }

    public Historial(Integer idhistorial, String huesped, Date fecha, Integer habitacion_id) {
        this.idhistorial = idhistorial;
        this.huesped = huesped;
        this.fecha = fecha;
        this.habitacion_id = habitacion_id;
    }

    public Integer getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(Integer idhistorial) {
        this.idhistorial = idhistorial;
    }

    public String getHuesped() {
        return huesped;
    }

    public void setHuesped(String huesped) {
        this.huesped = huesped;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(Integer habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

    @Override
    public String toString() {
        return "Historial{" + "idhistorial=" + idhistorial + ", huesped=" + huesped + ", fecha=" + fecha + ", habitacion_id=" + habitacion_id + '}';
    }
}
