package it.polito.tdp.metrodeparis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.javadocmd.simplelatlng.LatLng;
import com.sun.javafx.collections.MappingChange.Map;

import it.polito.tdp.metrodeparis.model.Connessione;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;

public class MetroDAO {
	private HashMap<Integer,Fermata> fermate;
	private HashMap<Integer, Linea> linee=this.getLinee();
	private HashMap<Integer, Connessione> connessioni;

	public HashMap<Integer,Fermata> getAllFermate() {

		fermate =  new HashMap <Integer,Fermata>();
		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(rs.getInt("id_Fermata"), rs.getString("nome"), new LatLng(rs.getDouble("coordx"), rs.getDouble("coordy")));
				fermate.put(rs.getInt("id_Fermata"),f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}
	
	
	public HashMap<Integer,Linea> getLinee(){
		final String sql="SELECT * FROM connessione, linea WHERE linea.id_linea=connessione.id_linea";
		linee=new HashMap<Integer,Linea>();
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea l= new Linea(rs.getInt("id_linea"),rs.getString("nome"),rs.getDouble("velocita"),rs.getInt("intervallo"));
				linee.put(l.getId(), l);
			}

			st.close();
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return linee;
	}
	
	
	public HashMap<Integer,Connessione> getConnessioni(){
		final String sql="SELECT * FROM connessione";
		connessioni=new HashMap<Integer,Connessione>();
		

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f1= (fermate.get(rs.getInt("id_stazP")));
				Fermata f2= (fermate.get(rs.getInt("id_stazA")));
				Linea l= linee.get(rs.getInt("id_linea"));
				Connessione c=new Connessione(f1,f2,l,rs.getInt("id_connessione"));
				connessioni.put(c.getIdConn(), c);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		return connessioni;
	}
	
}
