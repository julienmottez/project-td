package fr.treeptik.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.treeptik.dao.DrinkDao;
import fr.treeptik.entity.Drink;

public class DrinkService {

    @Autowired
    private DrinkDao drinkDao;

    public Drink getById(int id) {
        return drinkDao.findById(id);
    }
}
