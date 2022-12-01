package com.listes;

import javax.faces.bean.ManagedBean;

@ManagedBean
public enum Ville {
	Agadir("Agadir"),
    Ait_melloul("Ait melloul"),
    Arfoud("Arfoud"),
    Azrou("Azrou"),
    Bénguérir("Bénguérir"),
    Benslimane("Benslimane"),
    Berkane("Berkane"),
    Berrechid("Berrechid"),
    Bouskoura("Bouskoura"),
    Bouznika("Bouznika"),
    Casablanca("Casablanca"),
    Dakhla("Dakhla"),
    Dar_Bouazza("Dar Bouazza"),
    Deroua("Deroua"),
    El_Jadida("El Jadida"),
    Errachidia("Errachidia"),
    Essaouira("Essaouira"),
    Fkih_Ben_Saleh("Fkih Ben Saleh"),
    Fés("Fés"),
    Had_Soualem("Had Soualem"),
    Inzegane("Inzegane"),
    Kalaat_Sraghna("Kalaat Sraghna"),
    Khemisset("Khemisset"),
    Khouribga("Khouribga"),
    Khénifra("Khénifra"),
    Kénitra("Kénitra"),
    Larache("Larache"),
    Laayoune("Laayoune"),
    Marrakech("Marrakech"),
    Mechra_Bel_Ksiri("Mechra Bel Ksiri"),
    Meknés("Meknés"),
    Mohammedia("Mohammedia"),
    Médiouna("Médiouna"),
    Nador("Nador"),
    Ouarzazate("Ouarzazate"),
    Oujda("Oujda"),
    Rabat("Rabat"),
    Safi("Safi"),
    Saidia("Saidia"),
    Salé("Salé"),
    Settat("Settat"),
    Sidi_Bennour("Sidi Bennour"),
    Sidi_Kacem("Sidi Kacem"),
    Skhirat("Skhirat"),
    Séfrou("Séfrou"),
    Tanger("Tanger"),
    Taroudant("Taroudant"),
    Tata("Tata"),
    Taza("Taza"),
    Temara("Temara"),
    Tétouan("Tétouan")
	;
	
	private final String getVilleName;
	
	
	
	public String getGetVilleName() {
		return getVilleName;
	}



	Ville(String villeName){
		getVilleName = villeName;
	}
	
	Ville(){
		getVilleName = "";
	}

	

	
	

}
