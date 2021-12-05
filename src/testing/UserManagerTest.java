package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DatabaseErrorException;
import managers.UserManager;
import objects.User;

class UserManagerTest {

	UserManager um;
	static UnitTestHelpers db;
	
	@BeforeEach
	void setUp() throws Exception {
		try {
			um = new UserManager();
			um.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			um.exit();
			um = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			db = new UnitTestHelpers();
			db.setup();
			db.cleanUpUserTests();
			db.exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("User can be added to the database")
	void testCreate() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create user in the DB
			id = um.create("UM", "Unit Test", false, "unittestuser1", "Test1234!", "junit@test.com");
			
			// Check if new user id was returned
			assertNotEquals(-1, id, "User was not created successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occurred when adding a new user: " + e);
		}
	}

	@Test
	@DisplayName("User can be reteived from the database")
	void testGet() {
		long id = -1;
		
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "unittestuser2", "Test1234", "test1@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			// Attempt to retrieve user
			assertEquals(id, um.get(id).getId(), "User id's do not match");
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
			fail("An error occurred when adding a new user: " + e);
		}
	}

	@Test
	@DisplayName("User can be deleted from the database")
	void testDelete() {
		long id = -1;
		
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "unittestuser3", "Test123!", "test2@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			// Attempt to delete user
			assertTrue(um.delete(id), "User was not deleted successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occurred when deleting a user: " + e);
		}
	}

	@Test
	@DisplayName("All users can be gotten from database")
	void testGetAll() {
		List<User> users = null;
		
		try {
			// Get all users
			users = um.getAll();
			
			// Make sure all users were obtained
			assertTrue(users != null, "Users were not obtained successfully");
			
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
			fail("An error occurred when obtaining users: " + e);
		}
	}
	
	@Test
	@DisplayName("User can be be identified by username")
	void testGetByUsername() {
		long id = -1;
		User u = null;
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "ttesterson1", "Test1234!", "test3@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			u = um.getByUsername("ttesterson1");
			
			// Make sure user was identified
			assertEquals(id, u.getId(), "User was not identified successfully");
			
		} catch (DatabaseErrorException e) {
			fail("An error occurred when identifying a user: " + e);
		}
	}
	
	@Test
	@DisplayName("User can be be identified by email")
	void testGetByEmail() {
		long id = -1;
		User u = null;
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "ttesterson2", "Test1234!", "test4@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			u = um.getByEmail("test4@test.com");
			
			// Make sure user was identified
			assertEquals(id, u.getId(), "User was not identified successfully");
			
		} catch (DatabaseErrorException e) {
			fail("An error occurred when identifying a user: " + e);
		}
	}
	
	@Test
	@DisplayName("User can be be upgraded to admin")
	void testSetAdmin() {
		long id = -1;
		User u = null;
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "ttesterson3", "Test1234!", "test5@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			
			// Make sure becomes admin
			u = um.getByEmail("test4@test.com");
			um.setAdmin(u.getId(), true);
			
		} catch (DatabaseErrorException e) {
			fail("An error occurred when making user an admin: " + e);
		}
	}
	
	@Test
	@DisplayName("User can enter their email to get a reset token")
	void testforgotPasswordEmail() {
		long id = -1;
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "ttesterson4", "Test1234!", "test6@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			
			// Make sure email is generated
			assertTrue(um.forgotPassowrdEmail("test6@test.com"), "Email was not generated successfully");
			
		} catch (DatabaseErrorException e) {
			fail("An error occurred when generating the email: " + e);
		}
	}

	@Test
	@DisplayName("User can reset their password")
	void testResetPassword() {
		long id = -1;
		try {
			// Create new user
			id = um.create("UM", "Testerson", false, "ttesterson5", "Test1234!", "test8@test.com");
			
			// Make sure new user was created
			if (id == -1)
				fail("User was not created");
			
			
			// Make sure users password is updated
			assertTrue(um.resetPassword("1234sade", "newPassword"), "Password was not updated successfully");
			
		} catch (DatabaseErrorException e) {
			fail("An error occurred when resetting a password: " + e);
		}
	}
}
