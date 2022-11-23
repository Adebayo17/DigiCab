package com.cabinetMedical;

import java.util.List;

import com.dao.IDAO;

public interface cabinetMedicalIdao extends IDAO<CabinetMedical> {
	public List<CabinetMedical> getAll(String des);

}
