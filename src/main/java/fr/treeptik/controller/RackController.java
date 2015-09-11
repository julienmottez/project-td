package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Rack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.DrinkService;
import fr.treeptik.service.RackService;
import fr.treeptik.service.TypeRackService;

@Controller
@RequestMapping(value = "/admin/rack/")
public class RackController {

	@Autowired
	private RackService rackService;

	@Autowired
	private TypeRackService typeRackService;

	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private DrinkService drinkService;  

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView("admin/rack/rack");
		modelAndView.addObject("rack", new Rack());
		modelAndView.addObject("typeRacks", typeRackService.findAll());
		modelAndView.addObject("distributors", distributorService.findAll());
		modelAndView.addObject("drinks", drinkService.findAll());

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/rack/rack");
			Rack rack = rackService.findById(id);
			modelAndView.addObject("rack", rack);
			modelAndView.addObject("typeRacks", typeRackService.findAll());
			modelAndView.addObject("distributors", distributorService.findAll());
			modelAndView.addObject("drinks", drinkService.findAll());

			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/rack/list-rack");
		try {
			modelAndView.addObject("racks", rackService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Rack rack) throws ServiceException {
		try {
			rackService.verifyRack(rack);
			if (rack.getId() == null) {
				rackService.save(rack);
			} else {
				// rack = rackService.findById(rack.getId());
				rackService.update(rack);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(rack.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Rack rack) throws ServiceException {

		rackService.deleteRack(rack);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("rack", new Rack());
		return modelAndView;
	}
}
