package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Rack;
import fr.treeptik.entity.Technician;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.TypeRackService;

@Controller
@RequestMapping(value = "/admin/typeRack/")
public class TypeRackController {

	@Autowired
	private TypeRackService typeRackService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("admin/typeRack/typeRack");
		modelAndView.addObject("typeRack", new TypeRack());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/typeRack/typeRack");
			TypeRack typeRack = typeRackService.findById(id);
			modelAndView.addObject("typeRack", typeRack);
			// modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/typeRack/list-typeRack");
		try {
			modelAndView.addObject("typeRacks", typeRackService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(TypeRack typeRack) throws ServiceException {
		try {
			if (typeRack.getId() == null) {
				typeRackService.save(typeRack);
			} else {
				// rack = rackService.findById(rack.getId());
				typeRackService.update(typeRack);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(typeRack.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(TypeRack typeRack) throws ServiceException {

		typeRackService.deleteTypeRack(typeRack);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("typeRack", new Rack());
		return modelAndView;
	}
}
