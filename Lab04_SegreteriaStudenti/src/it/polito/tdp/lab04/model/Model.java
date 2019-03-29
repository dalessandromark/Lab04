package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	private List<Corso> corsi;
	private CorsoDAO c;
	private StudenteDAO s;
	
	public Model() {
		s = new StudenteDAO();
		c = new CorsoDAO();
	}
	
	public ObservableList<String> getNomiCorso() {
		corsi = c.getTuttiICorsi();
		List<String> res = new ArrayList<String>();
		
		for(Corso c : corsi) {
			res.add(c.getNome());
		}
		
		res.add("");
		
		ObservableList<String> l = FXCollections.observableList(res);
		
		return l;
	}
	
	
	 public String getStudente(int matricola) {
		 return s.getStudente(matricola);
	 }
	 
	 public String getIscrittiCorso(String nomeCorso) {
		 List<Studente> iscritti = new ArrayList<Studente>(c.getStudentiIscrittiAlCorso(nomeCorso));
		 String lista = new String();
		 
		 for(Studente x : iscritti) {
			 lista += x.getMatricola()+" "+x.getNome()+" "+x.getCognome()+" "+x.getCds()+"\n";
		 }
		 
		 return lista;
	 }
	 
	 public String getCorsiFrequentati(int matricola) {
		 List<Corso> corsiF = new ArrayList<Corso>(s.getCorsiFrequentati(matricola)); 
		 String lCorsi= new String();
		 
		 if(corsiF.isEmpty())
			 lCorsi = "Matricola errata";
		 else {
			 for(Corso x : corsiF) {
				 lCorsi += x.getCodins()+" "+x.getCrediti()+" "+x.getNome()+" "+x.getPd()+"\n";
			 }
		 }
		 
		 return lCorsi;
	 }
	 
	 public boolean isIscritto(int matricola, String nomeCorso) {
		 if (this.getCorsiFrequentati(matricola).contains(nomeCorso))
			 return true;
		 else 
			 return false;
	 }
	 
	 
	 public boolean esiste(int matricola) {
		 String stud = s.getStudente(matricola);
		 if(stud.contains(";"))
			 return true;
		 else
			 return false;
	 }
	 
	 public boolean iscrivi(int matricola, String nomeCorso) {
		 if(this.esiste(matricola) && nomeCorso != null)
			return c.inscriviStudenteACorso(matricola, nomeCorso);
		 else 
			 return false;
	 }
	
}
