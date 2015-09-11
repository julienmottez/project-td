package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Technician;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.SectorService;
import fr.treeptik.service.TechnicianService;

@Controller
@RequestMapping(value = "admin/technician")
public class TechnicianController {

	@Autowired
	private TechnicianService technicianService;
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private DistributorService distributorService;
	



	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		
		ModelAndView modelAndView = new ModelAndView("admin/technician/technician");
		modelAndView.addObject("technician", new Technician());
		modelAndView.addObject("sectors", sectorService.findAll());
		modelAndView.addObject("distributorss", distributorService.findAll());

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/technician/technician");
			Technician technician = technicianService.findById(id);
			modelAndView.addObject("technician", technician);
			modelAndView.addObject("sectors", sectorService.findAll());
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/technician/list-technician");
		try {
			modelAndView.addObject("technicians", technicianService.findAll());
			modelAndView.addObject("sectors", sectorService.findAll());
			modelAndView.addObject("distributorss", distributorService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}
	

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Technician technician) throws ServiceException {
		try {
			if (technician.getId() == null) {
				technicianService.save(technician);
			} else {
				technicianService.update(technician);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(technician.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Technician technician) throws ServiceException {
		
		technicianService.deleteTechnician(technician);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("technician", new Technician());
		return modelAndView;
	}

}
