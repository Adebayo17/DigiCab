package com.medecin;

import java.util.List;

import com.dao.IDAO;

public interface medecinIdao extends IDAO<Médecin> {
	public List<Médecin> getAll(String des);

}

