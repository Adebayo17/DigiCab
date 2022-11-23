package com.medecin;

import java.util.Date;

public class Médecin {
	
	private String carteIdentite;
	private String email;
	private String password;
	private String nomMedecin;
	private String prenomMedecin;
	private String specialite;
	private Date dateNaissance;
	private String telephone;
	
	//
	public Médecin(String carteIdentite, String email, String password, String nomMedecin, String prenomMedecin,
			String specialite, Date dateNaissance, String telephone) {
		super();
		this.carteIdentite = carteIdentite;
		this.email = email;
		this.password = password;
		this.nomMedecin = nomMedecin;
		this.prenomMedecin = prenomMedecin;
		this.specialite = specialite;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
	}

	public Médecin(String carteIdentite) {
		super();
		this.carteIdentite = carteIdentite;
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
	
	public String getNomMedecin() {
		return nomMedecin;
	}

	public void setNomMedecin(String nomMedecin) {
		this.nomMedecin = nomMedecin;
	}

	public String getPrenomMedecin() {
		return prenomMedecin;
	}

	public void setPrenomMedecin(String prenomMedecin) {
		this.prenomMedecin = prenomMedecin;
	}

	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
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
