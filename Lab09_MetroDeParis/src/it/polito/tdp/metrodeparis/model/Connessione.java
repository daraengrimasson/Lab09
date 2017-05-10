package it.polito.tdp.metrodeparis.model;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class Connessione{
	private Fermata fermata1;
	private Fermata fermata2;
	private Linea l;
	private int idConn;
	private double tempo;
	
	public Connessione(Fermata fermata1, Fermata fermata2, Linea l, int idConn) {
		this.fermata1 = fermata1;
		this.fermata2 = fermata2;
		this.l = l;
		this.idConn = idConn;
		tempo=this.doCalcolaTempo();
	}

	private double doCalcolaTempo() {
		double distanza=LatLngTool.distance(new LatLng(fermata1.getCoords().getLatitude(), fermata1.getCoords().getLongitude()), new LatLng(fermata2.getCoords().getLatitude(), fermata1.getCoords().getLongitude()), LengthUnit.KILOMETER);
		tempo= distanza/l.getVelocita();
		return tempo;
	}

	public Fermata getFermata1() {
		return fermata1;
	}

	public void setFermata1(Fermata fermata1) {
		this.fermata1 = fermata1;
	}

	public Fermata getFermata2() {
		return fermata2;
	}

	public void setFermata2(Fermata fermata2) {
		this.fermata2 = fermata2;
	}

	public Linea getL() {
		return l;
	}

	public void setL(Linea l) {
		this.l = l;
	}

	public int getIdConn() {
		return idConn;
	}

	public void setIdConn(int idConn) {
		this.idConn = idConn;
	}
	
	public double getTempo(){
		return tempo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idConn;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessione other = (Connessione) obj;
		if (idConn != other.idConn)
			return false;
		return true;
	}

	
	
	
}
