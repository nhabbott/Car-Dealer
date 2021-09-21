package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.Vehicle.fuelType;
import main.Vehicle.numOfCylinders;
import main.Vehicle.vehicleSize;
import main.Vehicle.vehicleTrans;
import main.Vehicle.vehicleType;

public class VehicleManager {
	protected SessionFactory sessionFactory;
	
    public void setup() {
    	// Load settings from hibernate.cfg.xml
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    	
    	// Analyze class mappings and create DB connection
    	try {
    		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (Exception e) {
    		StandardServiceRegistryBuilder.destroy(registry);
    	}
    }
 
    public void exit() {
    	// Close the DB connection
        sessionFactory.close();
    }
    
    public void create(String vin, vehicleType type, vehicleSize size, int year, String make, String model, numOfCylinders cylinders, vehicleTrans trans, fuelType fuel, String countryOfProd, int mileage) {
        // Create vehicle
    	Vehicle v = new Vehicle(vin, type, size, year, make, model, cylinders, trans, fuel, countryOfProd, mileage);
        
        // Save to DB
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(v);
    	
    	session.getTransaction().commit();
    	session.close();
    }
 
    public void read() {
       
    }
 
    public void update() {
        
    }
 
    public void delete() {
        Vehicle v = new Vehicle();
    }
}
