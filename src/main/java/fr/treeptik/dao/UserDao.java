package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.treeptik.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	
}
