package fr.treeptik.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

import fr.treeptik.conf.ApplicationConfiguration;
import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.service.TypeDistributorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
@WebAppConfiguration
public class DistributorTypeControllerTest {

	@Mock
	private TypeDistributorService typeDistributorService;

	@InjectMocks
	private TypeDistributorController controller;

	private MockMvc mockMvc;
	private List<TypeDistributor> allEntities;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		allEntities = new ArrayList<>();

		allEntities.add(new TypeDistributor(1, "SMALL", 5, 4));
		allEntities.add(new TypeDistributor(2, "MEDIUM", 15, 2));
		allEntities.add(new TypeDistributor(3, "LARGE", 9, 3));

	}

	@Test
	public void testNew() throws Exception {
		mockMvc.perform(get("/admin/typeDistributor/new.html")).andExpect(status().isOk());
	}

	@Test
	public void testList() throws Exception {
		Map<String, List<TypeDistributor>> expectedModel = new LinkedHashMap<>();
		expectedModel.put("typeDistributors", allEntities);
		when(typeDistributorService.findAll()).thenReturn(allEntities);

		mockMvc.perform(get("/admin/typeDistributor/list.html")).andExpect(status().isOk())
				.andExpect(view().name("admin/typeDistributor/list-typeDistributor"))
				.andExpect(model().attribute("typeDistributors", is(allEntities)));

		verify(typeDistributorService, times(1)).findAll();
	}

	@Test
	public void testListGivenNoEntities() throws Exception {
		ArrayList<TypeDistributor> emptyList = new ArrayList<>();
		when(typeDistributorService.findAll()).thenReturn(emptyList);

		mockMvc.perform(get("/admin/typeDistributor/list.html")).andExpect(status().isOk())
				.andExpect(view().name("admin/typeDistributor/list-typeDistributor"))
				.andExpect(model().attribute("typeDistributors", is(emptyList)));

		verify(typeDistributorService, times(1)).findAll();
	}

	@Test
	public void testSave() throws Exception {
		mockMvc.perform(post("/admin/typeDistributor/save.html").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "").param("name", "SMALL").param("numberColumns", "5").param("numberLines", "4"))
				.andExpect(view().name("redirect:/admin/typeDistributor/list.html"));

		verify(typeDistributorService, times(1)).save(any(TypeDistributor.class));
		verify(typeDistributorService, never()).findById(anyInt());
	}

	@Test
	public void testSaveOnUpdate() throws Exception {
		when(typeDistributorService.findById(999)).thenReturn(new TypeDistributor());

		mockMvc.perform(post("/admin/typeDistributor/save.html").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "999").param("name", "LOW").param("numberColumns", "12").param("numberLines", "2"))
				.andExpect(view().name("redirect:/admin/typeDistributor/list.html"));

		verify(typeDistributorService, times(1)).update(any(TypeDistributor.class));
		// verify(typeDistributorService, times(1)).findById(999);
	}

	// @Test
	// public void testSaveGivenWrongNameIsEmpty() throws Exception {
	// String nameType = "";
	//
	// mockMvc.perform(post("/admin/typeDistributor/save.html").contentType(MediaType.APPLICATION_FORM_URLENCODED)
	// .param("id", "").param("name", nameType).param("numberColumns",
	// "12").param("numberLines", "2"))
	//// .andExpect(view().name("admin/typeDistributor/list.html"))
	// .andExpect(model().attribute("error", "Le nom est obligatoire"));
	// }
	//
	// @Test
	// @Ignore
	// public void testSaveGivenWrongNameIsNull() throws Exception {
	// String nameType = null;
	//
	// mockMvc.perform(post("/admin/typeDistributor/save.html").contentType(MediaType.APPLICATION_FORM_URLENCODED)
	// .param("id", "").param("name", nameType).param("numberColumns",
	// "12").param("numberLines", "2"))
	//// .andExpect(view().name("admin/typeDistributor/list.html"))
	// .andExpect(model().attribute("error", "Le nom est obligatoire"));
	// }
}
