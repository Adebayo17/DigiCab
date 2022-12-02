package com.cabinetMedical;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.assistant.Assistant;
import com.listes.Domaine;
import com.listes.Ville;
import com.medecin.Médecin;

@ManagedBean
@ApplicationScoped
public class CabinetMedical {
	
	private long codeSiren;
	private String nomCabinet;
	private Ville nomVille;
	private String adresse;
	private Médecin medecin;
	private Assistant assistant;
	private String telephone;
	private Domaine domaine;
	
	
	public CabinetMedical() {
		super();
	}
	

	public CabinetMedical(long codeSiren, String nomCabinet, Ville nomVille, String adresse, String telephone,
			Domaine domaine) {
		super();
		this.codeSiren = codeSiren;
		this.nomCabinet = nomCabinet;
		this.nomVille = nomVille;
		this.adresse = adresse;
		this.telephone = telephone;
		this.domaine = domaine;
	}
	
	

	public CabinetMedical(long codeSiren, String nomCabinet, Ville nomVille, String adresse, Médecin medecin,
			Assistant assistant, String telephone, Domaine domaine) {
		super();
		this.codeSiren = codeSiren;
		this.nomCabinet = nomCabinet;
		this.nomVille = nomVille;
		this.adresse = adresse;
		this.medecin = medecin;
		this.assistant = assistant;
		this.telephone = telephone;
		this.domaine = domaine;
	}
	
	public CabinetMedical(long codeSiren, String nomCabinet) {
		super();
		this.codeSiren = codeSiren;
		this.nomCabinet = nomCabinet;
		
	}
	
	public long getCodeSiren() {
		return codeSiren;
	}
	public void setCodeSiren(long codeSiren) {
		this.codeSiren = codeSiren;
	}
	
	
	public String getNomCabinet() {
		return nomCabinet;
	}
	public void setNomCabinet(String nomCabinet) {
		this.nomCabinet = nomCabinet;
	}
	
	
	public Ville getNomVille() {
		return nomVille;
	}
	public void setNomVille(Ville nomVille) {
		this.nomVille = nomVille;
	}
	
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	public Médecin getMedecin() {
		return medecin;
	}
	public void setMedecin(Médecin medecin) {
		this.medecin = medecin;
	}
	
	
	public Assistant getAssistant() {
		return assistant;
	}
	public void setAssistant(Assistant assistant) {
		this.assistant = assistant;
	}
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	public Domaine getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}
	
	
	public List<CabinetMedical> getAll() {
		List<CabinetMedical> list = new ArrayList<CabinetMedical>();
		CabinetMedicalDaoImpl list_cab = new CabinetMedicalDaoImpl();
		
		
		if((domaine == null  && nomVille == null)) {
			list = list_cab.getAll();
			
		} else {
			list = list_cab.getAll(nomCabinet, domaine, nomVille);
				
		}
		
		return list;
	}
	
	public List<CabinetMedical> getAll2() {
		List<CabinetMedical> list = new ArrayList<CabinetMedical>();
		CabinetMedicalDaoImpl list_cab = new CabinetMedicalDaoImpl();
		list = list_cab.getAll();
		return list;
	}

	public String invalidPassword() {
		if(medecin.invalidPassword() == null && assistant.invalidPassword() == null) {
			return null;
		}
		else
			return "Mot de passe incompatible";
	}
	
	public String Result() {
		if(getAll().isEmpty()) {
			return "Aucun résultat ne correspond à votre recherche";
		} else {
			return "Nous avons trouvé " + getAll().size() + " résultats à votre recherche";
		}
	}
	
	public String addCabinet() {
		CabinetMedicalDaoImpl inscription = new CabinetMedicalDaoImpl();
		if(invalidPassword() == null) {
			inscription.add(new CabinetMedical(codeSiren, nomCabinet, nomVille,adresse, 
                		new Médecin(medecin.getCarteIdentite(), medecin.getEmail(), medecin.getPassword(), medecin.getNomMedecin(), medecin.getPrenomMedecin(), medecin.getSpecialite(), medecin.getDateNaissance(), medecin.getTelephone()),
            			new Assistant(assistant.getCarteIdentite(), assistant.getEmail(), assistant.getPassword(), assistant.getNomAssistant(), assistant.getPrenomAssistant(), assistant.getDateNaissance(), assistant.getTelephone()), 
            			telephone, domaine));
			
			return "login-cabinet";
		} else {
			return "inscription-cabinet";
		}
	}
	


}
