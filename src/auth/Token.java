package auth;

import java.util.Random;

public class Token {
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final int LENGTH = 6;
	private final static Random rand = new Random();
	
	public static String generateResetToken() {
		String toReturn = "";
		int i = LENGTH;
		
		while (i-- > 0) {
			toReturn += CHARS.charAt(rand.nextInt(CHARS.length()));
		}
		
		return toReturn;
	}
}
