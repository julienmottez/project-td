package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Distributor;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DistributorService;

@Controller
@RequestMapping(value="/admin/distributor/")
public class DistributorController {

	@Autowired
	private DistributorService service;
	
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("distributor");
		modelAndView.addObject("distributor", new Distributor());
		return modelAndView;
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("admin/distributor/list");
		view.addObject("distributeurs",service.findAll());
		view.addObject("distributor", new Distributor());
		return view;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Distributor distributor, BindingResult result)
			throws ServiceException {
		try {
			if (distributor.getId() == null) {
				service.save(distributor);
			} else {
				service.update(distributor);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:admin/distributor/list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(distributor.getId());
			return modelAndView;
		}
	}

	private ModelAndView edit(Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("distributeur");
			Distributor distributor = service.findById(id);
			modelAndView.addObject("distributor", distributor);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}
}
