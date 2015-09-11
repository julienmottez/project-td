package fr.treeptik.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import fr.treeptik.TestConfiguration;
import fr.treeptik.conf.ApplicationConfiguration;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.entity.Temperature;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.RefrigeratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@WebAppConfiguration
public class RefrigeratorControllerTest {

	@Mock
	private RefrigeratorService refrigeratorService;
	
	@Mock
	private DistributorService distributorService;

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
	public void testNew() throws Exception {
		ArrayList<Distributor> list = new ArrayList<>();
		list.add(new Distributor());
		when(distributorService.findAll()).thenReturn(list);
		
		mockMvc.perform(get("/admin/refrigerator/new.html"))
				.andExpect(status().isOk())
				.andExpect(model().attribute("distributors", is(not(empty()))))
				.andExpect(model().attribute("temperature_units", is(not(empty()))));
		
		verify(distributorService, times(1)).findAll();
	}

	@Test
	public void testList() throws Exception {
		Map<String, List<Refrigerator>> expectedModel = new LinkedHashMap<>();
		expectedModel.put("refrigerators", allEntities);
		when(refrigeratorService.findAll()).thenReturn(allEntities);

		mockMvc.perform(get("/admin/refrigerator/list.html"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/refrigerator/list-refrigerator"))
				.andExpect(model().attribute("refrigerators", is(allEntities)));

		verify(refrigeratorService, times(1)).findAll();
	}

	@Test
	public void testListGivenNoEntities() throws Exception {
		ArrayList<Refrigerator> emptyList = new ArrayList<>();
		when(refrigeratorService.findAll()).thenReturn(emptyList);

		mockMvc.perform(get("/admin/refrigerator/list.html")).andExpect(status().isOk())
				.andExpect(view().name("admin/refrigerator/list-refrigerator"))
				.andExpect(model().attribute("refrigerators", is(emptyList)));

		verify(refrigeratorService, times(1)).findAll();
	}

	@Ignore
	@Test
	public void testListOnError() throws Exception {

	}

	@Test
	public void testSave() throws Exception {
		mockMvc.perform(post("/admin/refrigerator/save.html")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", "")
					.param("coldLevel.value", "5")
					.param("coldLevel.unit", "CELSIUS")
					.param("brand", "some_brand")
					.param("distributor_id", "1")
				)
				.andExpect(view().name("redirect:/admin/refrigerator/list.html"));
		
		verify(refrigeratorService, times(1)).save(any(Refrigerator.class));
		verify(refrigeratorService, never()).findById(anyInt());
	}

	@Test
	public void testSaveOnUpdate() throws Exception {
		when(refrigeratorService.findById(999)).thenReturn(new Refrigerator());
		
		mockMvc.perform(post("/admin/refrigerator/save.html")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)
					.param("id", "999")
					.param("coldLevel.value", "5")
					.param("coldLevel.unit", "CELSIUS")
					.param("brand", "some_brand")
					.param("distributor_id", "1")
				)
				.andExpect(view().name("redirect:/admin/refrigerator/list.html"));
		
		verify(refrigeratorService, times(1)).save(any(Refrigerator.class));
		verify(refrigeratorService, times(1)).findById(999);
	}
	
	@Test
	public void testSaveGivenWrongBrandNameBeingEmpty() throws Exception {		
		String brandName = "";
		
		mockMvc.perform(post("/admin/refrigerator/save.html")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "999")
			.param("coldLevel.value", "5")
			.param("coldLevel.unit", "CELSIUS")
			.param("brand", brandName)
			.param("distributor_id", "1")
		)
		.andExpect(view().name("admin/refrigerator/refrigerator"))
		.andExpect(model().attribute("error", "La marque est obligatoire."));
	}
	
	@Test
	public void testSaveGivenWrongBrandNameBeingNull() throws Exception {	
		String brandName = null;
		
		mockMvc.perform(post("/admin/refrigerator/save.html")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "999")
			.param("coldLevel.value", "5")
			.param("coldLevel.unit", "CELSIUS")
			.param("brand", brandName)
			.param("distributor_id", "1")
		)
		.andExpect(view().name("admin/refrigerator/refrigerator"))
		.andExpect(model().attribute("error", "La marque est obligatoire."));
	}
}
