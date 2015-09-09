package fr.treeptik.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drink")
public class Drink implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cold")
    @Embedded
    private TemperatureRange coldStorage;
    
    @ManyToOne
    @JoinColumn(name="distributionPoint_id")
    private DistributionPoint distributionPoint;

    public Drink() {
	}
    
	public Drink(int id, TemperatureRange coldStorage) {
		this.id = id;
		this.coldStorage = coldStorage;
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

	public DistributionPoint getDistributionPoint() {
		return distributionPoint;
	}

	public void setDistributionPoint(DistributionPoint distributionPoint) {
		this.distributionPoint = distributionPoint;
	}
}
