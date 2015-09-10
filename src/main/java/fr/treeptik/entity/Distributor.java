package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "distributor")
public class Distributor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;

	@Transient
	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "distributor")
	private List<Refrigerator> refrigerators;

	@ManyToOne
	private Sector sector;

	@ManyToOne
	private Technician technician;

	@ManyToOne
	@JoinColumn(name = "type_distributor")
	private TypeDistributor typeDistributor;

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
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

	public List<Refrigerator> getRefrigerators() {
		return refrigerators;
	}

	public void setRefrigerators(List<Refrigerator> refrigerators) {
		this.refrigerators = refrigerators;
	}

}
