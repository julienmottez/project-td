package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Distributeur;

public interface DistributorDao extends JpaRepository<Distributeur, Integer> {

}
