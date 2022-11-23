package com.assistant;

import java.util.Date;

public class Assistant {
	
	private String carteIdentite;
	private String email;
	private String password;
	private String nomAssistant;
	private String prenomAssistant;
	private Date dateNaissance;
	private String telephone;
	
	//
	public Assistant(String carteIdentite, String email, String password, String nomAssistant, String prenomAssistant,
			Date dateNaissance, String telephone) {
		super();
		this.carteIdentite = carteIdentite;
		this.email = email;
		this.password = password;
		this.nomAssistant = nomAssistant;
		this.prenomAssistant = prenomAssistant;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
	}
	
	
	public String getCarteIdentite() {
		return carteIdentite;
	}
	
	

	public void setCarteIdentite(String carteIdentite) {
		this.carteIdentite = carteIdentite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNomAssistant() {
		return nomAssistant;
	}


	public void setNomAssistant(String nomAssistant) {
		this.nomAssistant = nomAssistant;
	}


	public String getPrenomAssistant() {
		return prenomAssistant;
	}


	public void setPrenomAssistant(String prenomAssistant) {
		this.prenomAssistant = prenomAssistant;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	
	

}
