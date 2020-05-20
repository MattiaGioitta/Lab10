package it.polito.tdp.bar.model;

public class Tavolo {
	private int numTavoloDisponibile;
	private int capienzaTavolo;
	
	/**
	 * @return the capienzaTavolo
	 */
	public int getCapienzaTavolo() {
		return capienzaTavolo;
	}
	/**
	 * @param capienzaTavolo the capienzaTavolo to set
	 */
	public void setCapienzaTavolo(int capienzaTavolo) {
		this.capienzaTavolo = capienzaTavolo;
	}
	/**
	 * @param numTavolo
	 * @param capienzaTavolo
	 */
	public Tavolo(int numTavoloDisponibile, int capienzaTavolo) {
		super();
		this.numTavoloDisponibile = numTavoloDisponibile;
		this.capienzaTavolo = capienzaTavolo;
	}
	public int getNumTavoloDisponibile() {
		return numTavoloDisponibile;
	}
	public void setNumTavoloDisponibile(int numTavoloDisponibile) {
		this.numTavoloDisponibile = numTavoloDisponibile;
	}
	
	

}
