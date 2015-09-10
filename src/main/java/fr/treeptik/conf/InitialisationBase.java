package fr.treeptik.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.Address;
import fr.treeptik.entity.Area;
import fr.treeptik.entity.Coordinate;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Drink;
import fr.treeptik.entity.Rack;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.entity.Sector;
import fr.treeptik.entity.Technician;
import fr.treeptik.entity.TemperatureRange;
import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.AreaService;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.DrinkService;
import fr.treeptik.service.PersonService;
import fr.treeptik.service.RackService;
import fr.treeptik.service.RefrigeratorService;
import fr.treeptik.service.SectorService;
import fr.treeptik.service.TechnicianService;
import fr.treeptik.service.TypeDistributorService;
import fr.treeptik.service.TypeRackService;

@Component
public class InitialisationBase {
	public InitialisationBase() {

	}

	// ============================ SERVICE =================================
	@Autowired
	private DrinkService drinkService;
	@Autowired
	private PersonService personService;
	@Autowired
	private RackService rackService;
	@Autowired
	private TypeRackService typeRackService;
	@Autowired
	private DistributorService distributorService;
	@Autowired
	private TypeDistributorService typeDistributorService;
	@Autowired
	private SectorService sectorService;
	@Autowired
	private TechnicianService technicianService;
	@Autowired
	private RefrigeratorService refrigeratorService;
	@Autowired
	private AreaService areaService;
	
	public void run() {
		initAdress();
		initRefregirators();
		initSectors();
		initDrinks();
		initTechnicians();
		initTypeDistributor();
		initDistributor();
		initRack();
		initArea();
	}

	// attention à l'ordre d'init
	private void initDrinks() {
		drinkService.save(new Drink(1, TemperatureRange.inCelsius(0, 5)));
		drinkService.save(new Drink(2, TemperatureRange.inCelsius(4, 8)));
		drinkService.save(new Drink(3, TemperatureRange.inCelsius(3, 6)));
		drinkService.save(new Drink(4, TemperatureRange.inCelsius(0, 2)));
	}

	private Address adress1;
	private Address adress2;
	private void initAdress() {
		adress1 = new Address("rue toto", "totoCity", "totoZIP");
		adress2 = new Address("rue tata", "tataCity", "tataZIP");
	}

	private Distributor distributor1;
	private Distributor distributor2;

	private void initDistributor() {
		distributor1 = new Distributor();
		distributor1.setAddress(adress1);
		distributor1.getRefrigerators().add(refrigerator1);
		distributor1.setSector(sector1);
		distributor1.setTechnician(technician1);
		distributor1.setTypeDistributor(typeDistributor1);

		distributor2 = new Distributor();
		distributor2.setAddress(adress2);
		distributor2.getRefrigerators().add(refrigerator2);
		distributor2.setSector(sector2);
		distributor2.setTechnician(technician2);
		distributor2.setTypeDistributor(typeDistributor2);

		distributorService.save(distributor1);
		distributorService.save(distributor2);
	}

	private Refrigerator refrigerator1;
	private Refrigerator refrigerator2;

	private void initRefregirators() {
		refrigeratorService.getLogger();
		refrigerator1 = new Refrigerator();
		refrigerator2 = new Refrigerator();
		try {
			refrigeratorService.save(refrigerator1);
			refrigeratorService.save(refrigerator2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Sector sector1;
	private Sector sector2;

	private void initSectors() {
		sector1 = new Sector();
		sector2 = new Sector();
		try {
			sectorService.save(sector1);
			sectorService.save(sector2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Technician technician1;
	private Technician technician2;

	private void initTechnicians() {
		technician1 = new Technician();
		technician2 = new Technician();
		try {
			technicianService.save(technician1);
			technicianService.save(technician2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private TypeDistributor typeDistributor1;
	private TypeDistributor typeDistributor2;

	private void initTypeDistributor() {
		typeDistributor1 = new TypeDistributor(1, "type distributor 1", 10, 5);
		typeDistributor2 = new TypeDistributor(2, "type distributor 2", 5, 8);
		try {
			typeDistributorService.save(typeDistributor1);
			typeDistributorService.save(typeDistributor2);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	private void initRack() {
		TypeRack typeRack1 = new TypeRack();
		typeRack1.setName("SMALL");
		typeRack1.setQuantity(10);

		TypeRack typeRack2 = new TypeRack();
		typeRack2.setName("MEDIUM");
		typeRack2.setQuantity(20);

		Rack rack1 = new Rack();
		rack1.setColonneDistributor(11);
		rack1.setLigneDistributor(55);
		rack1.setTypeRack(typeRack1);
		rack1.setDistributor(distributor1);

		Rack rack2 = new Rack();
		rack2.setColonneDistributor(8);
		rack2.setLigneDistributor(6);
		rack2.setTypeRack(typeRack2);
		rack2.setDistributor(distributor1);

		try {
			typeRackService.save(typeRack1);
			typeRackService.save(typeRack2);

			rackService.save(rack1);
			rackService.save(rack2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	private void initArea() {
		try {

			
			areaService.save(new Area(new Coordinate(50.45897F, 32.98742F), new Coordinate(60.45897F, 42.98742F)));
			areaService.save(new Area(new Coordinate(40.45897F, 42.98742F), new Coordinate(70.45897F, 82.98742F)));
			areaService.save(new Area(new Coordinate(30.45897F, 52.98742F), new Coordinate(90.45897F, 12.98742F)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	
	
	

}
