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
	//@Qualifier("technicianDAO")
	private TechnicianDAO technicianDAO;
	
	@Autowired
	private EncryptionService encryptionService;


	@Transactional()
	public Technician save(Technician technician) throws ServiceException {
		logger.debug("appel de la methode save technician " + technician.getName());

		try {
			return technicianDAO.save(technician);
		} catch (PersistenceException e) {
			logger.error("erreur save personne " + e.getMessage());
			throw new ServiceException("erreur save technician", e);
		}
	}

	@Transactional
	public Technician update(Technician technician) throws ServiceException {
		logger.debug("appel de la methode update technician " + technician.getName());
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
	
	public boolean login(String login, String mdp) throws ServiceException {
		Technician technician;
		try {
			technician=technicianDAO.findByName(login);
			if (technician.getEncryptedPassword().equals(encryptionService.encrypt(mdp))) {
				return true;
			}
			
		} catch (Exception e) {
			throw new ServiceException("erreur login", e);
		}
		return false;
	}
}
