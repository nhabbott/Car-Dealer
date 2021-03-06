package managers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import exceptions.DatabaseErrorException;
import objects.User;

public class UserManager {
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
    		e.printStackTrace();
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
    
    public long create(String firstName, String lastName, boolean isAdmin, String userName, String password, String email) throws DatabaseErrorException {
    	// Open session and create var to return
    	Session session = sessionFactory.openSession();
    	long id = -1;
    	
    	try {
    		User u = new User(firstName, lastName, isAdmin, userName, password, email);
    		
	        // Save to DB
	    	session.beginTransaction();
	    	id = (long)session.save(u);
	    	session.getTransaction().commit();
    	} catch (HibernateException e) {
    		if (id == -1) {
        		session.getTransaction().rollback();
        		throw new DatabaseErrorException("There was an error creating a new user", e);
    		}
    	} finally {
    		session.close();
    	}
    	
    	return id;
    }
    
    public User get(long id) throws DatabaseErrorException {
    	// Open session
    	Session session  = sessionFactory.openSession();
    	User u = null;
    	
    	// Delete item from DB
    	try {
    		u = (User) session.get(User.class, id);
    		
    	} catch (HibernateException e) {
    		throw new DatabaseErrorException("There was an error retreiving a user with an id: " + id, e);
    	} finally {
    		session.close();
    	}
    	
    	// Return the user
    	return u;
    }
    
    public boolean delete(long id) throws DatabaseErrorException {
    	// Open session
    	Session session = sessionFactory.openSession();
    	
    	// Delete item from DB
    	try {
    		User u = (User) session.get(User.class, id);
    		
    		session.beginTransaction();
    		session.delete(u);
    		session.getTransaction().commit();
    		return true;
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error deleting a user with an id: " + id, e);
    	} finally {
    		session.close();
    	}
    }
    
    public void update(long id) {
        
    }
}
