package fr.treeptik.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("ProductionManager")
public class ProductionManager extends Person {
	
	@OneToMany(mappedBy = "productionManager")
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
