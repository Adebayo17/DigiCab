package com.views;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.util.LangUtils;

import com.cabinetMedical.CabinetMedical;
import com.listes.Domaine;
import com.listes.Ville;

@ManagedBean
@ApplicationScoped
@ViewScoped
public class Data implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CabinetMedical cabmed;
	
	private List<CabinetMedical> cabmeds;
	
	private List<CabinetMedical> filtercabmeds;
	
	private List<FilterMeta> filterBy;
	
	 private boolean globalFilterOnly;
	
	
	public void init() {
		cabmeds = cabmed.getAll2();
		
		filterBy = new ArrayList<>();
	}
	
	public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (LangUtils.isBlank(filterText)) {
            return true;
        }   
        

        CabinetMedical cabmed = (CabinetMedical) value;
        return cabmed.getNomCabinet().toLowerCase().contains(filterText)
                || cabmed.getDomaine().name().toLowerCase().contains(filterText)
                || cabmed.getNomVille().name().toLowerCase().contains(filterText);
    }

    public void toggleGlobalFilter() {
        setGlobalFilterOnly(!isGlobalFilterOnly());
    }
    
 
    
    public boolean isGlobalFilterOnly() {
        return globalFilterOnly;
    }
	
	public CabinetMedical getCabmed() {
		return cabmed;
	}

	public void setCabmed(CabinetMedical cabmed) {
		this.cabmed = cabmed;
	}

	public List<CabinetMedical> getCabmeds() {
		return cabmeds;
	}

	public void setCabmeds(List<CabinetMedical> cabmeds) {
		this.cabmeds = cabmeds;
	}

	public List<CabinetMedical> getFiltercabmeds() {
		return filtercabmeds;
	}

	public void setFiltercabmeds(List<CabinetMedical> filtercabmeds) {
		this.filtercabmeds = filtercabmeds;
	}

	public List<FilterMeta> getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(List<FilterMeta> filterBy) {
		this.filterBy = filterBy;
	}

	public void setGlobalFilterOnly(boolean globalFilterOnly) {
		this.globalFilterOnly = globalFilterOnly;
	}

	public Ville[] getVilles() {
        return Ville.values();
    }
	
	
	public Domaine[] getDomaines() {
        return Domaine.values();
    }

	
	
	
	
	
	

}
