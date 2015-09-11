package fr.treeptik.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.treeptik.entity.Address;
import fr.treeptik.entity.Area;
import fr.treeptik.entity.Coordinate;
import fr.treeptik.entity.DistributionPoint;
import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Drink;
import fr.treeptik.entity.Rack;
import fr.treeptik.entity.Refrigerator;
import fr.treeptik.entity.Sector;
import fr.treeptik.entity.SectorManager;
import fr.treeptik.entity.Technician;
import fr.treeptik.entity.TemperatureRange;
import fr.treeptik.entity.TypeDistributor;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.service.AreaService;
import fr.treeptik.service.DistributionPointService;
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
	private DistributionPointService distributionPointService;
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
		initDistributionPoint();
		initTechnicians();
		initTypeDistributor();
		initDistributor();
		initRack();
		initArea();
		initManagers();
	}

	// attention à l'ordre d'init
	private Drink drink;
	private Drink drink2;
	private Drink drink3;
	private Drink drink4;
	private void initDrinks() {
		drink=new Drink(1, TemperatureRange.inCelsius(0, 5));
		drink2=new Drink(2, TemperatureRange.inCelsius(4, 8));
		drink3=new Drink(3, TemperatureRange.inCelsius(3, 6));
		drink4=new Drink(4, TemperatureRange.inCelsius(0, 2));
		drinkService.save(drink);
		drinkService.save(drink2);
		drinkService.save(drink3);
		drinkService.save(drink4);
	}
	
	
	private DistributionPoint distributionpoint ;
	private DistributionPoint distributionpoint2 ;
	
	private void initDistributionPoint() {
		List<Drink> drinks=new ArrayList<Drink>();
		List<Drink> drinks1=new ArrayList<Drink>();
		drinks.add(drink);
		drinks.add(drink3);
		drinks1.add(drink2);
		drinks1.add(drink4);
		
		distributionpoint=new DistributionPoint();
		distributionpoint2=new DistributionPoint();
		distributionpoint.setId(1);
		distributionpoint.setName("Coca Cola Factory");
		distributionpoint2.setId(2);
		distributionpoint2.setName("Orangina Factory");
		distributionpoint.setDrinks(drinks);
		distributionpoint2.setDrinks(drinks1);
		distributionPointService.save(distributionpoint);
		distributionPointService.save(distributionpoint2);
		
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
		sector1.setName("secteur1");
		sector1.setManagerSector(sectorManager1);
	
		

		sector2 = new Sector();
		sector2.setName("secteur2");
		sector2.setManagerSector(sectorManager2);
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
		technician1.setAdress(adress1);
		technician1.setFirstName("robert");
		technician1.setLastName("dupond");
		technician1.setSector(sector1);
		List<Distributor>distributors=new ArrayList<Distributor>();
		distributors.add(distributor1);
		distributors.add(distributor2);
		technician1.setDistributors(distributors);
		
		technician2 = new Technician();
		technician2.setAdress(adress2);
		technician2.setFirstName("TOTO");
		technician2.setLastName("tata");
		technician2.setSector(sector2);
		List<Distributor>distributors2=new ArrayList<Distributor>();
		distributors2.add(distributor2);
		distributors2.add(distributor1);
		technician1.setDistributors(distributors2);
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
	
	private SectorManager sectorManager1; 
	private SectorManager sectorManager2;
	
	private void initManagers() {
		try {
			sectorManager1 = new SectorManager();
			sectorManager1.setFirstName("Jean");
			sectorManager1.setLastName("Dupont");
			personService.save(sectorManager1);
			
			sectorManager2 = new SectorManager();
			sectorManager2.setFirstName("Jack");
			sectorManager2.setLastName("Boulet");
			personService.save(sectorManager2);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
