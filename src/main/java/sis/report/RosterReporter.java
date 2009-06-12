package sis.report;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import sis.studentinfo.*;


/*
 * Lesson 11 - Character Streams
 * You have been told to make the student information system flexible. Initially 
 * the system must be able to write reports to either the console or to local files. 
 * To meet this requirement, you will first update the RosterReporter class to write
 * directly to a character stream. First, you will need to update the test.
 */
public class RosterReporter {
	static String ROSTER_REPORT_HEADER = "Student%n-%n";
	static String ROSTER_REPORT_FOOTER = "%n# students = %d%n";
	
	private Session session;
	private Writer writer;
	
	RosterReporter(Session session) {
		this.session = session;
	}

	/**
	 * <p>
	 * Write report to a file
	 * </p>
	 * @param filename - String
	 * @throws IOException
	 */
	void writeReport(String filename) throws IOException {
		Writer bufferedWriter = new BufferedWriter(new FileWriter(filename));
		try {
			writeReport(bufferedWriter);
		}
		finally {
			bufferedWriter.close();
		}
	}
	
	/**
	 * <p>
	 * write report to a character stream
	 * </p>
	 * @param writer - Writer
	 * @throws IOException
	 */
	void writeReport(Writer writer) throws IOException {
		this.writer = writer;
		writeHeader();
		writeBody();
		writeFooter();
	}
	
	private void writeHeader() throws IOException {
		writer.write(String.format(ROSTER_REPORT_HEADER));
	}
	
	private void writeBody() throws IOException {
		for (Student student: session.getAllStudents()) {
			writer.write(String.format(student.getName() + "%n"));
		}
	}
	
	private void writeFooter() throws IOException {
		writer.write(String.format(ROSTER_REPORT_FOOTER,session.getAllStudents().size()));
	}
	
	
}
