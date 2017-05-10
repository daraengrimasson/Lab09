package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> cmbPartenza;

    @FXML
    private ComboBox<Fermata> cmbArrivo;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	//prima di tutto creo il grafo
    	txtResult.appendText(model.createGraph());
    	txtResult.appendText("\n");
    	txtResult.appendText(model.calcolaPercorso(cmbPartenza.getValue(), cmbArrivo.getValue()));
    }

    @FXML
    void initialize() {
        assert cmbPartenza != null : "fx:id=\"cmbPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert cmbArrivo != null : "fx:id=\"cmbArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        

    }

	public void setModel(Model model) {
		this.model=model;
		cmbPartenza.getItems().addAll(model.getAllFermate());
		cmbArrivo.getItems().addAll(model.getAllFermate());
	}
}

