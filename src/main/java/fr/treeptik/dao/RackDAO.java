package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Rack;

public interface RackDAO extends JpaRepository<Rack, Integer> {

}
