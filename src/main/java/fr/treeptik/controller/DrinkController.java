package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Drink;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.DistributionPointService;
import fr.treeptik.service.DrinkService;

@Controller
@RequestMapping("/admin/drink")
public class DrinkController {

	@Autowired
	private DrinkService drinkService;
	@Autowired
	private DistributionPointService distributionPointService;

	@InitBinder
	    protected void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(List.class, "distributionPoints", new CustomCollectionEditor(List.class) {
	        	
	        	
	        	Integer id=null;
	        	@Override
	        	protected Object convertElement(Object element) {
	        		if(element instanceof DistributionPoint) {
	        		
	        			return element;
	        		}
	        		if(element instanceof Integer) {
	        		
	        			DistributionPoint distributionPoint = new DistributionPoint();
	        				distributionPoint.setId(Integer.valueOf((String) element));
	        				id=(Integer) element;
	        				try {
								return id != null ? distributionPointService.findById(id) : null;
							} catch (ServiceException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							};
	        			
	        		}
	        		if(element instanceof String) {
	        			
	        			DistributionPoint distributionPoint = new DistributionPoint();
	        				distributionPoint.setId(Integer.parseInt( (String) element));
	        			return distributionPoint;
	        			
	        		}
	        		return super.convertElement(element);
	        	}
	        });
	    }

/*
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	    binder.registerCustomEditor(DistributionPoint.class, "distributionPoints", new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) {
	    	DistributionPoint ch;
			try {
				ch = distributionPointService.findById(Integer.parseInt(text));
				setValue(ch);
			} catch (NumberFormatException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	    });
	}*/
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("admin/drink/drink");
		modelAndView.addObject("drink", new Drink());
		try {
			modelAndView.addObject("distributionpoints", distributionPointService.findAll());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.addObject("action", "Ajouter");

		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("admin/drink/drink");

			modelAndView.addObject("drink", drinkService.findById(id));
			modelAndView.addObject("action", "Editer");

			return modelAndView;

		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("admin/drink/list-drink");

		try {
			modelAndView.addObject("drinks", drinkService.findAll());

		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}

		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Drink drink,BindingResult result) throws ServiceException {
		 if(result.hasErrors()){
		 System.out.println(result.getAllErrors().toString());
		 
		 }
		
		try {
			if (drink.getId() == null) {
				drinkService.save(drink);

			} else {
				drinkService.update(drink);
			}

			ModelAndView modelAndView = new ModelAndView("redirect:list.html");

			return modelAndView;

		} catch (Exception e) {

			ModelAndView modelAndView = edit(drink.getId());
			modelAndView.addObject("error", e.getMessage());

			return modelAndView;
		}
	}

	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		ModelAndView modelAndView = null;

		try {
			drinkService.removeById(id);

			modelAndView = new ModelAndView("redirect:list.html");

			return modelAndView;

		} catch (Exception e) {
			modelAndView = edit(id);

			modelAndView.addObject("error", e.getMessage());

			return modelAndView;
		}
	}
}
