package fr.treeptik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.UnityDrinkDAO;
import fr.treeptik.entity.UnityDrink;

@Service
public class UnityDrinkService {

	@Autowired
    private UnityDrinkDAO unityDrinkDAO;

    public UnityDrinkService() {
	}
    
    public UnityDrinkService(UnityDrinkDAO unityDrinkDAO) {
		this.unityDrinkDAO = unityDrinkDAO;
	}

	public UnityDrink findById(int id) {
        return unityDrinkDAO.findById(id);
    }

	public List<UnityDrink> findAll() {
		return unityDrinkDAO.findAll();
	}

	public UnityDrink save(UnityDrink drink) {
		return unityDrinkDAO.save(drink);
	}

	public UnityDrink update(UnityDrink drink) {
		return unityDrinkDAO.save(drink);
	}

	public void removeById(Integer id) {
		unityDrinkDAO.delete(id);
	}
}
