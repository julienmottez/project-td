package fr.treeptik.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Refrigerator;

@Repository
public interface RefrigeratorDao extends JpaRepository<Refrigerator, Integer> {

//	@Query("SELECT u FROM User u LEFT JOIN FETCH u.projects")
//	List<User> findAllPlayerWithProjects();
	
}
