package com.horaire;

import java.sql.Time;

public class Horaire {
	
	private long horaire_id;
	private JourSemaine jour;
	private Time heureDebut;
	private Time heureFin;
	
	public Horaire(long horaire_id, JourSemaine jour, Time heureDebut, Time heureFin) {
		super();
		this.horaire_id = horaire_id;
		this.jour = jour;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	
	
	public long getHoraire_id() {
		return horaire_id;
	}
	public void setHoraire_id(long horaire_id) {
		this.horaire_id = horaire_id;
	}
	
	public JourSemaine getJour() {
		return jour;
	}
	public void setJour(JourSemaine jour) {
		this.jour = jour;
	}
	
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	
	public Time getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	
	

}
