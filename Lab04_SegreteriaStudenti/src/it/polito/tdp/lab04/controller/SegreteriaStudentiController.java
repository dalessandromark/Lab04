package it.polito.tdp.lab04.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
private Model model;
	
	public void setModel(Model model) {
		this.model = model;
		boxCorso.setItems(model.getNomiCorso());
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxCorso;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private CheckBox boxSpunta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	String cc = new String();
    	
    	if(boxCorso.getValue()!=null)
    		cc = boxCorso.getValue();
    	else
    		cc = boxCorso.getPromptText();
    	
    	int matricola = Integer.parseInt(txtMatricola.getText());
		String y = model.getCorsiFrequentati(matricola);
    	
		if(cc.compareTo(boxCorso.getPromptText())==0 || cc.compareTo("")==0)
    		txtMessage.setText(y); 
    	else if(model.isIscritto(matricola, cc) == true)
    			txtMessage.setText("La matricola "+matricola+" � iscritta al corso "+cc);
    	else
    			txtMessage.setText("La matricola "+matricola+" non � iscritta al corso "+cc);
    }

    @FXML
    void doCercaIscritti(ActionEvent event) {   	
    	String nomeCorso = boxCorso.getValue();
    	String l = model.getIscrittiCorso(nomeCorso);
    	txtMessage.setText(l);
    }

    @FXML
    void doCompleta(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	String st = model.getStudente(matricola);
    	if(st.contains(";")) {
    		String[] s = st.split(";");
    		txtCognome.setText(s[0]);
    		txtNome.setText(s[1]);
    	} else
    		txtMessage.setText(st);
    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	String nomeCorso = boxCorso.getValue();
    	if(model.iscrivi(matricola, nomeCorso))
    		txtMessage.setText("Matricola "+matricola+" aggiunta correttamente al corso "+" nomeCorso");
    	else
    		txtMessage.setText("Matricola "+matricola+" non � stata aggiunta al corso "+" nomeCorso");
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtMessage.clear();
    }

    @FXML
    void initialize() {
        assert boxCorso != null : "fx:id=\"boxCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert boxSpunta != null : "fx:id=\"boxSpunta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMessage != null : "fx:id=\"txtMessage\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";

    }
}
