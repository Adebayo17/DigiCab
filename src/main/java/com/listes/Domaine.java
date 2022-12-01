package com.listes;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;


@ManagedBean
public enum Domaine implements Serializable {
	Chirurgie("Chirurgie"),
	Pédiatrie("Pédiatrie"),
	Dentaire("Dentaire"),
	Ophtamologie("Ophtamologie"),
	Cardiologie("Cardiologie"),
	Dermatologie("Dermatologie"),
	Gynécologie("Gynécologie"),
	Orthopédie("Orthopédie"),
	Radiologie("Radiologie"),
	Généraliste("Généraliste"),
	Psychiatrie("Psychiatrie"),
	ORL("ORL");
	
	
	private final String getDomaineName;
	
	
	public String getGetDomaineName() {
		return getDomaineName;
	}


	Domaine(String domaineName){
		this.getDomaineName = domaineName;
	}
	
	Domaine(){
		this.getDomaineName = "";
	}
	
	
	
	

}
