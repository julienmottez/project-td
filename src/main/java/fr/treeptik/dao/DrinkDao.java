package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Drink;

@Repository
public interface DrinkDao extends JpaRepository<Drink, Integer> {

    Drink findById(int id);
}
