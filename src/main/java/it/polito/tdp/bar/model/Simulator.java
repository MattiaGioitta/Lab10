package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import it.polito.tdp.bar.model.Event.EventType;

public class Simulator {
	    //coda degli eventi
		private PriorityQueue<Event> queue = new PriorityQueue<>();
		
		//PARAMETRI DI SIMULAZIONE
		private Tavolo tavolo10 = new Tavolo(2,10);
		private Tavolo tavolo8 = new Tavolo(4,8);
		private Tavolo tavolo6 = new Tavolo(4,6);
		private Tavolo tavolo4 = new Tavolo(5,4);
		private final LocalTime orarioApertura = LocalTime.of(6, 00);
		private final LocalTime orarioChiusura = LocalTime.of(22, 00);
		private final float costante = (float) 0.7;
		
		
		
		//valori da calcolare
		private int numClientiTotali;
		private int numClientiSoddisfatti;
		private int numClientiInsoddisfatti;
		//metodi per restituire i risultati
		/**
		 * @return the numClientiTotali
		 */
		public int getNumClientiTotali() {
			return numClientiTotali;
		}
		/**
		 * @return the numClientiSoddisfatti
		 */
		public int getNumClientiSoddisfatti() {
			return numClientiSoddisfatti;
		}
		/**
		 * @return the numClientiInsoddisfatti
		 */
		public int getNumClientiInsoddisfatti() {
			return numClientiInsoddisfatti;
		}
		
		public void run() {
			this.numClientiInsoddisfatti = 0;
			this.numClientiSoddisfatti = 0;
			this.numClientiTotali = 0;
			this.queue.clear();
			LocalTime oraArrivoGruppoClienti = this.orarioApertura;
			int i = 0;
			//preparazione iniziale (mondo +coda degli eventi)
			do {
				ThreadLocalRandom random = ThreadLocalRandom.current();
				int numPersone = random.nextInt(1,11);
				int d = random.nextInt(60,121);
				Duration durata = Duration.of(d,ChronoUnit.MINUTES);
				int tol = random.nextInt(0,91);
				float tolleranza = tol/10;
				Event e = new Event(oraArrivoGruppoClienti,numPersone,durata,tolleranza,EventType.ARRIVO_GRUPPO_CLIENTI);
				this.queue.add(e);
				int tempo = random.nextInt(1, 11);
				Duration intervallo = Duration.of(tempo, ChronoUnit.MINUTES);
				oraArrivoGruppoClienti = oraArrivoGruppoClienti.plus(intervallo);
				
				
			}while(oraArrivoGruppoClienti.isBefore(this.orarioChiusura));
			
			//esecuzione del ciclo di simulazione
			while(!this.queue.isEmpty()){
				Event e = this.queue.poll();
				//System.out.println(e);
				processEvent(e);
			}
			
		}
		private void processEvent(Event e) {
			switch(e.getType()) {
			case ARRIVO_GRUPPO_CLIENTI:
				int numT = assegnaTavolo(e);
				if(numT==4 || numT==6 || numT==8 || numT==10) {
					this.numClientiSoddisfatti++;
					this.numClientiTotali++;
					Event nuovo = new Event(e.getTime().plus(e.getDurata()),e.getNumPersone(),e.getDurata(),e.getTolleranza(),EventType.USCITA_GRUPPO_CLIENTI);
					nuovo.setTavoloAssegnato(true);
					nuovo.setCapienzaTavoloAssegnato(numT);
					this.queue.add(nuovo);
				}
				else
				{
					if(e.getTolleranza()<=this.costante) {
						this.numClientiSoddisfatti++;
						this.numClientiTotali++;
						Event nuovo = new Event(e.getTime().plus(e.getDurata()),e.getNumPersone(),e.getDurata(),e.getTolleranza(),EventType.USCITA_GRUPPO_CLIENTI);
						this.queue.add(nuovo);
					}
					else {
						//clienti che non possono essere soddisfatti
						this.numClientiInsoddisfatti++;
						this.numClientiTotali++;
					}
					
				}
				
				break;
				
			case USCITA_GRUPPO_CLIENTI:
				if(e.isTavoloAssegnato()) {
					liberaTavolo(e);
				}
				break;
				
					
			}
			
			
		}
		private void liberaTavolo(Event e) {
			//in base alla tipologia di tavolo assegnato, incremento 
			//il numero di tavoli disponibili per la tipologia di quel tavolo
			//poichè in questo momento viene liberato
			if(e.getCapienzaTavoloAssegnato()==4)
				this.tavolo4.setNumTavoloDisponibile(this.tavolo4.getNumTavoloDisponibile()+1);
			if(e.getCapienzaTavoloAssegnato()==6)
				this.tavolo6.setNumTavoloDisponibile(this.tavolo6.getNumTavoloDisponibile()+1);
			if(e.getCapienzaTavoloAssegnato()==8)
				this.tavolo8.setNumTavoloDisponibile(this.tavolo8.getNumTavoloDisponibile()+1);
			if(e.getCapienzaTavoloAssegnato()==10)
				this.tavolo10.setNumTavoloDisponibile(this.tavolo10.getNumTavoloDisponibile()+1);
			
			
			
		}
		private int assegnaTavolo(Event e) {
			/*
			 * bisogna assegnare un tavolo che abbia come caratteristica la seguente:
			 * k come capienza max del tavolo
			 * -->k>=numPersone || k<2*numPersone
			 */
			int numPersone = e.getNumPersone();
			if((4>=numPersone || 4<2*numPersone) && this.tavolo4.getNumTavoloDisponibile()>0) {
				this.tavolo4.setNumTavoloDisponibile(this.tavolo4.getNumTavoloDisponibile()-1);
				return 4;
			}
			if((6>=numPersone || 6<2*numPersone) && this.tavolo6.getNumTavoloDisponibile()>0) {
				this.tavolo6.setNumTavoloDisponibile(this.tavolo6.getNumTavoloDisponibile()-1);
				return 6;
			}
			if((8>=numPersone || 8<2*numPersone) && this.tavolo8.getNumTavoloDisponibile()>0) {
				this.tavolo8.setNumTavoloDisponibile(this.tavolo8.getNumTavoloDisponibile()-1);
				return 8;
			}
			if((10>=numPersone || 10<2*numPersone) && this.tavolo10.getNumTavoloDisponibile()>0) {
				this.tavolo10.setNumTavoloDisponibile(this.tavolo10.getNumTavoloDisponibile()-1);
				return 10;
			}
			
			return -1;
		}
			
		
		
		
		

}
