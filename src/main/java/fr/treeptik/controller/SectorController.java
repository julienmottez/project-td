package fr.treeptik.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Sector;
import fr.treeptik.service.DistributionPointService;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.SectorService;


@Controller
@RequestMapping(value = "/admin/sector/")
public class SectorController {

	@Autowired
	private SectorService sectorservice;
	
	@Autowired
	private DistributionPointService distributionPointService;
	
	@Autowired
	private DistributorService distributorService;
	

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("admin/sector/sector");
		modelAndView.addObject("sector", new Sector());

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/sector/sector");
			Sector sector = sectorservice.findById(id);
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
			modelAndView.addObject("sectors", sectorservice.findAll());
			modelAndView.addObject("distributionPointss", distributionPointService.findAll());
			modelAndView.addObject("distributors", distributorService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Sector sector) throws ServiceException {
		try {
			if (sector.getId() == null) {
				sectorservice.save(sector);
				
		/*		List<DistributionPoint>distributionPoints = sector.getDistributionPoints();  
				DistributionPoint dp = null;  
				  if(distributionPoints != null){  
				   for(DistributionPoint distributionPoint : distributionPoints){  
				    dp = new DistributionPoint();  
				  //  dp.s;  
				    dp.setSectordp(sector);  
				    distributionPointService.save(dp);  
				    
				    
				    List<Distributor>distributors = sector.getDistributors();  
				    Distributor distributor = null;  
				    if(distributors != null){  
				     for(Distributor dist : distributors){  
				    	 distributor = new Distributor();  
				    	
				    	 distributor.setSector(sector);   
				    	 distributorService.save(dist);  
				     }  
				    }  
				      
				   }  
				  }  */
				
				
			} else {
				sectorservice.update(sector);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(sector.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(Sector sector) throws ServiceException {
		try {
			
				sectorservice.delete(sector);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

}
