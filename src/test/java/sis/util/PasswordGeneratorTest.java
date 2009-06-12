/**
 * 
 */
package sis.util;
import junit.framework.TestCase;

/**
 * @author hirian
 *
 */
public class PasswordGeneratorTest extends TestCase {
	public void testGeneratePaswword() {
		PasswordGenerator generator = new PasswordGenerator();
		generator.setRandom(new MockRandom('A'));
		assertEquals("ABCDEFGH", generator.generatePassword());
		
		generator.setRandom(new MockRandom('C'));
		assertEquals("CDEFGHIJ", generator.generatePassword());
		
	}
	
	//nested class
	class MockRandom extends java.util.Random {
		private int i;
		MockRandom(char startCharValue) {
			i = startCharValue - PasswordGenerator.LOW_END_PASSWORD_CHAR;
		}
		
		protected int next(int bits) {
			return i++;
		}
	}
}
