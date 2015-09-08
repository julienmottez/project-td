package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Refrigerator;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.RefrigeratorService;

@Controller
@RequestMapping(value = "/refrigerator")
public class RefrigeratorController {

	@Autowired
	private RefrigeratorService refrigeratorService;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("refrigerator");
		modelAndView.addObject("refrigerator", new Refrigerator());
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("refrigerator");
			Refrigerator refrigerator = refrigeratorService.findById(id);

			modelAndView.addObject("refrigeratorMaker", refrigerator);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-refrigerator");
		try {
			modelAndView.addObject("refrigerators", refrigeratorService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Refrigerator refrigerator) throws ServiceException {
		try {

			Integer coldLevel = refrigerator.getColdLevel();
			String brand = refrigerator.getBrand();

			if (coldLevel == null || coldLevel == 0) {
				throw new FormException("Le niveau de froid est obligatoires.");
			}
			if (brand == null || brand == "") {
				throw new FormException("La marque est obligatoires.");
			}

			if (refrigerator.getId() == null) refrigerator = new Refrigerator();
			else refrigerator = refrigeratorService.findById(refrigerator.getId());
			
			
			refrigerator.setColdLevel(coldLevel);
			refrigerator.setBrand(brand);
			
			refrigeratorService.save(refrigerator);

			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(refrigerator.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			refrigeratorService.remove(refrigeratorService.findById(id));
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(id);
			modelAndView.addObject("error", "Impossible de supprimer l'élément.");
			return modelAndView;
		}

	}
	

}
