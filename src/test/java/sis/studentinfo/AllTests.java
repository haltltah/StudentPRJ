package sis.studentinfo;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for studentinfo package");
		//$JUnit-BEGIN$
		suite.addTestSuite(AccountTest.class);
		suite.addTestSuite(BasicGradingStrategyTest.class);
		suite.addTestSuite(CourseSessionTest.class);
		suite.addTestSuite(CourseTest.class);
		suite.addTestSuite(DateUtilTest.class);
		suite.addTestSuite(HonorsGradingStartegyTest.class);
		suite.addTestSuite(PerformanceTest.class);
		suite.addTestSuite(StudentTest.class);
		suite.addTestSuite(StudentDirectoryTest.class);
		//$JUnit-END$
		return suite;
	}

}
