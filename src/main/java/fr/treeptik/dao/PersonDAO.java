package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Person;

@Repository
public interface PersonDAO extends JpaRepository<Person, Integer> {

}
