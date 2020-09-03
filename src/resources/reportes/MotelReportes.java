/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.reportes;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author olivo
 */
public class MotelReportes {

    public MotelReportes() {
    }
    
    public void ReporteDiario(String date){
        String path = "src/resources/reportes/reporteDiario.jasper";
        JasperPrint report;
        Map params = new HashMap();
        params.put("Parameter1", date);
        try {
            report = JasperFillManager.fillReport(path, params, new model.CargarConexion().getConexion());
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            System.err.println("No se cargo el reporte papu: " + ex.getMessage());
        }
    }
    
    public void ReporteGeneral(){
        String path = "src/resources/reportes/reporteGeneral.jasper";
        JasperPrint report;
        Map params = new HashMap();
        try {
            report = JasperFillManager.fillReport(path, params, new model.CargarConexion().getConexion());
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            System.err.println("No se cargo el reporte papu: " + ex.getMessage());
        }
    }
    
    public void ReporteHabitacion(int id){
        String path = "src/resources/reportes/reporteHabitacion.jasper";
        JasperPrint report;
        Map params = new HashMap();
        params.put("Parameter1", id);
        params.put("Empresa", "UwU Company Inc.");
        try {
            report = JasperFillManager.fillReport(path, params, new model.CargarConexion().getConexion());
            JasperViewer.viewReport(report, false);
        } catch (JRException ex) {
            System.err.println("No se cargo el reporte papu: " + ex.getMessage());
        }
    }
}
