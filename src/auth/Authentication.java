package auth;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class Authentication {
	private static final String ALGO = "PBKDF2WithHmacSHA512";
	
	private static final int ITERATION_COUNT = 1000;
	
	private static final int KEY_LENGTH = 64;
	
	private Authentication() {
		throw new AssertionError();
	}
	
	public static byte[] hash(final String password, final byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
		final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGO);
		return secretKeyFactory.generateSecret(keySpec).getEncoded();
	}
	
	public static byte[] salt() throws NoSuchAlgorithmException {
		final byte[] salt = new byte[16];
		SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
		return salt;
	}
	
	public static String convertToString(final byte[] arr) {
		String res = "";
		
		for (byte b : arr) {
			res += b + " ";
		}
		
		return res.trim();
	}
	
	public static byte[] toByteArray(final String s) {
		final String[] arr = s.split(" ");
		final byte[] b = new byte[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			b[i] = Byte.parseByte(arr[i]);
		}
		
		return b;
	}
	
	public static boolean authenticate(final String password, final byte[] salt, final byte[] hashedPassword) throws Exception {
		return Arrays.equals(hash(password, salt), hashedPassword);
	}
}
