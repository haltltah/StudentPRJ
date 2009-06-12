package sis.report;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for sis.report");
		//$JUnit-BEGIN$
		suite.addTestSuite(RosterReporterTest.class);
		suite.addTestSuite(CourseReportTest.class);
		suite.addTestSuite(ReportCardTest.class);
		//$JUnit-END$
		return suite;
	}

}
