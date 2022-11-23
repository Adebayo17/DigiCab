package com.horaire;


import java.util.List;

import com.dao.IDAO;

public interface horaireIdao extends IDAO<Horaire> {
    public List<Horaire> getAll(String des);
}
