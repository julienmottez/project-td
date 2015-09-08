package fr.treeptik.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class DistributionPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String adress;
	
	public DistributionPoint() {
		
	}

	public DistributionPoint(Integer id, String adress) {
		super();
		this.id = id;
		this.adress = adress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	
	

}
