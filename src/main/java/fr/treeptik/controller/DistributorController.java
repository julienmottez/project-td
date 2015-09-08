package fr.treeptik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Distributor;
import fr.treeptik.service.DistributorService;

@Controller
@RequestMapping(value="/admin")
public class DistributorController {

	private DistributorService service;
	
	
	@RequestMapping(value = "/distributor/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("person");
		modelAndView.addObject("distributor", new Distributor());
		return modelAndView;
	}

	@RequestMapping(value = "/distributor/list.html", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("player");
		view.addObject("distributeurs",service.findAll());
		return view;
	}
	
	
}
