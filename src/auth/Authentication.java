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
	
	/**
	 * Hashes a string
	 * @param password - String to be hashed
	 * @param salt - Previosly generated salt. See 
	 * @return byte[] - Hash of the string
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @see salt
	 */
	public static byte[] hash(final String password, final byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		final KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
		final SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGO);
		return secretKeyFactory.generateSecret(keySpec).getEncoded();
	}
	
	/**
	 * Creates a salt
	 * @return byte[] - Salt
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] salt() throws NoSuchAlgorithmException {
		final byte[] salt = new byte[16];
		SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
		return salt;
	}
	
	/**
	 * Converts a byte[] to a string
	 * @param arr - byte[] to convert
	 * @return String
	 */
	public static String convertToString(final byte[] arr) {
		String res = "";
		
		for (byte b : arr) {
			res += b + " ";
		}
		
		return res.trim();
	}
	
	/**
	 * Converts a string to a byte[]
	 * @param s - String to convert
	 * @return byte[]
	 */
	public static byte[] toByteArray(final String s) {
		final String[] arr = s.split(" ");
		final byte[] b = new byte[arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			b[i] = Byte.parseByte(arr[i]);
		}
		
		return b;
	}
	
	/**
	 * Check saved hash against given string
	 * @param password - String to check against
	 * @param salt - Salt used to create hash
	 * @param hashedPassword - Saved hash
	 * @return boolean - Whether or not the hashes match
	 * @throws Exception
	 * @see hash
	 * @see salt
	 */
	public static boolean authenticate(final String password, final byte[] salt, final byte[] hashedPassword) throws Exception {
		return Arrays.equals(hash(password, salt), hashedPassword);
	}
}
