package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;

public class Event implements Comparable<Event> {
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI,USCITA_GRUPPO_CLIENTI //costanti simboliche
	}
	private LocalTime time;
	private int numPersone;
	private Duration durata;
	private float tolleranza;
	private EventType type;
	private boolean tavoloAssegnato;
	private int capienzaTavoloAssegnato;
	/**
	 * @return the time
	 */
	public LocalTime getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}
	/**
	 * @return the numPersone
	 */
	public int getNumPersone() {
		return numPersone;
	}
	/**
	 * @param numPersone the numPersone to set
	 */
	public void setNumPersone(int numPersone) {
		this.numPersone = numPersone;
	}
	/**
	 * @return the durata
	 */
	public Duration getDurata() {
		return durata;
	}
	/**
	 * @param durata the durata to set
	 */
	public void setDurata(Duration durata) {
		this.durata = durata;
	}
	/**
	 * @return the tolleranza
	 */
	public float getTolleranza() {
		return tolleranza;
	}
	/**
	 * @param tolleranza the tolleranza to set
	 */
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}
	/**
	 * @param time ora arrivo cliente 
	 * @param numPersone numero delle persone
	 * @param durata durata della permanenza tra 60 e 120 minuti
	 * @param tolleranza toll random
	 * @param type tipologia ARRIVO_GRUPPO_CLIENTI
	 */
	public Event(LocalTime time, int numPersone, Duration durata, float tolleranza, EventType type) {
		super();
		this.time = time;
		this.numPersone = numPersone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.type = type;
		this.tavoloAssegnato = false;
		this.capienzaTavoloAssegnato = 0;
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", numPersone=" + numPersone + ", durata=" + durata + ", tolleranza="
				+ tolleranza + "]";
	}
	public boolean isTavoloAssegnato() {
		return tavoloAssegnato;
	}
	public void setTavoloAssegnato(boolean tavoloAssegnato) {
		this.tavoloAssegnato = tavoloAssegnato;
	}
	public int getCapienzaTavoloAssegnato() {
		return capienzaTavoloAssegnato;
	}
	public void setCapienzaTavoloAssegnato(int capienzaTavoloAssegnato) {
		this.capienzaTavoloAssegnato = capienzaTavoloAssegnato;
	}
	@Override
	public int compareTo(Event o) {
		return this.time.compareTo(o.getTime());
	}
	
	
	
	
	

}
