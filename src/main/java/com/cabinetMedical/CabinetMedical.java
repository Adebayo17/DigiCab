package com.cabinetMedical;


import javax.faces.bean.ManagedBean;

import com.assistant.Assistant;
import com.listes.Domaine;
import com.listes.Ville;
import com.medecin.Médecin;

@ManagedBean
public class CabinetMedical {
	
	private long codeSiren;
	private String nomCabinet;
	private Ville nomVille;
	private String adresse;
	private Médecin medecin;
	private Assistant assistant;
	private String telephone;
	private Domaine domaine;
	
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
	
	
	

	


}
