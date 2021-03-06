package managers;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import exceptions.DatabaseErrorException;
import objects.Listing;
import objects.Vehicle;

public class ListingManager {
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
     * @param Vehicle - Vehicle object to store
     * @param Price  - Price of the vehicle
     * @param userId - DB id of the user that owns the listing
     * @return id - The id given to the object by MySQL
     * @throws DatabaseErrorException 
     */
    public long create(Vehicle v, float price, long userId) throws DatabaseErrorException {
    	// Open session and create return var
    	Session session = sessionFactory.openSession();
    	long id = -1;
    	
    	// Send to DB
    	try {
    		Listing l = new Listing(v, price, userId);
    		
    		// Save
    		session.beginTransaction();
    		id = (long)session.save(l);
    		session.getTransaction().commit();
    	} catch (HibernateException e) {
    		if (id == -1) {
    			session.getTransaction().rollback();
    			throw new DatabaseErrorException("There was an error creating a new listing", e);
    		}
    	} finally {
    		session.close();
    	}
    	
    	return id;
    }
    
    /**
     * Creates a new vehicle entry in the DB
     * 
     * @param Vehicle - Vehicle object to store
     * @param Price  - Price of the vehicle
     * @param userId - DB id of the user that owns the listing
     * @param publishListing - Whether or not the listing is viewable by the public
     * @return id - The id given to the object by MySQL
     * @throws DatabaseErrorException 
     */
    public long create(Vehicle v, float price, long userId, boolean publishListing) throws DatabaseErrorException {
    	// Open session and create return var
    	Session session = sessionFactory.openSession();
    	long id = -1;
    	
    	// Send to DB
    	try {
    		Listing l = new Listing(v, price, userId, publishListing);
    		
    		// Save
    		session.beginTransaction();
    		id = (long)session.save(l);
    		session.getTransaction().commit();
    	} catch (HibernateException e) {
    		if (id == -1) {
    			session.getTransaction().rollback();
    			throw new DatabaseErrorException("There was an error creating a new listing", e);
    		}
    	} finally {
    		session.close();
    	}
    	
    	return id;
    }
    
	/**
	 * Retrieves a vehicle entries from the DB
	 * 
	 * @return Listing - The desired listing object
	 * @throws DatabaseErrorException 
	 */
    public Listing get(long id) throws DatabaseErrorException {
    	// Open session and create return var
    	Session session = sessionFactory.openSession();
    	Listing l = null;
    	
    	// Get from DB
    	try {
    		l = (Listing) session.get(Listing.class, id);
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error retreiving a listing with an id: " + id, e);
    	} finally {
    		session.close();
    	}
    	
    	return l;
    }
    
	/**
	 * Retrieves all listing entries from the DB
	 * 
	 * @return List - A list of all listings stored in the DB
	 * @throws DatabaseErrorException 
	 */
    @SuppressWarnings("unchecked")
	public List<Listing> getAll() throws DatabaseErrorException {
    	// Open session and create return var
    	Session session = sessionFactory.openSession();
    	List<Listing> listings = null;
    	
    	// Get from DB
    	try {
    		session.beginTransaction();
    		listings = session.createQuery("FROM listing").list();
    		session.getTransaction().commit();
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error retreiving all listings: ", e);
    	} finally {
    		session.close();
    	}
    	
    	return listings;
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
    	
    	// Delete from DB 
    	try {
    		Listing l = (Listing) session.get(Listing.class, id);
    		
    		session.beginTransaction();
    		session.delete(l);
    		session.getTransaction().commit();
    		return true;
    	} catch (HibernateException e) {
    		session.getTransaction().commit();
    		throw new DatabaseErrorException("", e);
    	} finally {
    		session.close();
    	}
    }
}
