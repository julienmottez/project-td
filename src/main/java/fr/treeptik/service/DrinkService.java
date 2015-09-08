package fr.treeptik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.DrinkDao;
import fr.treeptik.entity.Drink;

@Service
public class DrinkService {

    private DrinkDao drinkDao;

    @Autowired
    public DrinkService(DrinkDao drinkDao) {
		this.drinkDao = drinkDao;
	}

	public Drink getById(int id) {
        return drinkDao.findById(id);
    }
}
