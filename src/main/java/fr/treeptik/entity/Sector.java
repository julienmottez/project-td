package fr.treeptik.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="sector")
public class Sector implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private SectorManager managerSector;

	@OneToOne
	private Area area;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sector")
	private List<Distributor>distributors;

	@OneToMany(fetch = FetchType.LAZY,mappedBy="sectordp")
	private List<DistributionPoint>distributionPoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SectorManager getManagerSector() {
		return managerSector;
	}

	public void setManagerSector(SectorManager managerSector) {
		this.managerSector = managerSector;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Distributor> getDistributors() {
		return distributors;
	}

	public void setDistributors(List<Distributor> distributors) {
		this.distributors = distributors;
	}

	public List<DistributionPoint> getDistributionPoints() {
		return distributionPoints;
	}

	public void setDistributionPoints(List<DistributionPoint> distributionPoints) {
		this.distributionPoints = distributionPoints;
	}



	



}
