package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DatabaseErrorException;
import managers.ListingManager;

class ListingManagerTest {

	ListingManager lm;
	static UnitTestHelpers db;
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			db = new UnitTestHelpers();
			db.setup();
			db.cleanUpListingTests();
			db.exit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeEach
	void setUp() throws Exception {
		try {
			lm = new ListingManager();
			lm.setup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		try {
			lm.exit();
			lm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("Listing can be created and not published")
	void testCreate1() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			
			// Create listing
			id = lm.create(v, v.getPrice(), 1);
		} catch (DatabaseErrorException e) {
			fail(": " + e);
		}
	}
	
	@Test
	@DisplayName("Listing can be created and published")
	void testCreate2() {
		// Create id object for comparison
		long id = -1;
		
		try {
			
		} catch (DatabaseErrorException e) {
			fail(": " + e);
		}
	}

	@Test
	@DisplayName("All listings can be retreived from the database")
	void testGetAll() {
		
	}
	
	@Test
	@DisplayName("Listing information can be updated in the database")
	void testUpdate() {
		
	}
	
	@Test
	@DisplayName("Listing can be deleted from the database")
	void testDelete() {
		
	}
}