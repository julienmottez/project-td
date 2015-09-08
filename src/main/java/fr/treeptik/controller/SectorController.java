package fr.treeptik.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.entity.Sector;

import fr.treeptik.service.SectorService;


@Controller
@RequestMapping(value = "/sector")
public class SectorController {

	@Autowired
	private SectorService sectorservice;

	

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("sector");
		
		modelAndView.addObject("sector", new Sector());

		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("sector");
			Sector sector = sectorservice.findById(id);
			modelAndView.addObject("sector", sector);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-sector");
		try {
			modelAndView.addObject("sectors", sectorservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Sector sector) throws ServiceException {
		try {
			if (sector.getId() == null) {
				sectorservice.save(sector);
			} else {
				sectorservice.update(sector);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(sector.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(Sector sector) throws ServiceException {
		try {
			
				sectorservice.deleteSector(sector);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

}
