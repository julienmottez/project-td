package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Drink;
import fr.treeptik.service.DrinkService;

@Controller
public class DrinkController {

	@Autowired
	private DrinkService DrinkService;
	
	@RequestMapping("/admin/drink/{drinkId}")
	public ModelAndView showDrink(@PathVariable("ownerId") int drinkId) {
		Drink drink = null;
		
		try {
			drink = DrinkService.getById(drinkId);	
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}
		
		ModelAndView mav = new ModelAndView("admin/drink/drinkDetails");
		mav.addObject(drink);
		
		return mav;
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public final class ResourceNotFoundException extends RuntimeException {
	}
}
