package it.polito.tdp.bar.model;

public class Tavolo {
	int nposti;
	boolean occupato;
	/**
	 * @param nposti
	 * @param occupato
	 */
	public Tavolo(int nposti, boolean occupato) {
		super();
		this.nposti = nposti;
		this.occupato = occupato;
	}
	public int getNposti() {
		return nposti;
	}
	public void setNposti(int nposti) {
		this.nposti = nposti;
	}
	public boolean isOccupato() {
		return occupato;
	}
	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
	
	
	

}
