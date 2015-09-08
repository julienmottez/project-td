package fr.treeptik.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.DistributorDao;
import fr.treeptik.entity.Distributor;

@Service
@Scope(value = "singleton")
public class DistributorService {
	
	private Logger logger = Logger.getLogger(DistributorService.class);
	@Autowired
	private DistributorDao dao;
	
	public Distributor save(Distributor distributeur) throws Exception {
		logger.debug("appel de la methode save distributeur " + distributeur.getId());
		try {
			return dao.save(distributeur);
		} catch (Exception e) {
			logger.error("erreur save distributeur " + e.getMessage());
			throw new ServiceException("erreur save distributeur", e);
		}
	}
	

	@Transactional()
	public Distributor update(Distributor distributeur) throws Exception {
		logger.debug("appel de la methode udate distributeur " + distributeur.getId());
		try {
			return dao.save(distributeur);
		} catch (Exception e) {
			logger.error("erreur save distributeur " + e.getMessage());
			throw new ServiceException("erreur update distributeur", e);
		}
	}
	
	@Transactional()
	public void remove(Distributor distributeur) throws Exception {
		logger.debug("appel de la methode remove distributeur " + distributeur.getId());
		try {
			dao.delete(distributeur);
		} catch (Exception e) {
			logger.error("erreur save distributeur " + e.getMessage());
			throw new ServiceException("erreur remove distributeur", e);
		}
	}
	
	@Transactional()
	public Distributor findById(Integer id) throws Exception {
		logger.debug("appel de la methode find by id distributeur ");
		try {
			return dao.findOne(id);
		} catch (Exception e) {
			logger.error("erreur save distributeur " + e.getMessage());
			throw new ServiceException("erreur findbyid distributeur", e);
		}
	}
}
