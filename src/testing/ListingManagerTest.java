package testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
	
	private String genVIN() {
		private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		private static final String NUMS = "0123456789";
		private static final String CHARNUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		private final static Random rand = new Random();

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

	/@Test
	@DisplayName("Listing can be created and not published")
	void testCreate1() {
		// Create id object for comparison
		long id = -1;
		
		try {
			// Create vehicle
			Vehicle v = new Vehicle(getVIN(), vehicleType.crossover, vehicleSize.mid, 2020, "Subaru", "Outback", numOfCylinders.six, vehicleTrans.automatic, fuelType.gasoline, "Japan", 23000);
			// Create listing
			id = lm.create(v, 50000, 2);
			// Check if new listing id was returned
			assertNotEquals(-1, id, "Listing was not created successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occurred when adding a new listing: " + e);
		}
	}
	
	@Test
	@DisplayName("Listing can be created and published")
	void testCreate2() {
		// Create id object for comparison
		long id = -1;

		try {
			Vehicle v = new Vehicle(getVIN(), vehicleType.crossover, vehicleSize.mid, 2021, "Subaru", "Outback", numOfCylinders.six, vehicleTrans.automatic, fuelType.gasoline, "Japan", 200);
			id = lm.create(v, 55000, 2, true);
			// Check if new listing id was returned
			assertNotEquals(-1, id, "Listing was not created successfully");
		} catch (DatabaseErrorException e) {
			fail("An error occurred when adding a new listing: " + e);
		}
	}

	@Test
	@DisplayName("All listings can be retreived from the database")
	void testGetAll() {
		List<Listing> listings = null;

		try {
			listings = lm.getAll();
		} catch (DatabaseErrorException e) {
			fail("An error occurred when retrieving all listings: " + e);
		}
	}
	
	@Test
	@DisplayName("Listing can be deleted from the database")
	void testDelete() {
		boolean worked = false;

		try {
			worked = lm.delete((long)101);
			assertEquals(true, worked, "Listing was not deleted successfully");
		} catch (DatabaseErrorException e) {
			fail("An erro occurred when deleting a listing: " + e);
		}
	}
}
