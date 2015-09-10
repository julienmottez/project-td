package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Distributor;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.SectorService;
import fr.treeptik.service.TypeDistributorService;

@Controller
@RequestMapping(value = "/admin/distributor/")
public class DistributorController {

	@Autowired
	private DistributorService serviceDistributor;

	@Autowired
	private SectorService sectorService;
	@Autowired
	private TypeDistributorService typeDistributorService;

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		ModelAndView modelAndView = new ModelAndView("admin/distributor/distributor");
		modelAndView.addObject("distributor", new Distributor());
		modelAndView.addObject("sectors", sectorService.findAll());
		modelAndView.addObject("typeDistributors", typeDistributorService.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("distributor");
			Distributor distributor = serviceDistributor.findById(id);
			modelAndView.addObject("distributor", distributor);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView view = new ModelAndView("admin/distributor/list-distributor");
		try {
			view.addObject("distributors", serviceDistributor.findAll());
		} catch (Exception e) {
			view.addObject("error", e.getMessage());
		}
		return view;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Distributor distributor) throws ServiceException {
		try {
			if (distributor.getId() == null) {
				serviceDistributor.save(distributor);
			} else {
				serviceDistributor.update(distributor);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(distributor.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Distributor distributor) throws ServiceException {

		serviceDistributor.remove(distributor);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("distributor", new Distributor());
		return modelAndView;
	}

}
