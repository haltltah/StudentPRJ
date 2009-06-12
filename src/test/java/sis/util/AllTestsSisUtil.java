/**
 * 
 */
package sis.util;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author hirian
 *
 */
public class AllTestsSisUtil {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for sis.util");
		//$JUnit-BEGIN$
		suite.addTestSuite(ParityCheckerTest.class);
		suite.addTestSuite(PasswordGeneratorTest.class);
		//$JUnit-END$
		return suite;
	}

}
