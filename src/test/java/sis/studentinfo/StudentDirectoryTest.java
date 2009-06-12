/**
 * 
 */
package sis.studentinfo;

import java.io.*;

import junit.framework.TestCase;

/**
 * @author hirian
 *
 */
public class StudentDirectoryTest extends TestCase {
	private StudentDirectory dir;
	
	@Override
	protected void setUp() {
		dir = new StudentDirectory();
	}
	
	public void testStoreAndRetrieve() throws IOException {
		final int numberOfStudents = 10;
		
		for (int i = 0; i < numberOfStudents; i++) {
			addStudent(dir, i);
		}
		
		for (int i = 0; i < numberOfStudents; i++) {
			verifyStudentLookup(dir, i);
		}
	}
	
	void addStudent(StudentDirectory directory, int i) throws IOException {
		String id = "" + i;
		Student student = new Student(id);
		student.setId(id);
		student.addCredits(i);
		directory.add(student);
	}
	
	void verifyStudentLookup(StudentDirectory dir, int i) throws IOException {
		String id = "" + i;
		Student student = dir.findById(id);
		assertEquals(id, student.getLastName());
		assertEquals(id, student.getId());
		assertEquals(i, student.getCredits());
	} 
}

