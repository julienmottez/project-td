package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Drink;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DrinkService;

@Controller
@RequestMapping("/admin/drink")
public class DrinkController {

	@Autowired
	private DrinkService drinkService;
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("admin/drink/drink");
		modelAndView.addObject("drink", new Drink());
		modelAndView.addObject("action", "Ajouter");
		
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/drink/drink");

			modelAndView.addObject("drink", drinkService.findById(id));
			modelAndView.addObject("action", "Editer");
			
			return modelAndView;
			
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/drink/list-drink");
		
		try {
			modelAndView.addObject("drinks", drinkService.findAll());
			
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		
		return modelAndView;

	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Drink drink) throws ServiceException {
		try {
			if (drink.getId() == null) {
				drinkService.save(drink);
				
			} else {
				drinkService.update(drink);
			}
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			
			return modelAndView;
			
		} catch (Exception e) {
			
			ModelAndView modelAndView = edit(drink.getId());
			modelAndView.addObject("error", e.getMessage());
			
			return modelAndView;
		}
	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		ModelAndView modelAndView = null;
		
		try {
			drinkService.removeById(id);
			
			modelAndView = new ModelAndView("redirect:list.html");
			
			return modelAndView;
			
		} catch (Exception e) {
			modelAndView = edit(id);
			
			modelAndView.addObject("error", e.getMessage());
			
			return modelAndView;
		}
	}
}
