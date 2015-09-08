package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Table(name="distributor")
public class Distributor implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	@Embedded
	private Address address;
	
	@OneToMany(mappedBy = "distributor")
	private List<Refrigerator> refrigerators;
	
	
	@OneToOne
	private Sector sector;
	

	@ManyToOne
	private Technician technician;
	
	
	 
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
	public List<Refrigerator> getRefrigerators() {
		return refrigerators;
	}
	public void setRefrigerators(List<Refrigerator> refrigerators) {
		this.refrigerators = refrigerators;
	}
	
	
}
