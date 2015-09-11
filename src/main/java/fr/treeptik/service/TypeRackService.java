package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.TypeRackDAO;
import fr.treeptik.entity.Rack;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TypeRackService {

	private Logger logger = Logger.getLogger(TypeRackService.class);

	@Autowired
	private TypeRackDAO typeRackDAO;

	@Transactional()
	public TypeRack save(TypeRack typeRack) throws ServiceException {
		logger.debug("appel de la methode save rack " + typeRack.getName());
		try {
			return typeRackDAO.save(typeRack);
		} catch (PersistenceException e) {
			logger.error("erreur save typeRack " + e.getMessage());
			throw new ServiceException("erreur save typeRack", e);
		}
	}

	@Transactional
	public TypeRack update(TypeRack typeRack) throws ServiceException {
		logger.debug("appel de la methode update rack " + typeRack.getName());
		try {
			return typeRackDAO.save(typeRack);
		} catch (PersistenceException e) {
			logger.error("erreur update TypeRack " + e.getMessage());
			throw new ServiceException("erreur update TypeRack", e);
		}
	}

	public List<TypeRack> findAll() throws ServiceException {
		try {
			return typeRackDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll TypeRack", e);
		}
	}

	public TypeRack findById(Integer id) throws ServiceException {
		try {
			TypeRack typeRack = typeRackDAO.findOne(id);
			// setRackName(rack);
			// return rackDAO.findOne(id);
			return typeRack;
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById typeRack", e);
		}
	}

	@Transactional
	public void deleteTypeRack(TypeRack typeRack) throws ServiceException {
		try {
			typeRackDAO.delete(typeRack.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save typeRack", e);
		}
	}

	public void setTypeRackDAO(TypeRackDAO typeRackDAO) {
		this.typeRackDAO = typeRackDAO;
	}
}
