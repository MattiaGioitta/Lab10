package it.polito.tdp.bar.model;

import java.util.PriorityQueue;

public class Model {
	
	private Simulator simulatore;
	
	public Model () {
		this.simulatore = new Simulator();
	}
	public void simula() {
		this.simulatore.run();
		}
	public int getNumeroClientiTotali() {
		return this.simulatore.getNumClientiTotali();
	}
	public int getNumeroClientiSoddisfatti() {
		return this.simulatore.getNumClientiSoddisfatti();
	}
	public int getNumeroClientiInsoddisfatti() {
		return this.simulatore.getNumClientiInsoddisfatti();
	}
	
	

}
