package fr.treeptik.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.exception.ServiceException;

@Controller
public abstract class AbstractController {

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public abstract ModelAndView add();

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public abstract ModelAndView edit(@ModelAttribute("id") Integer id);

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public abstract ModelAndView list();

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public abstract ModelAndView save(TypeDistributor typeDistributor) throws ServiceException;

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public abstract ModelAndView delete(TypeDistributor typeDistributor) throws ServiceException;

}
