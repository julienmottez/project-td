package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Technician;

@Repository
public interface TechnicianDAO extends JpaRepository<Technician, Integer> {
	
	Technician findByName(String login);

}
