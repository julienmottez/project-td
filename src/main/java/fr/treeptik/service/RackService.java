package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.RackDAO;
import fr.treeptik.entity.Rack;
import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class RackService {

	private Logger logger = Logger.getLogger(RackService.class);

	@Autowired
	private RackDAO rackDAO;

	// @Autowired
	// private EncryptionService encryptionService;

	public void setRackName(Rack rack) {
		int num = rack.getLigneDistributor();

		if (num > 26) {
			num = 26;
		}
		if (num < 1) {
			num = 1;
		}
		num = num + 64;

		rack.setName((char) num + Integer.toString(rack.getColonneDistributor()));
		// System.out.println((char) num +
		// Integer.toString(rack.getColonneDistributor()));
	}

	@Transactional()
	public Rack save(Rack rack) throws ServiceException {
		logger.debug("appel de la methode save rack " + rack.getName());

		try {
			return rackDAO.save(rack);
		} catch (PersistenceException e) {
			logger.error("erreur save rack " + e.getMessage());
			throw new ServiceException("erreur save rack", e);
		}
	}

	@Transactional
	public Rack update(Rack rack) throws ServiceException {
		logger.debug("appel de la methode update rack " + rack.getName());
		try {
			return rackDAO.save(rack);
		} catch (PersistenceException e) {
			logger.error("erreur update rack " + e.getMessage());
			throw new ServiceException("erreur update rack", e);
		}
	}

	public Rack verifyRack(Rack rack) {
		try {
			Integer numCol = rack.getColonneDistributor();
			Integer numLigne = rack.getLigneDistributor();

			if (numCol == null || numCol == 0) {
				throw new FormException("Le numero de colonne est obligatoires.");
			}
			if (numLigne == null || numLigne == 0) {
				throw new FormException("Le numero de ligne est obligatoires.");
			}
			if (rack.getTypeRack() == null) {
				throw new FormException("Choisir un type de rack");
			}
			if (rack.getDistributor() == null) {
				throw new FormException("Choisir un distributeur");
			}
			return rack;
		} catch (Exception e) {
			return rack;
		}
	}

	public List<Rack> findAll() throws ServiceException {
		try {
			return rackDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll rack", e);
		}
	}

	public Rack findById(Integer id) throws ServiceException {
		try {
			Rack rack = rackDAO.findOne(id);
//			setRackName(rack);
			// return rackDAO.findOne(id);
			return rack;
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById rack", e);
		}

	}

	@Transactional
	public void deleteRack(Rack rack) throws ServiceException {
		try {
			rackDAO.delete(rack.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save rack", e);
		}
	}

	public void setRackDAO(RackDAO rackDAO) {
		this.rackDAO = rackDAO;
	}
}
