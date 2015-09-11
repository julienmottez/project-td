package fr.treeptik.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Rack;
import fr.treeptik.entity.TypeRack;

@Repository
public interface TypeRackDAO extends JpaRepository<TypeRack, Integer>

{
	
}
