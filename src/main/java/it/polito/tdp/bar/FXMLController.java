/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Model;
import it.polito.tdp.bar.model.Statistiche;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class FXMLController {
	Model model;
	Statistiche stat= new Statistiche();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleSimula(ActionEvent event) {
     stat= this.model.simula();
     if(stat!= null) {
txtResult.appendText(String.format("Totali: %d\nSoddisfatti: %d\nInsoddisfatti: %d\n", stat.getNumClientiTot(), stat.getNumClientiSoddisfatti(), stat.getNumClientiInsoddisfatti()));
int soddisfatti=(int)(stat.getNumClientiSoddisfatti()/stat.getNumClientiTot())*100;
int insoddisfatti= (int) (stat.getNumClientiInsoddisfatti()/stat.getNumClientiTot())*100;
     txtResult.appendText(String.format("Percentuale soddisfatti: %d , Percentuale insoddisfatti %d ", soddisfatti,insoddisfatti));
    }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    public void setModel(Model model) {
    	this.model=  model;
    }
}

