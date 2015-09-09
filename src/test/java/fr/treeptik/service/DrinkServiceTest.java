package fr.treeptik.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import fr.treeptik.dao.DrinkDao;

public class DrinkServiceTest {

	private static final int ENTITY_ID = 999_999;
	
	private DrinkService drinkService;
	private DrinkDao mockDrinkDao;
	
	@Before
	public void setUp() {
		mockDrinkDao = mock(DrinkDao.class);
		drinkService = new DrinkService(mockDrinkDao);
	}
	
	@Test
	public void testFindById() throws Exception {
		drinkService.findById(ENTITY_ID);
		
		verify(mockDrinkDao).findById(ENTITY_ID);
	}
}
