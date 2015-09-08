package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.service.DistributionPointService;


@Controller
@RequestMapping(value = "/distributionPoint")
public class DistributionPointController {

	@Autowired
	private DistributionPointService distributionPointService;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("distributionPoint");
		modelAndView.addObject("distributionPoint", new DistributionPoint());
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("distributionPoint");
			DistributionPoint distributionPoint = distributionPointService.findById(id);

			modelAndView.addObject("distributionPointMaker", distributionPoint);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-distributionPoint");
		try {
			modelAndView.addObject("distributionPoints", distributionPointService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(DistributionPoint distributionPoint) throws ServiceException {
		try {
			if (distributionPoint.getId() == null) {
				distributionPointService.save(distributionPoint);
			} else {
				distributionPointService.update(distributionPoint);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(distributionPoint.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
			distributionPointService.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}
