package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DatabaseErrorException;
import managers.UserManager;

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
			id = um.create("Test", "Testerson", false, "ttesterson", "Test1234!", "test@test.com");
			
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
			id = um.create("Test", "Testerson", false, "ttesterson", "Test1234!", "test1@test.com");
			
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
			id = um.create("Test", "Testerson", false, "ttesterson", "Test1234!", "test2@test.com");
			
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
	@DisplayName("User information can be updated in the database")
	void testUpdate() {
		fail("Not yet implemented");
	}
}
