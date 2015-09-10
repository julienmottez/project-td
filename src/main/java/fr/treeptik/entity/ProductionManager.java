package fr.treeptik.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@DiscriminatorValue("ProductionManager")
@DiscriminatorOptions(force = true) 
public class ProductionManager extends Person {
	
	@OneToMany(mappedBy = "productionManager",cascade=CascadeType.ALL)
	private List<DistributionPoint> distributionPoints;

	public ProductionManager() {
		super();
		
	}

	public ProductionManager(List<DistributionPoint> distributionPoints) {
		super();
		this.distributionPoints = distributionPoints;
	}

	public List<DistributionPoint> getDistributionPoints() {
		return distributionPoints;
	}

	public void setDistributionPoints(List<DistributionPoint> distributionPoints) {
		this.distributionPoints = distributionPoints;
	}
	
	
	
	

}
