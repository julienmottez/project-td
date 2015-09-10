package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.TechnicianDAO;
import fr.treeptik.entity.Technician;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TechnicianService {

	private Logger logger = Logger.getLogger(TechnicianService.class);

	@Autowired
	private TechnicianDAO technicianDAO;

	@Transactional()
	public Technician save(Technician technician) throws ServiceException {
		logger.debug("appel de la methode save technician " + technician.getLastName());

		try {
			return technicianDAO.save(technician);
		} catch (PersistenceException e) {
			logger.error("erreur save personne " + e.getMessage());
			throw new ServiceException("erreur save technician", e);
		}
	}

	@Transactional
	public Technician update(Technician technician) throws ServiceException {
		logger.debug("appel de la methode update technician " + technician.getLastName());
		try {
			return technicianDAO.save(technician);
		} catch (PersistenceException e) {
			logger.error("erreur update personne " + e.getMessage());
			throw new ServiceException("erreur update technician", e);
		}
	}

	public List<Technician> findAll() throws ServiceException {
		try {
			return technicianDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll technician", e);
		}
	}

	public Technician findById(Integer id) throws ServiceException {
		try {
			return technicianDAO.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById technician", e);
		}
	}

	@Transactional
	public void deleteTechnician(Technician technician) throws ServiceException {
		try {
			technicianDAO.delete(technician.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save technician", e);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TechnicianDAO getTechnicianDAO() {
		return technicianDAO;
	}

	public void setTechnicianDAO(TechnicianDAO technicianDAO) {
		this.technicianDAO = technicianDAO;
	}

	
	
	 
}
