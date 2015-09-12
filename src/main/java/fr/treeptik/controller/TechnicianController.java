package fr.treeptik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.Distributor;
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
	
	private Map<String, Distributor> distributorCache;


	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException {
		
		ModelAndView modelAndView = new ModelAndView("admin/technician/technician");
		modelAndView.addObject("technician", new Technician());
		modelAndView.addObject("sectors", sectorService.findAll());
		distributorCache = new HashMap<>();
		List<Distributor> distributors = distributorService.findAll();
		for (Distributor distributor : distributors) {
			distributorCache.put(distributor.getId().toString(), distributor);
		}
		modelAndView.addObject("distributors", distributors);
		modelAndView.addObject("technician", new Technician());

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
			
			for (Distributor distributor : technician.getDistributors()) {
				System.out.println(distributor.getLabel());
			}
			
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
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(List.class, "distributors", new CustomCollectionEditor(List.class) {
			protected Object convertElement(Object element) {
				if (element instanceof Distributor) {
					System.out.println("Converting from Staff to Staff: " + element);
					return element;
				}
				if (element instanceof String) {
					Distributor distributor = distributorCache.get(element);
					System.out.println("Looking up staff for id " + element + ": " + distributor);
					return distributor;
				}
				System.out.println("Don't know what to do with: " + element);
				return null;
			}
		});
	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Technician technician) throws ServiceException {
		
		technicianService.deleteTechnician(technician);
		ModelAndView modelAndView = new ModelAndView("redirect:list.html");
		modelAndView.addObject("technician", new Technician());
		return modelAndView;
	}
	
	
}
