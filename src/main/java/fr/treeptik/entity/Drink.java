package fr.treeptik.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String name;

	@Column(name = "cold")
	@Embedded
	private TemperatureRange coldStorage;
	
	@ManyToMany(mappedBy="drinks")
	private List<DistributionPoint> distributionPoints;

	 
	

	public Drink() {
	}


	public Drink(int id, TemperatureRange coldStorage) {
	
		this.id = id;
		
		this.coldStorage = coldStorage;
	
	
	}


	public Drink(int id, String name, TemperatureRange coldStorage, List<DistributionPoint> distributionPoints) {
		super();
		this.id = id;
		this.name = name;
		this.coldStorage = coldStorage;
		this.distributionPoints = distributionPoints;
	}


	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TemperatureRange getColdStorage() {
		return coldStorage;
	}

	public void setColdStorage(TemperatureRange coldStorage) {
		this.coldStorage = coldStorage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}






	public List<DistributionPoint> getDistributionPoints() {
		return distributionPoints;
	}


	public void setDistributionPoints(List<DistributionPoint> distributionPoints) {
		this.distributionPoints = distributionPoints;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coldStorage == null) ? 0 : coldStorage.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Drink other = (Drink) obj;
		if (coldStorage == null) {
			if (other.coldStorage != null)
				return false;
		} else if (!coldStorage.equals(other.coldStorage))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
