package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "refrigerator")
public class Refrigerator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "cold_level")
	private Temperature coldLevel;

	@Column(name = "brand")
	private String brand;

	@ManyToOne
	@JoinColumn(name="distributor_id")
	private Distributor distributor;

	public Refrigerator() {
	}
	
	public Refrigerator(Integer id, Temperature coldLevel, String brand, Distributor distributor) {
		this.id = id;
		this.coldLevel = coldLevel;
		this.brand = brand;
		this.distributor = distributor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Temperature getColdLevel() {
		return coldLevel;
	}

	public void setColdLevel(Temperature coldLevel) {
		this.coldLevel = coldLevel;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}
	
	
	
	
}
