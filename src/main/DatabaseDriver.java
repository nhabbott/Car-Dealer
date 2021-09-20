package main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseDriver {
	// Initialize the DB connection library
	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
	SessionFactory factory = meta.getSessionFactoryBuilder().build();
	Session session = null;
	
	public DatabaseDriver() {}
	
	public Session openSession() {
		// Create new session and store it
		this.session = factory.openSession();
		return session;
	}
	
	public void closeSession() {
		// Close session and remove stored object
		session.close();
		this.session = null;
	}
	
	public void saveItem(Object item) {
		// Create new transaction, save given object, and commit changes
		Transaction t = session.beginTransaction();
		session.saveOrUpdate(item);
		t.commit();
	}
	
	public void removeItem(Object item) {
		// Create new transaction, delete given object, and commit changes
		Transaction t = session.beginTransaction();
		session.delete(item);
		t.commit();
	}
	
	public void removeItem(String name, Object item) {
		// Create new transaction, save given object, and commit changes
		Transaction t = session.beginTransaction();
		session.delete(name, item);
		t.commit();
	}

	public void close() {
		// Close session creator
		factory.close();
		
		// Close the session
		if (session == null) {
			return;
		} else {
			session.close();
		}
	}
}
