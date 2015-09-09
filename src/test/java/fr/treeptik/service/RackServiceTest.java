package fr.treeptik.service;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import fr.treeptik.dao.RackDAO;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Rack;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.exception.ServiceException;

public class RackServiceTest {
	private static final int ENTITY_ID = 999_999;

	private RackService rackService;
	private RackDAO mockRackDao;

	private Rack rack;
	private Distributor distributor;

	@Before
	public void setUp() {
		mockRackDao = mock(RackDAO.class);
		rackService = new RackService();
		rackService.setRackDAO(mockRackDao);

		rack = new Rack();

		rack.setId(1);
		rack.setColonneDistributor(1);
		rack.setLigneDistributor(5);
		rack.setTypeRack(new TypeRack());
		rackService.setRackName(rack);

		distributor = new Distributor();
		rack.setDistributor(distributor);
	}

	@Test
	public void testFindById() throws Exception {
		rackService.findById(ENTITY_ID);
		verify(mockRackDao).findOne(ENTITY_ID);
	}

	@Test
	public void testSaveRack() throws Exception {
		rackService.save(rack);
		verify(mockRackDao).save(rack);
	}

	@Test
	public void testDeleteRack() throws Exception {
		rackService.deleteRack(rack);
		verify(mockRackDao).delete(rack.getId());
	}

	@Test
	public void testVerifyEntity() {
		rackService.verifyRack(rack);
	}

	@Test
	public void testUpdateRack() throws ServiceException {
		rackService.update(rack);
		verify(mockRackDao).save(rack);
	}

	@Test
	public void testSetRackName() {
		rackService.setRackName(rack);
		assertFalse(rack.getName().isEmpty());
	}
}
