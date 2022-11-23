package com.cabinet_horaire;


import java.util.List;

import com.dao.IDAO;

public interface cabinetHoraireIdao extends IDAO<CabinetHoraire> {
    public List<CabinetHoraire> getAll(String des);
}
