package fr.treeptik.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Person;
import fr.treeptik.entity.SectorManager;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {

	@Query("SELECT p FROM Person p WHERE TYPE(p) = SectorManager")
	List<SectorManager> findAllSectorManager();
	
	
}
