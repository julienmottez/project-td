package fr.treeptik.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Drink;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.service.DistributionPointService;
import fr.treeptik.service.PersonService;
import fr.treeptik.service.SectorService;


@Controller
@RequestMapping(value = "/admin/distributionpoint/")
public class DistributionPointController {

	@Autowired
	private DistributionPointService distributionPointService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView("admin/distributionpoint/distributionpoint");
		modelAndView.addObject("distributionPoint", new DistributionPoint());
		modelAndView.addObject("sectordps", sectorService.findAll());
		modelAndView.addObject("productionManagers", personService.findAll());
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/distributionpoint/distributionpoint");
			DistributionPoint distributionPoint = distributionPointService.findById(id);

			modelAndView.addObject("distributionPoint", distributionPoint);
		   
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list-drink-dp.html", method = RequestMethod.GET)
	public ModelAndView listdp(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/distributionpoint/list-distributionPoint");
			List<Drink> drinks=new ArrayList<Drink>();
			Drink drink =new Drink();
			drink.setId(id);
			drinks.add(drink);
			List<DistributionPoint> distributionPoint = distributionPointService.findByDrinks(drinks);

			modelAndView.addObject("distributionpoints", distributionPoint);
		   
			return modelAndView;
		} catch (Exception e) {
			return listdp(id);
		}
	}
	
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/distributionpoint/list-distributionPoint");
		try {
			modelAndView.addObject("distributionpoints", distributionPointService.findAll());
			modelAndView.addObject("sectordps", sectorService.findAll());
			modelAndView.addObject("productionManagers", personService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(DistributionPoint distributionPoint) throws ServiceException {
		try {
			if (distributionPoint.getId() == null) {
				distributionPointService.save(distributionPoint);
			} else {
				distributionPointService.update(distributionPoint);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(distributionPoint.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
			distributionPointService.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}
