package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.ProductionManager;
import fr.treeptik.entity.Technician;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.ProductionManagerService;
import fr.treeptik.service.SectorService;
import fr.treeptik.service.TechnicianService;

@Controller
@RequestMapping(value = "admin/productionmanager")
public class ProductionManagerController {

	@Autowired
	private ProductionManagerService productionManagerService;
	


	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		
		ModelAndView modelAndView = new ModelAndView("admin/productionmanager/productionmanager");
		modelAndView.addObject("productionmanager", new ProductionManager());

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/productionmanager/productionmanager");
			ProductionManager productionManager = productionManagerService.findById(id);
			modelAndView.addObject("productionManager", productionManager);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/productionmanager/list-productionmanager");
		try {
			modelAndView.addObject("productionManagers", productionManagerService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}
	

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(ProductionManager productionManager) throws ServiceException {
		try {
			if (productionManager.getId() == null) {
				productionManagerService.save(productionManager);
			} else {
				productionManagerService.update(productionManager);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(productionManager.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(ProductionManager productionManager) throws ServiceException {
		
		productionManagerService.delete(productionManager);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("productionManager", new ProductionManager());
		return modelAndView;
	}

}
