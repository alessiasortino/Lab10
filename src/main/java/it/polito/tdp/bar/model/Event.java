package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event implements Comparable <Event>{
	
	//prima di tutto devo chiedermi quanti tipi di eventi ci sono
	// in questo caso due
	//1 clienti arrivano
	//2 clienti se ne vanno
	
	public enum EventType{
		ARRIVO_GRUPPO_CLIENTI,
		TAVOLO_LIBERATO ,
	}
	
	
	private Duration time;
	private EventType type;
    private	int num_persone;
	private Duration durata;
	private float tolleranza;
	private Tavolo tavolo;
	/**
	 * @param time
	 * @param type
	 * @param num_persone
	 * @param durata
	 * @param tolleranza
	 * @param tavolo
	 */
	public Event(Duration time, EventType type, int num_persone, Duration durata, float tolleranza, Tavolo tavolo) {
		super();
		this.time = time;
		this.type = type;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
		this.tavolo = tavolo;
	}
	public Duration getTime() {
		return time;
	}
	public void setTime(Duration time) {
		this.time = time;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public int getNum_persone() {
		return num_persone;
	}
	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}
	public Duration getDurata() {
		return durata;
	}
	public void setDurata(Duration durata) {
		this.durata = durata;
	}
	public float getTolleranza() {
		return tolleranza;
	}
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	public Tavolo getTavolo() {
		return tavolo;
	}
	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", num_persone=" + num_persone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + ", tavolo=" + tavolo + "]";
	}
	@Override
	public int compareTo(Event o) {
		
		return this.time.compareTo(o.time);
	}

	
	

}
