package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@DiscriminatorValue("Technician")
public class Technician extends Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private Sector sector;
	
	@OneToMany(mappedBy = "technician", fetch = FetchType.LAZY)
	private List<Distributor> distributors;
	
	
	public List<Distributor> getDistributors() {
		return distributors;
	}
	public void setDistributors(List<Distributor> distributors) {
		this.distributors = distributors;
	}
	public Sector getSector() {
		return sector;
	}
	public void setSector(Sector sector) {
		this.sector = sector;
	}
}
