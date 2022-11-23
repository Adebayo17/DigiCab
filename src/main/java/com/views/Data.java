package com.views;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.listes.Domaine;

@ManagedBean
@ApplicationScoped
public class Data {
	
	
	public SelectItem[] getDomaines() {
		SelectItem[] items = new SelectItem[Domaine.values().length];
	    int i = 0;
	    for(Domaine g: Domaine.values()) {
	      items[i++] = new SelectItem(g, g.getGetDomaineName());
	    }
	    return items;
    }
	
	
	
	
	

}
