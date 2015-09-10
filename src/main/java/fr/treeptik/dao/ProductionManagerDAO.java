package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.ProductionManager;



@Repository
public interface ProductionManagerDAO extends JpaRepository<ProductionManager, Integer> {
	
	ProductionManager findById(Integer id);
	ProductionManager findBylastName(String name);

}