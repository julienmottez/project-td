package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.DistributionPointDAO;

import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Drink;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value ="singleton")
public class DistributionPointService {

	private Logger logger = Logger.getLogger(DistributionPointDAO.class);

	@Autowired
	
	private DistributionPointDAO distributionPointDAO;
	
	

	public DistributionPointService(DistributionPointDAO distributionPointDAO) {
	
		this.distributionPointDAO = distributionPointDAO;
	}

	@Transactional()
	public DistributionPoint save(DistributionPoint distributionPoint) throws ServiceException {
		logger.debug("appel de la methode save distributionPoint " + distributionPoint.getId());

		try {
			return distributionPointDAO.save(distributionPoint);
		} catch (PersistenceException e) {
			logger.error("erreur save distributionPoint " + e.getMessage());
			throw new ServiceException("erreur save distributionPoint", e);
		}
	}

	@Transactional
	public DistributionPoint update(DistributionPoint distributionPoint) throws ServiceException {
		logger.debug("appel de la methode update technician " + distributionPoint.getId());
		try {
			return distributionPointDAO.save(distributionPoint);
		} catch (PersistenceException e) {
			logger.error("erreur update distributionPoint " + e.getMessage());
			throw new ServiceException("erreur update distributionPoint", e);
		}
	}

	public List<DistributionPoint> findAll() throws ServiceException {
		try {
			return distributionPointDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll distributionPoint", e);
		}
	}

	public DistributionPoint findById(Integer id) throws ServiceException {
		try {
			return distributionPointDAO.findById(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById distributionPoint", e);
		}
	}

	@Transactional
	public void delete(DistributionPoint distributionPoint) throws ServiceException {
		try {
			distributionPointDAO.delete(distributionPoint.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save distributionPoint", e);
		}
	}
	

	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		distributionPointDAO.delete(id);
			
	
	}
	


	
}
