package sis.studentinfo;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sis.studentinfo.DateUtil;

import junit.framework.TestCase;

abstract public class SessionTest extends TestCase {

	private Session session;
	private Date startDate;
	private final static int CREDITS = 3;
	private Course course;
	
	@Override
	public void setUp() {
		startDate = DateUtil.createDate(2003, 1, 6);
		course = new Course("ENGL", "101");
		session = createSession(course, startDate);
		session.setNumberOfCredits(CREDITS);
;
	}
	abstract protected Session createSession(Course course, Date startDate);

	public void testCreate() {
		assertEquals("ENGL", session.getDepartment());
		assertEquals("101", session.getNumber());
		assertEquals(0, session.getNumberOfStudents());
		assertEquals(startDate, session.getStartDate());
	}
	
	public void testEnrollStudents() {
		Student student1 = new Student("Cain DiVoe");
		session.enroll(student1);
		assertEquals(CREDITS, student1.getCredits());
		assertEquals(1, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));

		Student student2 = new Student("Coralee DeVaughn");
		session.enroll(student2);
		assertEquals(CREDITS, student2.getCredits());
		assertEquals(2, session.getNumberOfStudents());
		assertEquals(student1, session.get(0));
		assertEquals(student2, session.get(1));
	}
	
	public void testComparable()  {
		final Date date = new Date();
		Course course2 = new Course("CMSC", "101");
		Session sessionA = createSession(course2, date);
		Session sessionB = createSession(course, date);
		assertTrue(sessionA.compareTo(sessionB) < 0);
		assertTrue(sessionB.compareTo(sessionA) > 0);

		Session sessionC = createSession(course2, date);
		assertEquals(0, sessionA.compareTo(sessionC));

		Course course3 = new Course("CMSC", "210");
		Session sessionD = createSession(course3, date);
		assertTrue(sessionC.compareTo(sessionD) < 0);
		assertTrue(sessionD.compareTo(sessionC) > 0);
	}
	
	public void testSessionLength() {
			Course course4 = new Course("", "");
		   Session session = createSession(course4, new Date());
		   assertTrue(session.getSessionLegth() > 0);
	}
	
	public void testAverageGpaForPartTimeStudents() {
		   session.enroll(createFullTimeStudent());

		   Student partTimer1 = new Student("1");
		   partTimer1.addGrade(Student.Grade.A);
		   session.enroll(partTimer1);

		   session.enroll(createFullTimeStudent());

		   Student partTimer2 = new Student("2");
		   partTimer2.addGrade(Student.Grade.B);
		   session.enroll(partTimer2);
		   assertEquals(3.5, session.averageGpaForPartTimeStudents(), 0.05);
	}
	
	public void testIterate() {
		   enrollStudents(session);

		   List<Student> results = new ArrayList<Student>();
		   for (Student student: session)
		      results.add(student);

		   assertEquals(session.getAllStudents(), results);
		}

	
	public void testSessionUrl() throws SessionException {
		final String url = "http://course.langrsoft.com/cmsc300";
		session.setUrl(url);
		assertEquals(url, session.getUrl().toString());
	}
	
	public void testInvalidSessionUrl() {
		final String url = "httsp://course.langrsoft.com/cmsc300";
		try {
			session.setUrl(url);
		    	fail("expected exception due to invalid protocol in URL");
		}
		catch (SessionException expectedException) {
			Throwable cause = expectedException.getCause();
			assertEquals(MalformedURLException.class, cause.getClass());
		}
	}

	private void enrollStudents(Session session) {
		session.enroll(new Student("1"));
		session.enroll(new Student("2"));
		session.enroll(new Student("3"));
	}


	private Student createFullTimeStudent() {
		Student student = new Student("a");
		student.addCredits(Student.CREDITS_REQUIERED_FOR_FULL_TIME);
		return student;
	}



	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
