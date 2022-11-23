package com.assistant;

import java.util.List;

import com.dao.IDAO;

public interface assistantIdao extends IDAO<Assistant> {
	public List<Assistant> getAll(String des);

}

