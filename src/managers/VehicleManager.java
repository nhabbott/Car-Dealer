package managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import exceptions.DatabaseErrorException;
import objects.Vehicle;
import objects.Vehicle.fuelType;
import objects.Vehicle.numOfCylinders;
import objects.Vehicle.vehicleSize;
import objects.Vehicle.vehicleTrans;
import objects.Vehicle.vehicleType;

public class VehicleManager {
	protected SessionFactory sessionFactory;

    /**
     * Analyzes classes and creates the appropriate mappings.
     */
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
 
    /**
     * Closes the connection to the database
     */
    public void exit() {
    	// Close the DB connection
        sessionFactory.close();
    }
    
    /**
     * Creates a new vehicle entry in the DB
     * 
     * @param vin VIN of the vehicle
     * @param type Coupe, Crossover, Truck, Sedan, Sportscar, Hatchback
     * @param size Full, Mid, Compact 
     * @param year Year of manufacture
     * @param make Who produced the vehicle
     * @param model
     * @param cylinders Number of cylinders in the engine
     * @param trans Automatic or Manual
     * @param fuel Gasloine, Diesel, Hybrid, Electric
     * @param countryOfProd Where was the vehicle made
     * @param mileage How many miles
     * @return id - The id given to the object by MySQL
     * @throws DatabaseErrorException 
     */
    public long create(String vin, vehicleType type, vehicleSize size, int year, String make, String model, numOfCylinders cylinders, vehicleTrans trans, fuelType fuel, String countryOfProd, int mileage) throws DatabaseErrorException {
    	// Open session and create var to return
    	Session session = sessionFactory.openSession();
    	long id = -1;
    	
    	// Retrieve data from DB
    	try {
    		Vehicle v = new Vehicle(vin, type, size, year, make, model, cylinders, trans, fuel, countryOfProd, mileage);
    		
	        // Save to DB
	    	session.beginTransaction();
	    	id = (long)session.save(v);
	    	session.getTransaction().commit();
    	} catch (HibernateException e) {
    		if (id == -1) {
        		session.getTransaction().rollback();
        		throw new DatabaseErrorException("There was an error creating a new vehicle", e);
    		}
    	} finally {
    		session.close();
    	}
    	
    	return id;
    }
 
    /**
     * Gets the object from the DB by id
     * @param id
     * @return Vehicle
     * @throws DatabaseErrorException
     * @see Vehicle
     */
    public Vehicle get(long id) throws DatabaseErrorException {
    	// Open session
    	Session session  = sessionFactory.openSession();
    	Vehicle v = null;
    	
    	// Delete item from DB
    	try {
    		v = (Vehicle) session.get(Vehicle.class, id);
    		
    	} catch (HibernateException e) {
    		throw new DatabaseErrorException("There was an error retreiving a vehicle with an id: " + id, e);
    	} finally {
    		session.close();
    	}
    	
    	// Return the user
    	return v;
    }
    
	/**
	 * Retrieves all vehicle entries from the DB
	 * 
	 * @return List<Vehicle> - A list of all vehicles stored in the DB
	 * @throws DatabaseErrorException 
	 */
	@SuppressWarnings("unchecked")
	public List<Vehicle> getAll() throws DatabaseErrorException {
		// Open session and create new list
    	Session session = sessionFactory.openSession();
    	List<Vehicle> vehicles = null;
    	
    	// Retrieve data from DB
    	try {
    		session.beginTransaction();
    		vehicles = session.createQuery("FROM Vehicle").list();
    		session.getTransaction().commit();
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error retreiving all vehicles", e);
    	} finally {
    		session.close();
    	}
    	
    	return vehicles;
    }
 
    public void update(long id) {
        
    }
 
    /**
     * Deletes a vehicle from the DB by id
     * 
     * @param id The id given to the object by MySQL
     * @return Boolean
     * @throws DatabaseErrorException 
     */
    public boolean delete(long id) throws DatabaseErrorException {
    	// Open session
    	Session session = sessionFactory.openSession();
    	
    	// Delete item from DB
    	try {
    		Vehicle v = (Vehicle) session.get(Vehicle.class, id);
    		
    		session.beginTransaction();
    		session.delete(v);
    		session.getTransaction().commit();
    		return true;
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error deleting a vehicle with an id: " + id, e);
    	} finally {
    		session.close();
    	}
    }
}
