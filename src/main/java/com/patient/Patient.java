package com.patient;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.dao.SessionUtils;

@ManagedBean
@SessionScoped
public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String carteIdentite;
	private String email;
	private String password;
	private String nomPatient;
	private String prenomPatient;
	private Date dateNaissance;
	private String telephone;
	
	
	public Patient(String carteIdentite, String email, String password, String nomPatient, String prenomPatient,
			Date dateNaissance, String telephone) {
		super();
		this.carteIdentite = carteIdentite;
		this.email = email;
		this.password = password;
		this.nomPatient = nomPatient;
		this.prenomPatient = prenomPatient;
		this.dateNaissance = dateNaissance;
		this.telephone = telephone;
	}
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
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
	
	
	public String getNomPatient() {
		return nomPatient;
	}
	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}
	
	
	public String getPrenomPatient() {
		return prenomPatient;
	}
	public void setPrenomPatient(String prenomPatient) {
		this.prenomPatient = prenomPatient;
	}
	
	
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	//validate login
	public String validatePatientPassword() {
		
		boolean valid = PatientDaoImpl.validate(email, password);
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			return "admin";
		} 
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect email and Passowrd",
							"Please enter correct email and Password"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

}
