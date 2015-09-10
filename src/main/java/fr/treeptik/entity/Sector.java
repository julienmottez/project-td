package fr.treeptik.entity;


import java.util.List;

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
public class Sector {
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


	public SectorManager getManagerSector() {
		return managerSector;
	}

	public Integer getId() {
		return id;
	}
	

	public void setManagerSector(SectorManager managerSector) {
		this.managerSector = managerSector;
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
