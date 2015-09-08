package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Distributor;

public interface DistributorDao extends JpaRepository<Distributor, Integer> {

}
