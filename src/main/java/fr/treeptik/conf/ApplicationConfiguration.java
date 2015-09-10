package fr.treeptik.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import fr.treeptik.entity.Distributor;
import fr.treeptik.entity.Drink;
import fr.treeptik.entity.Rack;
import fr.treeptik.entity.TemperatureRange;
import fr.treeptik.entity.TypeRack;
import fr.treeptik.service.DistributorService;
import fr.treeptik.service.DrinkService;
import fr.treeptik.service.PersonService;
import fr.treeptik.service.RackService;
import fr.treeptik.service.TypeRackService;

@Configuration
@ComponentScan(basePackages = "fr.treeptik")
@Import({ SecurityConfiguration.class })
@EnableWebMvc
@PropertySource(value = "classpath:config.properties", name = "config")
@EnableTransactionManagement
@EnableJpaRepositories("fr.treeptik.dao")

public class ApplicationConfiguration extends WebMvcConfigurerAdapter{
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.user"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setPersistenceUnitName("persistenceUnit");
		lcemfb.setDataSource(dataSource());
		lcemfb.setJpaDialect(new HibernateJpaDialect());
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter());
		lcemfb.setPackagesToScan("fr.treeptik.entity");

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		lcemfb.setJpaProperties(properties);
		lcemfb.afterPropertiesSet();

		return lcemfb.getObject();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return jpaVendorAdapter;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		return jpaTransactionManager;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/style/**").addResourceLocations("/style/");
	}
	
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver res = new InternalResourceViewResolver();
		res.setViewClass(JstlView.class);
		res.setPrefix("/WEB-INF/pages/");
		res.setSuffix(".jsp");
		return res;
	}

	@Component
	public static class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

		@Autowired
		private DrinkService drinkService;
		private PersonService personService;

		@Autowired
		private RackService rackService;
		@Autowired
		private TypeRackService typeRackService;
		@Autowired
		private DistributorService distributorService;

		public StartupListener() {
		}

		@Override
		public void onApplicationEvent(final ContextRefreshedEvent event) {
			initDrinks();
			initRack();
		}

		private void initDrinks() {
			drinkService.save(new Drink(1, TemperatureRange.inCelsius(0, 5)));
			drinkService.save(new Drink(2, TemperatureRange.inCelsius(4, 8)));
			drinkService.save(new Drink(3, TemperatureRange.inCelsius(3, 6)));
			drinkService.save(new Drink(4, TemperatureRange.inCelsius(0, 2)));

		}

		// init FAB pour les rack ! pas touche pas touche !
		private void initRack() {
			TypeRack typeRack1 = new TypeRack();
			typeRack1.setName("SMALL");
			typeRack1.setQuantity(10);

			TypeRack typeRack2 = new TypeRack();
			typeRack2.setName("MEDIUM");
			typeRack2.setQuantity(20);

			Distributor distributor = new Distributor();

			Rack rack1 = new Rack();
			rack1.setColonneDistributor(11);
			rack1.setLigneDistributor(55);
			rack1.setTypeRack(typeRack1);
			rack1.setDistributor(distributor);

			Rack rack2 = new Rack();
			rack2.setColonneDistributor(8);
			rack2.setLigneDistributor(6);
			rack2.setTypeRack(typeRack2);
			rack2.setDistributor(distributor);

			try {
				typeRackService.save(typeRack1);
				typeRackService.save(typeRack2);
				distributorService.save(distributor);
				rackService.save(rack1);
				rackService.save(rack2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
