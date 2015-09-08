package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Technician;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.TechnicianService;

@Controller
@RequestMapping(value = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService technicianService;


	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		
		ModelAndView modelAndView = new ModelAndView("technician");
		modelAndView.addObject("technician", new Technician());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("technician");
			Technician technician = technicianService.findById(id);
			modelAndView.addObject("technician", technician);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-technician");
		try {
			modelAndView.addObject("technician", technicianService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}
	

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Technician technician) throws ServiceException {
		try {
			if (technician.getId() == null) {
				technicianService.save(technician);
			} else {
				technicianService.update(technician);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(technician.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(Technician technician) throws ServiceException {
		
		technicianService.deleteTechnician(technician);
		ModelAndView modelAndView = new ModelAndView("redirect:list.do");
		modelAndView.addObject("technician", new Technician());
		return modelAndView;
	}

}
