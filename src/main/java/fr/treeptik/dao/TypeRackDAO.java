package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.TypeRack;

@Repository
public interface TypeRackDAO extends JpaRepository<TypeRack, Integer>

{

}
