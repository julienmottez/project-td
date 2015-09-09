package fr.treeptik.service;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import fr.treeptik.dao.DrinkDao;
import fr.treeptik.dao.RefrigeratorDao;
import fr.treeptik.dao.TechnicianDAO;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.entity.Technician;
import fr.treeptik.exception.ServiceException;

public class TechnicianServiceTest {

	@Test
	public void verifySave() throws ServiceException {
		TechnicianDAO technicianDAO = mock(TechnicianDAO.class);
		TechnicianService technicianService = new TechnicianService();
		technicianService.setTechnicianDAO(technicianDAO);
		
		Technician technician = new Technician();
		
		technicianService.save(technician);
		verify(technicianService.getTechnicianDAO()).save(technician);
	}
}
