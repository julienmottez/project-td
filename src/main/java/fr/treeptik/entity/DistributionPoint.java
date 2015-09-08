package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "distribution_point")
public class DistributionPoint implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Embedded
	private Address address;
	

	@ManyToOne
	private ProductionManager productionManager;
	
	@ManyToOne
	private Sector sectordp;
	
	
	public DistributionPoint() {
		
	}




	public DistributionPoint(Integer id, Address address, ProductionManager productionManager, Sector sectordp) {
		super();
		this.id = id;
		this.address = address;
		this.productionManager = productionManager;
		this.sectordp = sectordp;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}




	public ProductionManager getProductionManager() {
		return productionManager;
	}




	public void setProductionManager(ProductionManager productionManager) {
		this.productionManager = productionManager;
	}




	public Sector getSectordp() {
		return sectordp;
	}




	public void setSectordp(Sector sectordp) {
		this.sectordp = sectordp;
	}
	
	



	
	

}
