package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Drink;



@Repository
public interface DistributionPointDAO extends JpaRepository<DistributionPoint,Integer>{


	DistributionPoint findById(int id);
	List<DistributionPoint> findByDrinks(List<Drink> drinks);
	
	
	

}