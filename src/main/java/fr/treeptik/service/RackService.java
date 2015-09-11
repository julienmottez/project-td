package fr.treeptik.service;

import java.util.List;
import java.util.stream.Stream;

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

	public String generateName(Rack rack) {
		int num = rack.getLigneDistributor();

		if (num > 26) {
			num = 26;
		}
		
		if (num < 1) {
			num = 1;
		}
		
		num = num + 64;

		return (char) num + Integer.toString(rack.getColonneDistributor());
	}
	
	
	
	public void setRackName(Rack rack) {
		rack.setName(generateName(rack));
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
			Rack savedRack = rackDAO.save(rack);
			savedRack.setName(generateName(savedRack));
			
			return savedRack;
			
		} catch (PersistenceException e) {
			logger.error("erreur update rack " + e.getMessage());
			throw new ServiceException("erreur update rack", e);
		}
	}
	
	public List<Rack> findByTypeRackId(Integer idTypeRack) throws ServiceException {
		try {
			
			List<Rack> findByTypeRackId = rackDAO.findByTypeRack_Id(idTypeRack);
			findByTypeRackId.stream().forEach(e -> e.setName(generateName(e)));
			
			return findByTypeRackId;
			
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById rack", e);
		}
	}


	public Rack verifyRack(Rack rack) {
		try {
			
			Integer DistributorNumberColmuns= rack.getDistributor().getTypeDistributor().getNumberColumns();
			Integer DistributorNumberLines= rack.getDistributor().getTypeDistributor().getNumberLines();

			Integer RackColumnDistributor= rack.getColonneDistributor();
			Integer RackLineDistributor= rack.getLigneDistributor();

			if (RackColumnDistributor == null || RackColumnDistributor == 0) {
				throw new FormException("Le numero de colonne est obligatoires.");
			}
			if (RackLineDistributor == null || RackLineDistributor == 0) {
				throw new FormException("Le numero de ligne est obligatoires.");
			}
			if (rack.getTypeRack() == null) {
				throw new FormException("Choisir un type de rack");
			}
			if (rack.getDistributor() == null) {
				throw new FormException("Choisir un distributeur");
			}
			
			if(RackLineDistributor>DistributorNumberLines && RackColumnDistributor>DistributorNumberColmuns)
				throw new FormException("Erreur max");

			return rack;
		} catch (Exception e) {
			return rack;
		}
	}

	public List<Rack> findAll() throws ServiceException {
		try {
			List<Rack> findAll = rackDAO.findAll();
			findAll.stream().forEach(e -> e.setName(generateName(e)));
			
			return findAll;
			
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll rack", e);
		}
	}

	public Rack findById(Integer id) throws ServiceException {
		try {
			Rack rack = rackDAO.findOne(id);
			rack.setName(generateName(rack));;
			
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
