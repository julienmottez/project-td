package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.Area;


@Repository
public interface AreaDAO extends JpaRepository<Area,Integer>{

}
