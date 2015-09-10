package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.UnityDrink;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.UnityDrinkService;

@Controller
@RequestMapping("/admin/unityDrink")
public class UnityDrinkController {

	@Autowired
	private UnityDrinkService unityDrinkService;
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("admin/unityDrink/unitydrink");
		modelAndView.addObject("unityDrink", new UnityDrink());
		modelAndView.addObject("action", "Ajouter");
		
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/UnityDrink/UnityDrink");

			modelAndView.addObject("unityDrink", unityDrinkService.findById(id));
			modelAndView.addObject("action", "Editer");
			
			return modelAndView;
			
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/UnityDrink/list-UnityDrink");
		
		try {
			modelAndView.addObject("unityDrinks", unityDrinkService.findAll());
			
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		
		return modelAndView;

	}
	
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(UnityDrink unityDrink) throws ServiceException {
		try {
			if (unityDrink.getId() == null) {
				unityDrinkService.save(unityDrink);
				
			} else {
				unityDrinkService.update(unityDrink);
			}
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			
			return modelAndView;
			
		} catch (Exception e) {
			
			ModelAndView modelAndView = edit(unityDrink.getId());
			modelAndView.addObject("error", e.getMessage());
			
			return modelAndView;
		}
	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		ModelAndView modelAndView = null;
		
		try {
		    unityDrinkService.removeById(id);
			
			modelAndView = new ModelAndView("redirect:list.html");
			
			return modelAndView;
			
		} catch (Exception e) {
			modelAndView = edit(id);
			
			modelAndView.addObject("error", e.getMessage());
			
			return modelAndView;
		}
	}
}
