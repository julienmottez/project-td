package fr.treeptik.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import fr.treeptik.dao.DistributionPointDAO;
import fr.treeptik.entity.DistributionPoint;

public class DistributionPointServiceTest {

	private static final int ENTITY_ID = 999_999;
	
	private DistributionPointService distributionPointService;
	private DistributionPointDAO mockDistributionPointDAO;
	private DistributionPoint distributionPoint;
	
	@Before
	public void setUp() {
		mockDistributionPointDAO = mock(DistributionPointDAO.class);
		distributionPointService = new DistributionPointService(mockDistributionPointDAO);
	}
	
	@Test
	public void testFindById() throws Exception {
		distributionPointService.findById(ENTITY_ID);
		
		verify(mockDistributionPointDAO).findById(ENTITY_ID);
	}
	
	@Test
	public void testsave() throws Exception {
		distributionPointService.save(distributionPoint);
		
		verify(mockDistributionPointDAO).save(distributionPoint);
	}
	
	
	@Test
	public void testupdate() throws Exception {
		distributionPointService.update(distributionPoint);
		
		verify(mockDistributionPointDAO).save(distributionPoint);
	}
	
	
	@Test
	public void testfindAll() throws Exception {
		distributionPointService.findAll();
		
		verify(mockDistributionPointDAO).findAll();
	}
	
	@Test
	public void testdelete() throws Exception {
		distributionPointService.delete(distributionPoint);
		
		verify(mockDistributionPointDAO).delete(distributionPoint);
	}
	
	@Test
	public void testremoveById() throws Exception {
		distributionPointService.removeById(ENTITY_ID);
		
		verify(mockDistributionPointDAO).delete(ENTITY_ID);

	}
	
	
	
}
