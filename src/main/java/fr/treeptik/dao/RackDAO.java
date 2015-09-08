package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Rack;

@Repository
public interface RackDAO extends JpaRepository<Rack, Integer> {

}
