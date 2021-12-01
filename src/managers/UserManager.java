package managers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import auth.Authentication;
import auth.Token;
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
    
    /**
     * Creates new user and saves to database
     * @param firstName
     * @param lastName
     * @param isAdmin
     * @param userName
     * @param password
     * @param email
     * @return
     * @throws DatabaseErrorException
     */
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
    
    /**
     * Get a user from the database by username
     * @param username
     * @return
     * @throws DatabaseErrorException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public User getByUsername(String username) throws DatabaseErrorException {
    	// Open session
    	Session session = sessionFactory.openSession();
    	Query q;
    	User u = null;
    	
    	// Get user from DB
    	try {
    		session.beginTransaction();
    		
    		q = session.createQuery("FROM User WHERE userName=:param1");
			q.setParameter("param1", username);
    		u = (User) q.uniqueResult();
    		
			session.getTransaction().commit();
			return u;
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error retreiving a user with an username: " + username, e);
    	} finally {
    		session.close();
    	}
    }
    
    /**
     * Get a user from the database by email
     * @param email
     * @return
     * @throws DatabaseErrorException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public User getByEmail(String email) throws DatabaseErrorException {
    	// Open session
    	Session session = sessionFactory.openSession();
    	Query q;
    	User u = null;
    	List<User> res;
    	
    	// Get user from DB
    	try {
    		session.beginTransaction();
    		
    		q = session.createSQLQuery("FROM User WHERE email=:param1");
			q.setParameter("param1", email);
    		res = q.getResultList();
			
    		// Check if correct user
    		if ((res.size() == 1) && (res.get(0).getUserName() == email)) {
    			u = res.get(0);
    		} else {
    			for (User us: res) {
    				if (us.getUserName() == email) {
    					u = us;
    				}
    			}
    		}
    		
			session.getTransaction().commit();
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error retreiving a user with an username: " + email, e);
    	} finally {
    		session.close();
    	}
    	
    	// Return the user
    	return u;
    }
    
    /**
     * Sends a forgot password email to the user
     * @param em - User's email
     * @return Boolean - Whether or not email was sent
     * @throws DatabaseErrorException
     */
    @SuppressWarnings("rawtypes")
	public boolean forgotPassowrdEmail(String em) throws DatabaseErrorException {
    	/*
    	 * Add the reset token to the database
    	 */
    	// Open session
    	Session dbSession = sessionFactory.openSession();
    	Query q;
    	
    	// Create reset token
    	String resetToken = Token.generateResetToken();
    	
    	// Insert reset token into DB
    	try {
    		dbSession.beginTransaction();
    		
    		q = dbSession.createSQLQuery("INSERT INTO User (resetToken) VALUES (:param1) WHERE email=:param2");
			q.setParameter("param1", resetToken);
			q.setParameter("param2", em);
			q.executeUpdate();
    		
			dbSession.getTransaction().commit();
    	} catch (HibernateException e) {
    		dbSession.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error creating a reset token for user with email: " + em, e);
    	} finally {
    		dbSession.close();
    	}
    	
    	/*
    	 * Send the email
    	 */
    	// Mail server info
    	Properties props = new Properties();
    	final String username = "6393e835606b84";
    	final String password = "318f1e4afacb86";
    	final String host = "smtp.mailtrap.io";
    	
    	// Get required info
    	String to = em;
    	String from = "support@cardealersoftware.com";
    	String text = String.format("Hi, \n We have received your password reset request and your code is %s. If you did not make this request, please disregard this email", resetToken);
    	
    	// Set properties
    	props.put("mail.smtp.auth", true);
    	props.put("mail.smtp.starttls.enable", true);
    	props.put("mail.smtp.host", host);
    	props.put("mail.smtp.port", 2525);
    	
    	// Get mail session
    	javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
    		protected PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(username, password);
    		}
    	});
    	
    	// Send email
    	try {
    		// Create message
    		Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress(from));
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    		message.setSubject("Password Reset Request");
    		message.setText(text);
    		
    		// Send message
    		Transport.send(message);
    		
    		// Return
    		return true;
    	} catch (MessagingException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    
    /**
     * Resets the user's password
     * @param resetToken - The resetToken generated in forgotPasswordEmail()
     * @param newPassword - The new password
     * @throws DatabaseErrorException
     */
    @SuppressWarnings("rawtypes")
	public void resetPassword(String resetToken, String newPassword) throws DatabaseErrorException {
    	// Open session
    	Session session  = sessionFactory.openSession();
    	Query update;
    	Query remove;
    	
    	// Create new password
    	String finalHash = null;
		byte[] bSalt = null;
		
		try {
			bSalt = Authentication.salt();
			finalHash = Authentication.convertToString(Authentication.hash(newPassword, bSalt));
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
    	
    	try {
    		session.beginTransaction();
    		
    		// Set new password
    		update = session.createSQLQuery("UPDATE User SET (password, salt) VALUES (:param1, :param2) WHERE resetToken=:param3");
    		update.setParameter("param1", Authentication.convertToString(bSalt));
    		update.setParameter("param2", finalHash);
    		update.setParameter("param3", resetToken);
    		update.executeUpdate();
    		
    		// Remove resetToken
    		remove = session.createSQLQuery("UPDATE User SET (resetToken) VALUES (:param1) WHERE resetToken=:param2");
    		remove.setParameter("param1", "");
    		remove.setParameter("param2", resetToken);
    		remove.executeUpdate();
    		
			session.getTransaction().commit();
    	} catch (HibernateException e) {
    		session.getTransaction().rollback();
    		throw new DatabaseErrorException("There was an error resetting the user's password", e);
    	} finally {
    		session.close();
    	}
    }
    
    /**
     * Get a user from the database by id
     * @param id
     * @return
     * @throws DatabaseErrorException
     */
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
    
    /**
     * Delete a user from the DB by id
     * @param id
     * @return
     * @throws DatabaseErrorException
     */
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
}
