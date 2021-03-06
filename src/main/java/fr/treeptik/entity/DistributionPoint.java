package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "distribution_point")

public class DistributionPoint implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String name;
	
	@Embedded
	private Address address;
	

	@ManyToOne(cascade=CascadeType.PERSIST)
	private ProductionManager productionManager;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Sector sectordp;
	

 @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
		private List<Drink> drinks;
	
	
	
	
	public DistributionPoint() {
		
	}




	

	public DistributionPoint(Integer id, Address address, ProductionManager productionManager, Sector sectordp,
			List<Drink> drinks) {
		super();
		this.id = id;
		this.address = address;
		this.productionManager = productionManager;
		this.sectordp = sectordp;
		this.drinks = drinks;
	}






	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getName() {
		return name;
	}






	public void setName(String name) {
		this.name = name;
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






	public List<Drink> getDrinks() {
		return drinks;
	}






	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	
	
	



	
	

}
