package fr.treeptik.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import fr.treeptik.conf.ApplicationConfiguration;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.entity.Temperature;
import fr.treeptik.service.RefrigeratorService;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@WebAppConfiguration
public class RefrigeratorControllerTest {

	@Mock
	private RefrigeratorService refrigeratorService;

	@InjectMocks
	private RefrigeratorController controller;
	
	private MockMvc mockMvc;

	private List<Refrigerator> allEntities;

	private Distributor distributor;
	
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        
        distributor = new Distributor();
		distributor.setId(1);
        
        allEntities = new ArrayList<>();
		allEntities.add(new Refrigerator(1, Temperature.inCelsius(5), "some_brand", distributor));
		allEntities.add(new Refrigerator(2, Temperature.inCelsius(10), "some_brand", distributor));
		allEntities.add(new Refrigerator(3, Temperature.inCelsius(15), "some_brand", distributor));
    }
	
	@Test
	public void testList() throws Exception {
		Map<String, List<Refrigerator>> expectedModel = new LinkedHashMap<>();
		expectedModel.put("refrigerators", allEntities);
		
		when(refrigeratorService.findAll()).thenReturn(allEntities);
		
		ModelAndView mav = controller.list();
		
		Assert.assertEquals("list-refrigerator", mav.getViewName());
		Assert.assertEquals(expectedModel, mav.getModel());
	}
	
	@Test
	public void testListGivenNoEntity() throws Exception {
		ArrayList<Refrigerator> emptyList = new ArrayList<>();
		
		Map<String, List<Refrigerator>> expectedModel = new LinkedHashMap<>();
		expectedModel.put("refrigerators", emptyList);
		
		when(refrigeratorService.findAll()).thenReturn(emptyList);
		
		ModelAndView mav = controller.list();
		
		Assert.assertEquals("list-refrigerator", mav.getViewName());
		Assert.assertEquals(expectedModel, mav.getModel());
	}
	
	@Test
	public void testSave() throws Exception {
		
	}
}
