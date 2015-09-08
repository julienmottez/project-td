package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Distributor;

@Repository
public interface DistributorDao extends JpaRepository<Distributor, Integer> {

}
