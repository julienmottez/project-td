package fr.treeptik.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.dao.DistributionPointDAO;
import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Sector;
import fr.treeptik.entity.User;
import fr.treeptik.service.AreaService;
import fr.treeptik.service.DistributionPointService;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.PersonService;
import fr.treeptik.service.SectorService;


@Controller
@RequestMapping(value = "/admin/sector/")
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private DistributionPointService distributionPointService;
	
	@Autowired
	private DistributorService distributorService;
	
	private Map<String, Distributor> distributorCache;
	private Map<String, DistributionPoint> distributionPointsCache;
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("admin/sector/sector");
		
		modelAndView.addObject("areas", areaService.findAll());
		modelAndView.addObject("managers", personService.findAllSectorManager());
		
		distributorCache = new HashMap<>();
		List<Distributor> distributors = distributorService.findAll();
		for (Distributor distributor : distributors) {
			distributorCache.put(distributor.getId().toString(), distributor);
		}
		modelAndView.addObject("distributors", distributors);
		
		distributionPointsCache = new HashMap<>();
		List<DistributionPoint> distributionPoints = distributionPointService.findAll();
		for (DistributionPoint distributionPoint : distributionPoints) {
			distributionPointsCache.put(distributionPoint.getId().toString(), distributionPoint);
		}
		modelAndView.addObject("distributionPoints", distributionPoints);
		
		modelAndView.addObject("sector", new Sector());
		modelAndView.addObject("action", "Ajouter");
		
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/sector/sector");
			Sector sector = sectorService.findById(id);
			modelAndView.addObject("sector", sector);
			
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/sector/list-sector");
		try {
			modelAndView.addObject("sectors", sectorService.findAll());
			modelAndView.addObject("distributionPointss", distributionPointService.findAll());
			
			
			modelAndView.addObject("distributors", distributorCache);
			
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Sector sector) throws ServiceException {
		
		System.out.println("save");
		
		try {
			
			for (Distributor distributor : sector.getDistributors()) {
				System.out.println(distributor.getLabel());
			}
			
			for (DistributionPoint distributionPoint : sector.getDistributionPoints()) {
				System.out.println(distributionPoint.getName());
			}
			
			if (sector.getName() == null || sector.getName() == "") {
				throw new FormException("Le nom est obligatoire.");
			}
			if (sector.getArea() == null) {
				throw new FormException("La zone est obligatoire.");
			}
			if (sector.getManagerSector() == null) {
				throw new FormException("Le manager est obligatoire.");
			}

			if (sector.getId() == null) {
				sectorService.save(sector);
			} else {
				sectorService.update(sector);
			}

			System.out.println(sector.getName());
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			
			
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(sector.getId());
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
		
		binder.registerCustomEditor(List.class, "distributionPoints", new CustomCollectionEditor(List.class) {
			protected Object convertElement(Object element) {
				if (element instanceof DistributionPoint) {
					System.out.println("Converting from Staff to Staff: " + element);
					return element;
				}
				if (element instanceof String) {
					DistributionPoint distributionPoint = distributionPointsCache.get(element);
					System.out.println("Looking up distributorPoint for id " + element + ": " + distributionPoint.getName());
					return distributionPoint;
				}
				System.out.println("Don't know what to do with: " + element);
				return null;
			}
		});
		
		
	}
	
	
	
	
	
	

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Sector sector) throws ServiceException {
		try {
			
				sectorService.delete(sector);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

}
