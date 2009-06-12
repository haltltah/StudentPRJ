/**
 * 
 */
package sis.util;

/**
 * @author hirian
 *
 */
public class ParityChecker {
	
	/*
	 * Take this one level further to calculate a checksum for any integer. 
	 * The job of the ParityChecker class is to calculate a checksum for a byte 
	 * array of data. The test shows how corrupting a single byte within a datum 
	 * results in a different checksum.
	 */
	public byte checksum(byte[] bytes) {
		byte checksum = bytes[0];
		for (int i = 1; i < bytes.length; i++) 
			checksum ^= bytes[i];
		return checksum;
		
	}
}
