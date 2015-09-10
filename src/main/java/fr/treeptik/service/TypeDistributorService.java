package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.TypeDistributorDAO;
import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TypeDistributorService {

	private Logger logger = Logger.getLogger(RackService.class);

	@Autowired
	private TypeDistributorDAO typeDistributorDAO;

	@Transactional()
	public TypeDistributor save(TypeDistributor typeDistributor) throws ServiceException {
		logger.debug("appel de la methode save rack " + typeDistributor.getName());
		try {
			return typeDistributorDAO.save(typeDistributor);
		} catch (PersistenceException e) {
			logger.error("erreur save typeDistributor " + e.getMessage());
			throw new ServiceException("erreur save typeDistributor", e);
		}
	}

	@Transactional
	public TypeDistributor update(TypeDistributor typeDistributor) throws ServiceException {
		logger.debug("appel de la methode update typeDistributor " + typeDistributor.getName());
		try {
			return typeDistributorDAO.save(typeDistributor);
		} catch (PersistenceException e) {
			logger.error("erreur update typeDistributor " + e.getMessage());
			throw new ServiceException("erreur update typeDistributor", e);
		}
	}

	public List<TypeDistributor> findAll() throws ServiceException {
		try {
			return typeDistributorDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll typeDistributor", e);
		}
	}

	public TypeDistributor findById(Integer id) throws ServiceException {
		try {
			TypeDistributor typeDistributor = typeDistributorDAO.findOne(id);
			// setRackName(rack);
			// return rackDAO.findOne(id);
			return typeDistributor;
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById typeDistributor", e);
		}
	}

	@Transactional
	public void deleteTypeDistributor(TypeDistributor typeDistributor) throws ServiceException {
		try {
			typeDistributorDAO.delete(typeDistributor.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save typeDistributor", e);
		}
	}

	public void setTypeDistributorDAO(TypeDistributorDAO typeDistributorDAO) {
		this.typeDistributorDAO = typeDistributorDAO;
	}

}
