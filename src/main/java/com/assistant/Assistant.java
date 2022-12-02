package com.assistant;

import java.io.Serializable;
import java.time.LocalDate;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.cabinetMedical.CabinetMedical;
import com.listes.Domaine;
import com.listes.Ville;
import com.patient.SessionUtils;


@ManagedBean
@SessionScoped
@ApplicationScoped
public class Assistant implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String carteIdentite;
	private String email;
	private String password;
	private String nomAssistant;
	private String prenomAssistant;
	private LocalDate dateNaissance;
	private String telephone;
	private String password2;
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Assistant() {
		super();
	}

	//
	public Assistant(String carteIdentite, String email, String password, String nomAssistant, String prenomAssistant,
			LocalDate dateNaissance, String telephone) {
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
	
	public String validateAssistantPassword() {
		
		boolean valid = AssistantDaoImpl.validate(email, password);
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			AssistantDaoImpl assilog = new AssistantDaoImpl();
			Assistant assi = new Assistant();
			assi = assilog.getAssistantlogged(email);
			carteIdentite = assi.getCarteIdentite();
			nomAssistant = assi.getNomAssistant();
			prenomAssistant = assi.getPrenomAssistant();
			dateNaissance = assi.getDateNaissance();
			telephone = assi.getTelephone();
			
			
			return "admin-assistant";
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
	
	public void addAssistant() {
		AssistantDaoImpl inscription = new AssistantDaoImpl();
		if(invalidPassword() == null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			inscription.add(new Assistant(carteIdentite, email, password, nomAssistant, prenomAssistant,
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
	
	public CabinetMedical setCabAss() {
		AssistantDaoImpl cabAsslog = new AssistantDaoImpl();
		CabinetMedical cabAss = new CabinetMedical();
		cabAss = cabAsslog.getAsslogged(email);
		System.out.println(cabAss.getNomCabinet());
		return cabAss;
	}
	
	public long getCodeSirenAss() {
		long codeSiren = setCabAss().getCodeSiren();
		return codeSiren;
	}
	
	
	public String getNomCabinetAss() {
		String nomCabinet = setCabAss().getNomCabinet();
		return nomCabinet;
	}
	
	
	public Ville getNomVilleAss() {
		Ville nomVille = setCabAss().getNomVille();
		return nomVille;
	}
	
	
	public String getAdresseAss() {
		String adresse = setCabAss().getAdresse();
		return adresse;
	}
	
	public String getTelephoneAss() {
		String telephone = setCabAss().getTelephone();
		return telephone;
	}
	
	public Domaine getDomaineAss() {
		Domaine domaine = setCabAss().getDomaine();
		return domaine;
	}

}
