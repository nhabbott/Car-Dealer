package tests;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import exceptions.DatabaseErrorException;

public class UnitTestHelpers {
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
    
    public void cleanUpUserTests() throws DatabaseErrorException {
    	// Open session
    	Session session = sessionFactory.openSession();
    	
    	// Queries to be run
    	String[] queries = {"DELETE FROM user", "ALTER TABLE user AUTO_INCREMENT = 1", "DELETE FROM wishlist", "ALTER TABLE wishlist AUTO_INCREMENT = 1"};
    	
    	try {
    		session.beginTransaction();
    		
    		for (String s: queries) {
    			Query q = session.createSQLQuery(s);
    			q.executeUpdate();
    			q = null;
    		}
    		
    		session.getTransaction().commit();
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was en error running a query", e);
    	} finally {
    		session.close();
    	}
    }
    
}
