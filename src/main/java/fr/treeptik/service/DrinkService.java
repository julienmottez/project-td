package fr.treeptik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.DrinkDao;
import fr.treeptik.entity.Drink;

@Service
public class DrinkService {

	@Autowired
    private DrinkDao drinkDao;

    public DrinkService() {
	}
    
    public DrinkService(DrinkDao drinkDao) {
		this.drinkDao = drinkDao;
	}

	public Drink findById(int id) {
        return drinkDao.findById(id);
    }

	public List<Drink> findAll() {
		return drinkDao.findAll();
	}

	public Drink save(Drink drink) {
		return drinkDao.save(drink);
	}

	public Drink update(Drink drink) {
		return drinkDao.save(drink);
	}

	public void removeById(Integer id) {
		drinkDao.delete(id);
	}
}
