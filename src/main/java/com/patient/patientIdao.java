package com.patient;

import java.util.List;

import com.dao.IDAO;

public interface patientIdao extends IDAO<Patient> {
	public List<Patient> getAll(String des);

}

