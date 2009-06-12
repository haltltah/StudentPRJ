/**
 * 
 */
package sis.util;

import junit.framework.TestCase;


/**
 * @author hirian
 *
 */
public class ParityCheckerTest extends TestCase{
	
	/*
	 * ParityChecker loops through all bytes of data, xoring each byte with a 
	 * cumulative checksum. In the test, I lined up the binary translation of 
	 * each decimal number (source1, source2, and source3). This allows you to 
	 * see how the checksum of 5 is a result of xoring the bits in each column.
	 */
	public void testSingleByte() {
		ParityChecker checker = new ParityChecker();
		byte source1 = 10; //1010
		byte source2 = 13; //1101
		byte source3 = 2;  //0010
		
		byte[] data = new byte[] {source1, source2, source3};
		
		byte checksum = 5; //0101
		
		assertEquals(checksum, checker.checksum(data));
		
		//corrupt the source2 element
		data[2] = 14; //1110
		
		assertFalse(checksum == checker.checksum(data));
	}
}
