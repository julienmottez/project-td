package fr.treeptik.service;
import java.util.List;
import javax.persistence.PersistenceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fr.treeptik.dao.SectorDAO;
import fr.treeptik.entity.Drink;
import fr.treeptik.entity.Sector;
import fr.treeptik.exception.ServiceException;
@Service
@Scope(value = "singleton")
public class SectorService {
	
	private Logger logger = Logger.getLogger(SectorService.class);
	
	@Autowired
	private SectorDAO sectorDAO;
	
	public SectorService(){
		
	}
	
	public SectorService(SectorDAO sectorDao) {
		this.sectorDAO=sectorDao;
		}

	@Transactional()
	public Sector save(Sector sector) throws ServiceException {
		logger.debug("appel de la methode save sector " + sector.getName());
		try {
			return sectorDAO.save(sector);
		} catch (PersistenceException e) {
			logger.error("erreur save personne " + e.getMessage());
			throw new ServiceException("erreur save sector", e);
		}
	}
	
	@Transactional
	public Sector update(Sector sector) throws ServiceException {
		logger.debug("appel de la methode update sector " + sector.getName());
		try {
			return sectorDAO.save(sector);
		} catch (PersistenceException e) {
			logger.error("erreur update personne " + e.getMessage());
			throw new ServiceException("erreur update sector", e);
		}
	}
	
	public List<Sector> findAll() throws ServiceException {
		try {
			return sectorDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll sector", e);
		}
	}
	
	public Sector findById(Integer id) throws ServiceException {
		try {
			return sectorDAO.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById sector", e);
		}
	}
	
	@Transactional
	public void delete(Sector sector) throws ServiceException {
		try {
			sectorDAO.delete(sector.getId());
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save sector", e);
		}
	}

	public Sector getById(int id) {
        return sectorDAO.findById(id);
    }
}
