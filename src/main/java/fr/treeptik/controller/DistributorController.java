package fr.treeptik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Distributor;
import fr.treeptik.service.DistributorService;

@Controller
@RequestMapping(value="/admin/distributor/")
public class DistributorController {

	private DistributorService service;
	
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("distributeur");
		modelAndView.addObject("distributor", new Distributor());
		return modelAndView;
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("admin/distributor/list");
		System.out.println("test");
	//	view.addObject("distributeurs",service.findAll());
		return view;
	}
	
	
}
