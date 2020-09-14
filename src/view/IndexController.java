package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import controller.MotelDatos;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Historial;

public class IndexController implements Initializable {

    @FXML
    private ResourceBundle resources;
    private URL location;

    @FXML
    private TableView tablaHistorial;
    public Button btnReporte;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargeTable();
    }

    public void chargeTable () {

        ArrayList<String> encabezados = MotelDatos.getTitles();

        try{
            for (String encabezado : encabezados) {
                TableColumn<String, model.Historial> columna = new TableColumn<>(encabezado.toUpperCase());
                columna.setCellValueFactory(new PropertyValueFactory<>(encabezado));
                tablaHistorial.getColumns().add(columna);
            }

            ArrayList<Historial> historial = MotelDatos.getHistorial();

            for (Object registro : historial) {
                tablaHistorial.getItems().add(registro);
            }

        } catch(NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

    }

    public void generarReporte() {
        new resources.reportes.MotelReportes().ReporteGeneral();
    }

}