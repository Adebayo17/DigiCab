package com.medecin;

import java.io.Serializable;
import java.time.LocalDate;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.cabinetMedical.CabinetMedical;
import com.cabinetMedical.CabinetMedicalDaoImpl;
import com.listes.Domaine;
import com.listes.Ville;
import com.patient.SessionUtils;



@ManagedBean
@SessionScoped
public class Médecin implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String carteIdentite;
	private String email;
	private String password;
	private String nomMedecin;
	private String prenomMedecin;
	private String specialite;
	private LocalDate dateNaissance;
	private String telephone;
	private String password2;
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Médecin() {
		super();
	}

	//
	public Médecin(String carteIdentite, String email, String password, String nomMedecin, String prenomMedecin,
			String specialite, LocalDate dateNaissance, String telephone) {
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
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	public String validateMedecinPassword() {
		
		boolean valid = MedecinDaoImpl.validate(email, password);
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			MedecinDaoImpl medlog = new MedecinDaoImpl();
			Médecin med = new Médecin();
			med = medlog.getMedecinlogged(email);
			carteIdentite = med.getCarteIdentite();
			nomMedecin = med.getNomMedecin();
			prenomMedecin = med.getPrenomMedecin();
			specialite = med.getSpecialite();
			dateNaissance = med.getDateNaissance();
			telephone = med.getTelephone();
			
			
			return "admin-medecin";
		} 
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect email and Passowrd",
							"Please enter correct email and Password"));
			return "login-cabinet";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login-cabinet";
	}
	
	
	public String invalidPassword() {
		if(password.equals(password2)) {
			return null;
		}
		else
			return "Mot de passe incompatible";
	}
	
	public void addMedecin() {
		MedecinDaoImpl inscription = new MedecinDaoImpl();
		if(invalidPassword() == null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			inscription.add(new Médecin(carteIdentite, email, password, nomMedecin, prenomMedecin, specialite, 
					dateNaissance, telephone));
			
		}
		else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Mot de passe incompatible",
							"Confirmer le mot de passe"));
		}
		
	}
	
	public CabinetMedical setCabMed() {
		CabinetMedicalDaoImpl cabMedlog = new CabinetMedicalDaoImpl();
		CabinetMedical cabMed = new CabinetMedical();
		cabMed = cabMedlog.getMedlogged(carteIdentite);
		
		return cabMed;
	}
	
	public long getCodeSirenMed() {
		long codeSiren;
		codeSiren = setCabMed().getCodeSiren();
		return codeSiren;
	}
	
	
	public String getNomCabinetMed() {
		String nomCabinet;
		nomCabinet = setCabMed().getNomCabinet();
		return nomCabinet;
	}
	
	
	public Ville getNomVilleMed() {
		Ville nomVille;
		nomVille = setCabMed().getNomVille();
		return nomVille;
	}
	
	
	public String getAdresseMed() {
		String adresse;
		adresse = setCabMed().getAdresse();
		return adresse;
	}
	
	public String getTelephoneMed() {
		String telephone;
		telephone = setCabMed().getTelephone();
		return telephone;
	}
	
	public Domaine getDomaineMed() {
		Domaine domaine;
		domaine = setCabMed().getDomaine();
		return domaine;
	}
	

}
