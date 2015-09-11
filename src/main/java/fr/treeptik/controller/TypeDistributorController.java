package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.TypeDistributorService;

@Controller
@RequestMapping(value = "/admin/typeDistributor/")
public class TypeDistributorController extends AbstractController {

	@Autowired
	private TypeDistributorService typeDistributorService;

	@Override
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("admin/typeDistributor/typeDistributor");
		modelAndView.addObject("typeDistributor", new TypeDistributor());
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/typeDistributor/typeDistributor");
			TypeDistributor typeDistributor = typeDistributorService.findById(id);
			modelAndView.addObject("typeDistributor", typeDistributor);
			// modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@Override
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/typeDistributor/list-typeDistributor");
		try {
			modelAndView.addObject("typeDistributors", typeDistributorService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@Override
	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(TypeDistributor typeDistributor) throws ServiceException {
		try {
			if (typeDistributor.getId() == null) {
				typeDistributorService.save(typeDistributor);
			} else {
				// rack = rackService.findById(rack.getId());
				typeDistributorService.update(typeDistributor);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(typeDistributor.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	@Override
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(TypeDistributor typeDistributor) throws ServiceException {

		typeDistributorService.delete(typeDistributor);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("typeDistributor", new TypeDistributor());
		return modelAndView;
	}

}
