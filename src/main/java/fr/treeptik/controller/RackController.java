package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Rack;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.RackService;

@Controller
@RequestMapping(value = "/rack")
public class RackController {

	@Autowired
	private RackService rackService;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("rack");
		modelAndView.addObject("rack", new Rack());
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("rack");
			Rack rack = rackService.findById(id);

			modelAndView.addObject("rackMaker", rack);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-rack");
		try {
			modelAndView.addObject("rack", rackService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Rack rack) throws ServiceException {
		try {

			// Integer coldLevel = refrigerator.getColdLevel();
			// String brand = refrigerator.getBrand();
			//
			// if (coldLevel == null || coldLevel == 0) {
			// throw new FormException("Le niveau de froid est obligatoires.");
			// }
			// if (brand == null || brand == "") {
			// throw new FormException("La marque est obligatoires.");
			// }

			Integer numCol = rack.getColonneDistributor();
			Integer numLigne = rack.getLigneDistributor();

			if (numCol == null || numCol == 0) {
				throw new FormException("Le numero de colonne est obligatoires.");
			}
			if (numLigne == null || numLigne == 0) {
				throw new FormException("Le numero de ligne est obligatoires.");
			}
			if (rack.getTypeRack() == null) {
				throw new FormException("Choisir un type de rack");
			}
			if (rack.getId() == null) {
				rack = new Rack();
			} else {
				rack = rackService.findById(rack.getId());
			}

			rackService.save(rack);

			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(rack.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
}
