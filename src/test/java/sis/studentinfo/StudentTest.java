package sis.studentinfo;

import junit.framework.TestCase;
import java.util.*;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class StudentTest extends TestCase {
	private final static double GRADE_TOLERANCE = 0.05;
	
	public void testCreate() {
		final String firstStudentName = "Jane Doe";
		Student firstStudent = new Student (firstStudentName);
		assertEquals(firstStudentName, firstStudent.getName());
		assertEquals("Jane", firstStudent.getFirstName());
		assertEquals("Doe", firstStudent.getLastName());
		assertEquals("", firstStudent.getMiddleName());
		
		final String secondStudentName = "Blow";
		Student secondStudent = new Student(secondStudentName);
		assertEquals(secondStudentName, secondStudent.getName());
		assertEquals("", secondStudent.getFirstName());
		assertEquals("Blow", secondStudent.getLastName());
		assertEquals("", secondStudent.getMiddleName());
		
		final String thirdStudentName = "Raymond Douglas Davies";
		Student thirdStudent = new Student(thirdStudentName);
		assertEquals(thirdStudentName, thirdStudent.getName());
		assertEquals("Raymond", thirdStudent.getFirstName());
		assertEquals("Davies", thirdStudent.getLastName());
		assertEquals("Douglas", thirdStudent.getMiddleName());
		
		final String fourthStudentName = "Raymond Douglas Davies Fourth";
		Student fourthStudent = new Student(fourthStudentName);
		assertEquals(fourthStudentName, fourthStudent.getName());
		assertEquals("Raymond", fourthStudent.getFirstName());
		assertEquals("Fourth", fourthStudent.getLastName());
		assertEquals("Douglas Davies", fourthStudent.getMiddleName());
		
		final String fifthStudentName = " Raymond	 Douglas 	Davies	 	Fourth	";
		Student fifthStudent = new Student(fifthStudentName);
		assertEquals(fifthStudentName, fifthStudent.getName());
		assertEquals("Raymond", fifthStudent.getFirstName());
		assertEquals("Fourth", fifthStudent.getLastName());
		assertEquals("Douglas Davies", fifthStudent.getMiddleName());
		
		final String sixStudentName = " 	";
		Student sixStudent = new Student(sixStudentName);
		assertEquals(sixStudentName, sixStudent.getName());
		assertEquals("", sixStudent.getFirstName());
		assertEquals("", sixStudent.getLastName());
		assertEquals("", sixStudent.getMiddleName());
		
		assertEquals(firstStudentName, firstStudent.getName());	
	}
	
	public void testStudentStatus() {
		Student student = new Student ("a");
		assertEquals(0, student.getCredits());
		assertFalse("not enough credits for FT status", student.isFullTime());
		
		student.addCredits(3);
		assertEquals(3, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(4);
		assertEquals(7, student.getCredits());
		assertFalse(student.isFullTime());
		
		student.addCredits(5);
		assertEquals(Student.CREDITS_REQUIERED_FOR_FULL_TIME, student.getCredits());
		assertTrue(student.isFullTime());
	}
	
	public void testInState() {
		Student student = new Student ("a");
		assertFalse("Student not in state", student.isInState());
		student.setState(Student.IN_STATE);
		assertTrue(student.isInState());
		student.setState("MD");
		assertFalse(student.isInState());
		student.setState("Co");
		assertTrue(student.isInState());
		student.setState("co");
		assertTrue(student.isInState());
	}
	
	public void testCalculateGpa() {
		Student student = new Student("a");
		assertGpa(student, 0.0);
		student.addGrade(Student.Grade.A);
		assertGpa(student, 4.0);
		student.addGrade(Student.Grade.B);
		assertGpa(student, 3.5);
		student.addGrade(Student.Grade.C);
		assertGpa(student, 3.0);
		student.addGrade(Student.Grade.D);
		assertGpa(student, 2.5);
		student.addGrade(Student.Grade.F);
		assertGpa(student, 2.0);
	}
	
	private void assertGpa(Student student, double expectedGrade) {
		assertEquals(expectedGrade, student.getGpa(), GRADE_TOLERANCE);
	}
	
	public void testCalculateHonorsStudentGPA() {
		assertGpa(createHonorsStudent(), 0.0);
		assertGpa(createHonorsStudent(Student.Grade.A), 5.0);
		assertGpa(createHonorsStudent(Student.Grade.B), 4.0);
		assertGpa(createHonorsStudent(Student.Grade.C), 3.0);
		assertGpa(createHonorsStudent(Student.Grade.D), 2.0);
	}
	
	public void testCasting() {
		   List students = new ArrayList();
		   students.add(new Student("a"));
		   students.add(new Student("b"));

		   List names = new ArrayList();

		   Iterator it = students.iterator();
		   while (it.hasNext()) {
		      Student student = (Student)it.next();
		      names.add(student.getLastName());
		   }

		   assertEquals("a", names.get(0));
		   assertEquals("b", names.get(1));
		}

	public void testBadlyFormattedName() {
		//Logger logger = Logger.getLogger(Student.class.getName());
		Handler handler = new TestHandler();
		Student.logger.addHandler(handler);
		String studentName = "a b c d e";
		   try { 
		      new Student(studentName);
		      fail("expected exception from 5-part name");
		   }
		   catch (StudentNameFormatException expectedException) {
			   
			   //String format = lesson 8, part 12
			   String message = String.format(Student.TOO_MANY_NAME_PARTS_MSG,
                       studentName, Student.MAX_NAME_PARTS);
			   assertEquals(message, expectedException.getMessage());
			   assertEquals(message, ((TestHandler)handler).getMessage());
		   }
		   /*Since all (normal) exceptions extend from Exception, 
		   *this "catch-all" block will trap any application exception other than 
		   *StudentNameFormatException, which you already trapped with the first catch block.
		   */
		   catch (Exception e) {
		   }
		   
		   /*
		    * A catch-all is useful for trapping any unexpected exceptions. You should usually 
		    * include a catch-all only in your top-level class, the one closest to the user 
		    * interface layer. About the only thing you will be able to do in the catch-all 
		    * is to log the error and possibly show something to the end user. What you gain 
		    * is the ability to keep the application from crashing or visibly failing.
		    */
		}

	
	/**
	 * @param message - String
	 * @param halder - TestHandler
	 * @return
	 */
	private boolean wasLogged(String message, TestHandler handler) {
		return message.equals((handler).getMessage());
	}

	private Student createHonorsStudent(Student.Grade grade) {
		Student student = createHonorsStudent();
		student.addGrade(grade);
		return student;
	}
	
	private Student createHonorsStudent() {
		Student student = new Student("a");
		student.setGradingStrategy(new HonorsGradingStrategy());
		return student;
	}
	
	public void testFlags() {
		   Student student = new Student("a");
		   student.set(
		      Student.Flag.ON_CAMPUS,
		      Student.Flag.TAX_EXEMPT,
		      Student.Flag.MINOR);
		   assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
		   assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
		   assertTrue(student.isOn(Student.Flag.MINOR));

		   assertFalse(student.isOff(Student.Flag.ON_CAMPUS));
		   assertTrue(student.isOff(Student.Flag.TROUBLEMAKER));

		   student.unset(Student.Flag.ON_CAMPUS);
		   assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
		   assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
		   assertTrue(student.isOn(Student.Flag.MINOR));
		}

	
	
}
