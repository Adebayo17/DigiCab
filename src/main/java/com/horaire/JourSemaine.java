package com.horaire;

public enum JourSemaine {
	Lundi("Lundi"),
	Mardi("Mardi"),
	Mercredi("Mercredi"),
	Jeudi("Jeudi"),
	Vendredi("Vendredi"),
	Samedi("Samedi"),
	Dimanche("Dimanche");
	
	private final String getJourSemaine;
	
	
	public String getGetJourSemaine() {
		return getJourSemaine;
	}


	JourSemaine(String jour){
		getJourSemaine = jour;
	}

}
