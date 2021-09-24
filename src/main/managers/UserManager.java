package main.managers;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import main.User;

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
    
    public long create(String firstName, String lastName, boolean isAdmin, String userName, String password, String email) {
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
        		e.printStackTrace();
    		}
    	} finally {
    		session.close();
    	}
    	
    	return 1;
    }
    
    public User get(long id) {
    	
    }
    
    public void delete(long id) {
    	
    }
    
    public void update(long id) {
        
    }
}
