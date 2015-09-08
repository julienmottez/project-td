package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.entity.Area;
import fr.treeptik.dao.AreaDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class AreaService {
	
	private Logger logger = Logger.getLogger(AreaService.class);

	@Autowired
	
	private AreaDAO AreaDAO;

	@Transactional()
	public Area save(Area Area) throws ServiceException {
		logger.debug("appel de la methode save technician " + Area.getId());

		try {
			return AreaDAO.save(Area);
		} catch (PersistenceException e) {
			logger.error("erreur save Area " + e.getMessage());
			throw new ServiceException("erreur save Area", e);
		}
	}

	@Transactional
	public Area update(Area Area) throws ServiceException {
		logger.debug("appel de la methode update technician " + Area.getId());
		try {
			return AreaDAO.save(Area);
		} catch (PersistenceException e) {
			logger.error("erreur update Area " + e.getMessage());
			throw new ServiceException("erreur update Area", e);
		}
	}

	public List<Area> findAll() throws ServiceException {
		try {
			return AreaDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Area", e);
		}
	}

	public Area findById(Integer id) throws ServiceException {
		try {
			return AreaDAO.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Area", e);
		}
	}

	@Transactional
	public void delete(Area Area) throws ServiceException {
		try {
			AreaDAO.delete(Area.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save Area", e);
		}
	}
	

	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		AreaDAO.delete(id);
			
	
	}
	
	

}
