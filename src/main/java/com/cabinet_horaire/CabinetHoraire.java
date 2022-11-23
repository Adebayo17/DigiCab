package com.cabinet_horaire;

import com.cabinetMedical.CabinetMedical;
import com.horaire.Horaire;

public class CabinetHoraire {
	
	private CabinetMedical cabinet;
	private Horaire horaire;
	public CabinetHoraire(CabinetMedical cabinet, Horaire horaire) {
		super();
		this.cabinet = cabinet;
		this.horaire = horaire;
	}
	public CabinetMedical getCabinet() {
		return cabinet;
	}
	public void setCabinet(CabinetMedical cabinet) {
		this.cabinet = cabinet;
	}
	public Horaire getHoraire() {
		return horaire;
	}
	public void setHoraire(Horaire horaire) {
		this.horaire = horaire;
	}
	
	

}
