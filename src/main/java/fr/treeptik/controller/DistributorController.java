package fr.treeptik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.service.DistributorService;

@Controller
@RequestMapping(value="/admin")
public class DistributorController {

	private DistributorService service;
	
	@RequestMapping(value = "/distributor/list.html", method = RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView view = new ModelAndView("player");
		view.addObject("distributeurs",service.findAll());
		return view;
	}
	
	
}
