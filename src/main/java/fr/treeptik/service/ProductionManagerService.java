package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.ProductionManagerDAO;
import fr.treeptik.entity.ProductionManager;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class ProductionManagerService {

	private Logger logger = Logger.getLogger(ProductionManagerService.class);

	@Autowired
	//@Qualifier("productionManagerDAO")
	private ProductionManagerDAO productionManagerDAO;
	
	@Autowired
	private EncryptionService encryptionService;


	@Transactional()
	public ProductionManager save(ProductionManager productionManager) throws ServiceException {
		logger.debug("appel de la methode save productionManager " + productionManager.getLastName());

		try {
			return productionManagerDAO.save(productionManager);
		} catch (PersistenceException e) {
			logger.error("erreur save personne " + e.getMessage());
			throw new ServiceException("erreur save productionManager", e);
		}
	}

	@Transactional
	public ProductionManager update(ProductionManager productionManager) throws ServiceException {
		logger.debug("appel de la methode update productionManager " + productionManager.getLastName());
		try {
			return productionManagerDAO.save(productionManager);
		} catch (PersistenceException e) {
			logger.error("erreur update personne " + e.getMessage());
			throw new ServiceException("erreur update productionManager", e);
		}
	}

	public List<ProductionManager> findAll() throws ServiceException {
		try {
			return productionManagerDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll productionManager", e);
		}
	}

	public ProductionManager findById(Integer id) throws ServiceException {
		try {
			return productionManagerDAO.findById(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById productionManager", e);
		}
	}

	@Transactional
	public void delete(ProductionManager productionManager) throws ServiceException {
		try {
			productionManagerDAO.delete(productionManager.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save productionManager", e);
		}
	}
	


	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public ProductionManagerDAO getProductionManagerDAO() {
		return productionManagerDAO;
	}

	public void setProductionManagerDAO(ProductionManagerDAO productionManagerDAO) {
		this.productionManagerDAO = productionManagerDAO;
	}

	public EncryptionService getEncryptionService() {
		return encryptionService;
	}

	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}
	
	 
}
