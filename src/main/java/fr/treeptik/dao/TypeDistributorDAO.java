package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.TypeDistributor;

@Repository
public interface TypeDistributorDAO extends JpaRepository<TypeDistributor, Integer> {

}
