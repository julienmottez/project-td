package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.entity.Person;

public interface PersonDAO extends JpaRepository<Person, Integer> {

}
