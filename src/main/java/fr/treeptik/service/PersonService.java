package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.treeptik.dao.PersonDAO;
import fr.treeptik.entity.Person;
import fr.treeptik.entity.SectorManager;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")

public class PersonService {

	private Logger logger = Logger.getLogger(PersonDAO.class);

	@Autowired
	private PersonDAO personDAO;

	@Transactional()
	public Person save(Person person) throws ServiceException {
		logger.debug("appel de la methode save person " + person.getFirstName());

		try {
			return personDAO.save(person);
		} catch (PersistenceException e) {
			logger.error("erreur save person " + e.getMessage());
			throw new ServiceException("erreur save person", e);
		}
	}

	@Transactional
	public Person update(Person person) throws ServiceException {
		logger.debug("appel de la methode update technician " + person.getId());
		try {
			return personDAO.save(person);
		} catch (PersistenceException e) {
			logger.error("erreur update person " + e.getMessage());
			throw new ServiceException("erreur update person", e);
		}
	}

	public List<Person> findAll() throws ServiceException {
		try {
			return personDAO.findAll();
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException("erreur findAll person", e);
		}
	}

	public List<SectorManager> findAllSectorManager() throws ServiceException {
		try {
			return personDAO.findAllSectorManager();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllSectorManager SectorManager", e);
		}
	}
	
	
	public Person findById(Integer id) throws ServiceException {
		try {
			return personDAO.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById person", e);
		}
	}

	@Transactional
	public void delete(Person person) throws ServiceException {
		try {
			personDAO.delete(person.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save person", e);
		}
	}
	

	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		personDAO.delete(id);
			
	
	}
	
	
	
}
