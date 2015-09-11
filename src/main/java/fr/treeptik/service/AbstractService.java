package fr.treeptik.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.entity.InterfaceEntity;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class AbstractService<E extends InterfaceEntity<I>, I extends Serializable> {

	protected Logger logger = Logger.getLogger(AbstractService.class);

	protected Class<E> entityType;

	public AbstractService(Class<E> entityType) {
		this.entityType = entityType;
	}

	@Autowired
	private JpaRepository<E, I> dao;

	@Transactional()
	public E save(E t) throws ServiceException {
		logger.debug("appel de la methode save " + entityType.getName() + t.getId());

		try {
			return dao.save(t);
		} catch (PersistenceException e) {
			logger.error("erreur save " + entityType.getName() + t.getId() + e.getMessage());
			throw new ServiceException("erreur save " + " id :" + t.getId(), e);
		}

	}

	@Transactional
	public E update(E t) throws ServiceException {
		logger.debug("appel de la methode update " + entityType.getName() + t.getId());
		try {
			return dao.save(t);
		} catch (PersistenceException e) {
			logger.error("erreur update " + entityType.getName() + " id = " + t.getId() + e.getMessage());
			throw new ServiceException("erreur update " + entityType.getName() + " id = " + t.getId(), e);
		}
	}

	public List<E> findAll() throws ServiceException {
		try {
			return dao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll " + entityType.getName(), e);
		}
	}

	public E findById(I id) throws ServiceException {
		try {
			E t = dao.findOne(id);
			return t;
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById " + entityType.getName(), e);
		}

	}

	@Transactional
	public void delete(E t) throws ServiceException {
		try {
			dao.delete(t.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save " + entityType.getName() + "id = " + t.getId(), e);
		}
	}

	public void setDAO(JpaRepository<E, I> dao) {
		this.dao = dao;
	}

}
