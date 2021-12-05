package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DatabaseErrorException;
import managers.ListingManager;
import managers.VehicleManager;
import objects.Listing;
import objects.Vehicle;
import objects.Vehicle.fuelType;
import objects.Vehicle.numOfCylinders;
import objects.Vehicle.vehicleSize;
import objects.Vehicle.vehicleTrans;
import objects.Vehicle.vehicleType;

class ListingManagerTest {

	ListingManager lm;
	VehicleManager vm;
	static UnitTestHelpers db;
	
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
	
	private String genVIN() {
		final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String NUMS = "0123456789";
		final String CHARNUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		final Random rand = new Random();

		// Generate random VIN
		String VIN = null, s4 = "", s8 = "";
		char s1, s2, s3, s5, s6, s7;
		int lenS4 = 5;
		int lenS8 = 6;
		
		s1 = NUMS.charAt(rand.nextInt(NUMS.length()));
		s2 = CHARS.charAt(rand.nextInt(CHARS.length()));
		s3 = CHARS.charAt(rand.nextInt(CHARS.length()));
		s5 = NUMS.charAt(rand.nextInt(NUMS.length()));
		s6 = CHARS.charAt(rand.nextInt(CHARS.length()));
		s7 = CHARS.charAt(rand.nextInt(CHARS.length()));
		
		while (lenS4-- > 0) {
			s4 += CHARNUM.charAt(rand.nextInt(CHARNUM.length()));
		}
		
		while (lenS8-- > 0) {
			s8 += CHARNUM.charAt(rand.nextInt(CHARNUM.length()));
		}
		
		VIN = new StringBuilder().append(s1).append(s2).append(s3).append(s4).append(s5).append(s6).append(s7).append(s8).toString();
		
		return VIN;
	}
	
	@Test
	@DisplayName("Listing can be created and not published")
	void testCreate1() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Check if new listing id was returned
			assertNotEquals(-1, id, "Listing was not created successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to create the listing: " + e);
		}
	}
	
	@Test
	@DisplayName("Listing can be created and published")
	void testCreate2() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1, true);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Check if new listing id was returned
			assertNotEquals(-1, id, "Listing was not created successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to create the listing: " + e);
		}
	}
	
	@Test
	@DisplayName("Listing can be deleted from the database")
	void testDelete() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Check if listing was deleted
			assertTrue(lm.delete(id), "Listing was not deleted successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to delete the listing: " + e);
		}
	}

	@Test
	@DisplayName("A listing can be retreived from the database")
	void testGet() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Check if new listing id was returned
			assertEquals(id, lm.get(id).getId(), "Listing id's do not match");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to retrieve the listing: " + e);
		}
	}
	
	@Test
	@DisplayName("All listings can be retreived from the database")
	void testGetAll() {
		// Create object for comparison
		List<Listing> listings = null;
		
		try {
			// Get list of listings
			listings = lm.getAll();
			
			// Check if listings were returned
			assertTrue(listings != null, "Listings were not obtained successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to retrieve listings: " + e);
		}
	}
	
	@Test
	@DisplayName("All listings requests can be retreived from the database")
	void testGetAllRequests() {
		// Create object for comparison
		List<Listing> requests = null;
		
		try {
			// Get list of requests
			requests = lm.getAllRequests();
			
			// Check if requests were returned
			assertTrue(requests != null, "Requests were not obtained successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to retrieve requests: " + e);
		}
	}
	
	@Test
	@DisplayName("All sold listings can be retreived from the database")
	void testGetAllSold() {
		// Create object for comparison
		List<Listing> soldListings = null;
		
		try {
			// Get list of sold listings
			soldListings = lm.getAllSold();
			
			// Check if sold listings were returned
			assertTrue(soldListings != null, "Sold listings were not obtained successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to retrieve sold listings: " + e);
		}
	}
	
	@Test
	@DisplayName("All users listings can be retreived from the database")
	void testGetUserListings() {
		// Create id objects for comparison
		long id = -1;
		List<Listing> userListings = null;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			
			// Get list of user listings
			userListings = lm.getUsersListings(id);
			
			// Check if user listings were returned
			assertTrue(userListings != null, "User listings were not obtained successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to retrieve user listings: " + e);
		}
	}
	
	@Test
	@DisplayName("A listing can be published")
	void testSetPublished() {
		// Create id object for comparison
		long id = -1;
				
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Publish a listing
			lm.setPublished(id, true);
			
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to publish a listing: " + e);
		}
	}
	
	@Test
	@DisplayName("A listing can be sold")
	void testSetSold() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(genVIN(), vehicleType.coupe, vehicleSize.mid, 2016, "Toyota", "Camry", numOfCylinders.six, 
					vehicleTrans.automatic, fuelType.gasoline, "Japan", 60000);
			
			// Create listing
			id = lm.create(v, 300000, 1);
			
			// Make sure new listing was created
			if (id == -1)
				fail("Listing was not created");
			
			// Sell a listing
			lm.setSold(id, 1);
			
		} catch (DatabaseErrorException e) {
			fail("An error occured trying to sell a listing: " + e);
		}
	}
}