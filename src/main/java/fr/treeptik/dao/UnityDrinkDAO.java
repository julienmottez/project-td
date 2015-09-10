package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Drink;
import fr.treeptik.entity.UnityDrink;


@Repository
public interface UnityDrinkDAO extends JpaRepository<UnityDrink, Integer>

{ 
	UnityDrink findById(int id);
	   

}