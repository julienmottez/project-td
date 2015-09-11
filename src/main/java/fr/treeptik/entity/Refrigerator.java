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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((coldLevel == null) ? 0 : coldLevel.hashCode());
		result = prime * result + ((distributor == null) ? 0 : distributor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Refrigerator other = (Refrigerator) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (coldLevel == null) {
			if (other.coldLevel != null)
				return false;
		} else if (!coldLevel.equals(other.coldLevel))
			return false;
		if (distributor == null) {
			if (other.distributor != null)
				return false;
		} else if (!distributor.equals(other.distributor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Refrigerator [id=" + id + ", coldLevel=" + coldLevel + ", brand=" + brand + ", distributor="
				+ distributor + "]";
	}
	
	
	
	
}
