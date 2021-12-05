package testing;

import managers.ListingManager;
import managers.UserManager;
import objects.Vehicle;
import objects.Vehicle.fuelType;
import objects.Vehicle.numOfCylinders;
import objects.Vehicle.vehicleSize;
import objects.Vehicle.vehicleTrans;
import objects.Vehicle.vehicleType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.DatabaseErrorException;

class GenerateTestData {
	ListingManager lm;
	UserManager um;
	
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMS = "0123456789";
	private static final String CHARNUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final static Random rand = new Random();
	
	private String genVIN() {
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
	
	private long[] createUsers() {
		um = new UserManager();
		um.setup();
		
		long[] users = null;
		
		try {
			long callan = um.create("Callan", "Noak", true, "cnoak", "password", "callannoak@gmail.com");
			long jamie = um.create("Jamie", "McAndrew", false, "jmcandrew", "password", "jamiemcandrew@gmail.com");
			long kaitlin = um.create("Kaitlin", "Lunt", false, "klunt", "password", "klunt1@lamar.edu");
			users = new long[]{callan, jamie, kaitlin, 2, 3};
		} catch (DatabaseErrorException e) {
			e.printStackTrace();
		} finally {
			um.exit();
		}
		
		return users;
	}
	
	@Test
	@DisplayName("Generate Test Data")
	void genTest() throws IOException, ParseException {
		lm = new ListingManager();
		lm.setup();
		
		JSONParser p = new JSONParser();
		JSONObject obj = (JSONObject) p.parse(new FileReader("src/testing/vehicles.json"));
		JSONArray a = (JSONArray) obj.get("Vehicle List");
		long[] ids = createUsers();
		
		for (int i = 0; i < a.size(); i++) {
			JSONObject o = (JSONObject) a.get(i);
			Vehicle v = new Vehicle(genVIN(), vehicleType.valueOf(((String) o.get("Type")).toLowerCase()), vehicleSize.valueOf(((String) o.get("Size")).toLowerCase()), (Integer.parseInt((String) o.get("Year"))), ((String) o.get("Make")), ((String) o.get("Model")), numOfCylinders.valueOf(((String) o.get("Cylinders")).toLowerCase()), vehicleTrans.valueOf(((String) o.get("Transmission")).toLowerCase()), fuelType.valueOf(((String) o.get("Fuel")).toLowerCase()), ((String) o.get("Country")), (Integer.parseInt((String) o.get("Mileage"))));
			
			// Create listing
			try {
				long user = ids[rand.nextInt(ids.length)];
				boolean publish = false;
				int temp = rand.nextInt(1 - 0 + 1) + 0;
				
				if (temp == 1) {
					publish = true;
				} else if (temp == 0) {
					publish = false;
				}
				
				lm.create(v, (Integer.parseInt((String) o.get("Price"))), user, publish);
			} catch (DatabaseErrorException e) {
				e.printStackTrace();
			}
		}
		
		lm.exit();
	}
}
