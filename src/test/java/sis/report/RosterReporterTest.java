package sis.report;

import java.io.*;

import junit.framework.TestCase;
import sis.studentinfo.*;

public class RosterReporterTest extends TestCase {
	private Session session;
	
	/*
	 * See lesson 11- Writing to a file for more details
	 * You will now need to update RosterReporter to be able to take a filename as 
	 * a parameter. The test will need to make sure that the report is properly 
	 * written to an operating system file.
	 */
	public void testFiledReport() throws IOException {
		final String filename = "testFiledReport.txt";
		/*
		 * When writing tests that work with files, you want to ensure that you have 
		 * a clean environment for testing. You also want to ensure that you leave 
		 * things the way they were when the test completes. 
		 */
		try {
			delete(filename);
			new RosterReporter(session).writeReport(filename);
			
			StringBuffer buffer = new StringBuffer();
			String line;
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				buffer.append(String.format(line + "%n"));
			}
			reader.close();
			
			assertReportContents(buffer.toString());
		}
		finally {
			delete(filename);
		}
	}
	
	/**
	 * @param filename
	 */
	private void delete(String filename) {
		File file = new File(filename);
		if (file.exists())
			assertTrue("unable to delete " + filename, file.delete());
		
	}

	@Override
	protected void setUp() {
		session = CourseSession.create(new Course("ENGL", "101"), 
				DateUtil.createDate(2003, 1, 6));
		session.enroll(new Student("A"));
		session.enroll(new Student("B"));
	}
	
	/*
	 * Lesson 11 - Character Streams
	 * You have been told to make the student information system flexible. Initially 
	 * the system must be able to write reports to either the console or to local files. 
	 * To meet this requirement, you will first update the RosterReporter class to write
	 * directly to a character stream. First, you will need to update the test.
	 */
	public void testRosterReport() throws IOException{
		
		Writer writer = new StringWriter();
		new RosterReporter(session).writeReport(writer);
		assertReportContents(writer.toString());
	}

	/**
	 * @param string
	 */
	private void assertReportContents(String rosterReport) {
		assertEquals(String.format(RosterReporter.ROSTER_REPORT_HEADER + 
				"A%n" + 
				"B%n" + 
				RosterReporter.ROSTER_REPORT_FOOTER, session.getNumberOfStudents()), 
				rosterReport);
	}
}
