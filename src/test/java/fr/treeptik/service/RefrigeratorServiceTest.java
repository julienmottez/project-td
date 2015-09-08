package fr.treeptik.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import fr.treeptik.dao.RefrigeratorDao;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.exception.ServiceException;

public class RefrigeratorServiceTest {
	
	@Test
	public void verifySave() throws ServiceException {
		RefrigeratorDao refrigeratorDao = mock(RefrigeratorDao.class);
		RefrigeratorService refrigeratorService = new RefrigeratorService();
		refrigeratorService.setRefrigeratorDao(refrigeratorDao);
		
		Refrigerator refrigerator = new Refrigerator();
		
		refrigeratorService.save(refrigerator);
		verify(refrigeratorService.getRefrigeratorDao()).save(refrigerator);
	}
	
	
	
}
