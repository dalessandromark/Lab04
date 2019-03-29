package it.polito.tdp.lab04.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	public ArrayList<Studente> getElencoStudenti(){
		
		final String sql = "SELECT * FROM studente";
		
		ArrayList<Studente> studenti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("CDS");
				int matricola = rs.getInt("matricola");
				
				System.out.println(nome+" "+cognome+" "+matricola+" "+cds);
				
				Studente nuovo = new Studente(matricola, cognome, nome, cds);
				studenti.add(nuovo);
			}
			
			return studenti;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new RuntimeException("Erore Db");
		}	
		
	}
	
	/*
	 * Ottengo stringa concatenata di cognome e nome dello studente a partire dalla sua matricola
	 */
	public String getStudente(int matricola) {
		
		final String sql = "SELECT cognome, nome FROM studente WHERE matricola = ? ";
			
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
				
			String s = new String();
				
			if(rs.next()) {
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				s = cognome+";"+nome;
			} else
				s = "Nessuna Corrispondenza";
					
			return s;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Corso> getCorsiFrequentati(int matricola) {
		
		final String sql = "SELECT * FROM corso WHERE codins IN (SELECT codins\r\n" + 
				"												FROM iscrizione\r\n" + 
				"												WHERE matricola = ? )";
		
		List<Corso> corsi = new ArrayList<Corso>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				
				Corso c = new Corso(codins, crediti, nome, pd);
				corsi.add(c);
			}
			
			return corsi;
			
		} catch(SQLException e) {
			throw new RuntimeException("Errore Db");
		}
	}
	
		
		
}
