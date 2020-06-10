package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
// Modello del mondo
	private List <Tavolo> tavoli;
	
	//Parametri simulazione

private int NUM_EVENTI=2000;// nmax eventi
private int T_MIN_ARRIVO_MAX= 10;
private int NUM_PERSONE_MAX= 10;
private int DURATA_MIN= 60;
private int DURATA_MAX= 120;
private double TOLLERANZA_MAX = 0.9;
private double OCCUPAZIONE_MIN=0.5;//SE NON CI SONO TAVOLI DISP
//POSSO OCCUPARE UN TAVOLO PIU GRANDE MA ALMENO IL 50% 
//DEVE ESSERE RIEMPITO


//valori calcolati
private Statistiche stat;

//Coda degli eventi
private PriorityQueue<Event> queue;

public void caricaTavoli() {
	this.tavoli= new ArrayList();
	
	aggiungiTavolo(2,10);
	aggiungiTavolo(4,8);
	aggiungiTavolo(4,6);
	aggiungiTavolo(5,4);
	// ordino i tavoli dal piu piccolo al piu grande cosi facilito le ricerche
	Collections.sort(this.tavoli,new Comparator <Tavolo>(){
		@Override
	
		public int compare(Tavolo o1, Tavolo o2) {
			return -(o1.getNposti()-o2.getNposti());
		}
	});
		
	
}


private void aggiungiTavolo(int num, int nPosti) {
	
	//crea num tavoli da nposti

	for(int i=0; i<num; i++) {
		Tavolo t= new Tavolo(nPosti,false);
		this.tavoli.add(t);
	}
	
}

private void caricaEventi() {
	Duration arrivo= Duration.ofMinutes(0);
	//parto a contare da zero
	for(int i =0; i<this.NUM_EVENTI; i++) {
		int numPersone= (int) (Math.random()*this.NUM_PERSONE_MAX+1);
		Duration durata = 
		//alla durata minima aggiungo un num random tra 0 e 60
		Duration.ofMinutes(this.DURATA_MIN+ (int) (Math.random()* (this.DURATA_MAX- this.DURATA_MIN)));
	float tolleranza= (float) (Math.random()*this.TOLLERANZA_MAX);
	
	//creo evento
	Event e= new Event(arrivo, EventType.ARRIVO_GRUPPO_CLIENTI, numPersone,durata,tolleranza, null);
	this.queue.add(e);
	arrivo= arrivo.plusMinutes(1+(int)(Math.random()*this.T_MIN_ARRIVO_MAX));
	
	}
}

public void init() {
	caricaTavoli();
	this.queue= new PriorityQueue<>();
	caricaEventi();
	
	this.stat= new Statistiche();
	
	
	
}

public void run() {
	while(!queue.isEmpty()) {
		Event e= queue.poll();
		System.out.println(e);
		processEvent(e);
	}
}

private void processEvent(Event e) {
	switch(e.getType()) {
	case ARRIVO_GRUPPO_CLIENTI:
		//conta clienti
		stat.addNumClientiTot(e.getNum_persone());
		//cerca tavolo
		Tavolo trovato= null;
		 for(Tavolo t: this.tavoli) {
			 //se il tavolo è libero, i posti sono piu delle persone ,il tasso di occupazione è almeno il 50%
			 if(!t.isOccupato() && t.getNposti()>= e.getNum_persone() && t.getNposti()*this.OCCUPAZIONE_MIN<= e.getNum_persone()){
				 trovato =t;
				 break;// essendo ordinati in ordine crescente il primo che trova sarà il più piccolo
				 
			 }
			 
		 }
		 if(trovato!= null) {
			 System.out.format("Sedute %d persone in tavolo da %d posti", e.getNum_persone(),trovato.getNposti());
		 trovato.setOccupato(true);
		 stat.addNumClientiSoddisfatti(e.getNum_persone());
		 
		 // quando si alzeranno(time+durata)
		 queue.add(new Event(e.getTime().plus(e.getDurata()), EventType.TAVOLO_LIBERATO, e.getNum_persone(), e.getDurata(),e.getTolleranza(),trovato));
		
		 
		 }else {
			 float bancone= (float) Math.random();
			 if(bancone<= e.getTolleranza()) {
				 //vado al bancone
				 stat.addNumClientiSoddisfatti(e.getNum_persone());
			 }
		 else {
			 //vado a casa
			 stat.addNumClientiInsoddisfatti(e.getNum_persone());
		 }
		 
	}
		 break;
	case TAVOLO_LIBERATO:
		e.getTavolo().setOccupato(false);
		
		break;
		  
		 
}
}


public Statistiche getStat() {
	return stat;
}

}