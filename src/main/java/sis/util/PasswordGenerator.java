/**
 * 
 */
package sis.util;

import java.util.Random;

/**
 * @author hirian
 *
 */

/*
 * You must assign passwords to students so they can access their accounts online. 
 * Passwords are eight characters long. Each character of the password must fall in 
 * a certain character range.
 */
public class PasswordGenerator {
	private Random random = new Random();
	private String password;
	private static final int PASSWORD_LENGHT = 8;
	
	static final char LOW_END_PASSWORD_CHAR = 48;
	static final char HIGH_END_PASSWORD_CHAR = 122;
	
	/**
	 * <p>
	 * Set variable random
	 * </p>
	 * @param random - Random
	 */
	void setRandom(Random random) {
		this.random = random;
	}
	
	/**
	 * <p>
	 * Generate random password
	 * </p>
	 * 
	 * @return password - String
	 */
	String generatePassword() {
		StringBuffer buffer = new StringBuffer(PASSWORD_LENGHT);
		for (int i = 0; i < PASSWORD_LENGHT; i++) 
			buffer.append(getRandomChar());
		return buffer.toString();
	}

	/**
	 * <p>
	 * Get random char
	 * </p>
	 * @return
	 */
	private Object getRandomChar() {
		final char max = HIGH_END_PASSWORD_CHAR - LOW_END_PASSWORD_CHAR;
		return (char)(random.nextInt(max) + LOW_END_PASSWORD_CHAR);
	}
	
	/**
	 * <p>
	 * Get password
	 * </p>
	 * @return password - String
	 */
	public String getPassword() {
		return password;
	}
}
