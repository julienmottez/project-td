package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.RefrigeratorDao;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class RefrigeratorService {

	private Logger logger = Logger.getLogger(RefrigeratorService.class);
	
	@Autowired
	private RefrigeratorDao refrigeratorDao;

	@Transactional
	public Refrigerator save(Refrigerator refrigerator) throws ServiceException {
		logger.debug("appel de la methode save Refrigerator " + refrigerator.getBrand());
		try {
			return refrigeratorDao.save(refrigerator);
		} catch (PersistenceException e) {
			logger.error("erreur save Refrigerator " + e.getMessage());
			throw new ServiceException("erreur save Refrigerator", e);
		}
	}

	@Transactional
	public void remove(Refrigerator refrigerator) throws ServiceException {
		logger.debug("appel de la methode remove Refrigerator " + refrigerator.getBrand());
		try {
			refrigeratorDao.delete(refrigerator);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("Refrigerator id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("erreur remove Refrigerator", e);
		} catch (Exception e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("erreur remove Refrigerator", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Refrigerator id " + id);
		try {
			refrigeratorDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("Refrigerator id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("erreur removeById Refrigerator", e);
		} catch (Exception e) {
			logger.error("erreur remove Refrigerator " + e.getMessage());
			throw new ServiceException("erreur removeById Refrigerator", e);
		}
	}
	
	public Refrigerator findById(Integer id) throws ServiceException {
		try {
			return refrigeratorDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<Refrigerator> findAll() throws ServiceException {
		try {
			return refrigeratorDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Refrigerator", e);
		}
	}
	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public RefrigeratorDao getRefrigeratorDao() {
		return refrigeratorDao;
	}

	public void setRefrigeratorDao(RefrigeratorDao refrigeratorDao) {
		this.refrigeratorDao = refrigeratorDao;
	}


}
