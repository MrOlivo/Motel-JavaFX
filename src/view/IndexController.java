package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Historial;

public class IndexController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView tablaHistorial;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargeTable();
    }

    void chargeTable () {

        TableColumn<String, model.Historial> columna1 = new TableColumn<>("ID");
        columna1.setCellValueFactory(new PropertyValueFactory<>("idhistorial"));

        TableColumn<String, model.Historial> columna2 = new TableColumn<>("Huesped");
        columna2.setCellValueFactory(new PropertyValueFactory<>("huesped"));

        TableColumn<String, model.Historial> columna3 = new TableColumn<>("Fecha");
        columna3.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn<String, model.Historial> columna4 = new TableColumn<>("Habitación");
        columna4.setCellValueFactory(new PropertyValueFactory<>("habitacion_id"));

        tablaHistorial.getColumns().add(columna1);
        tablaHistorial.getColumns().add(columna2);
        tablaHistorial.getColumns().add(columna3);
        tablaHistorial.getColumns().add(columna4);

        ArrayList<Historial> historial = controller.MotelDatos.getHistorial();

        for (Object registro : historial) {
            tablaHistorial.getItems().add(registro);
        }

    }

}