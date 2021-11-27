package managers;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    
    
}
