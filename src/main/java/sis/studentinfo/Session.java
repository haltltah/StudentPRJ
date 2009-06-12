/**
 * 
 */
package sis.studentinfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 *<p>
 * Base type session 
 *</p>
 *
 * @author hirian
 *
 */
abstract public class Session implements Comparable<Session>, Iterable<Student>{

	private Course course;
	private List<Student> students = new LinkedList<Student>();
	private Date startDate;
	private int numberOfCredits;
	private URL url;


	
	/**
	 * <p>
	 * Constructs a CourseSession starting on a specific date
	 * </p>
	 * @param department - String
	 * @param number - String
	 * @param startDate - Date
	 */
	protected Session(Course course, Date startDate) {
		this.course = course;
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>
	 * Get department name
	 * </p>
	 * @return department
	 */
	public String getDepartment(){
		return course.getDepartment();
	}
	
	/**
	 * <p>
	 * Get department number
	 * </p>
	 * @return number
	 */
	public String getNumber() {
		return course.getNumber();
	}
	
	/**
	 * <p>
	 * Get number of students enrolled
	 * </p>
	 * @return number of student - int
	 */
	public int getNumberOfStudents() {
		return students.size();
	}
	
	/**
	 * <p>
	 * Enrolls a student to a session and updates student's credits 
	 * </p>
	 * @param student
	 */
	public void enroll(Student student) {
		student.addCredits(numberOfCredits);
		students.add(student);
	}
	
	/**
	 * <p>
	 * Get student at index from list of enrolled students
	 * </p>
	 * @param index
	 * @return Student
	 */
	Student get(int index) {
			return students.get(index);
	}
		
	/**
	 * <p>
	 * Get start date for a session
	 * </p>
	 * @returnDate
	 */
	protected Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>
	 * Get last date of a session
	 * </p>
	 * @return Date
	 */
	public Date getEndDate() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getStartDate());
		int daysInWeek = 7;
		int daysFromFridayToMonday = 3;
		int numberOfDays = getSessionLegth() * daysInWeek - daysFromFridayToMonday;
		calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
		return calendar.getTime();
	}
	
	/**
	 * <p>
	 * Get all students enrolled for session
	 * </p>
	 * @return List<Student>
	 */
	public List<Student> getAllStudents() {
		return students;
	}
	
	
	/**
	 * <p>
	 * Set number of credits for a session
	 * </p>
	 * @param numberOfCredits - int
	 */
	void setNumberOfCredits(int numberOfCredits) {
		this.numberOfCredits = numberOfCredits;
	}
	
	/**
	 * <p>
	 * Get session length in weeks
	 * </p>
	 * @return int
	 */
	abstract public int getSessionLegth();
	
	//@Override
	public int compareTo(Session that) {
		if (this.getDepartment().compareTo(that.getDepartment()) != 0) {
			return this.getDepartment().compareTo(that.getDepartment());
		} 
		return this.getNumber().compareTo(that.getNumber());
	}


	/**
	 * <p>
	 * Calculates the average GPA for part-time students enrolled in a course session
	 * </p>
	 * @return double
	 */
	
	/*
	 * When it encounters a continue statement, Java immediately transfers control 
	 * to the conditional expression of a loop. You can easily rewrite this (and most) 
	 * uses of continue using if-else statements. Sometimes the use of continue provides 
	 * the more-elegant, easy-to-read presentation.
	 */
	public double averageGpaForPartTimeStudents() {
		double total = 0.0;
		int count  = 0;
		for (Student student: students) {
			if (student.isFullTime()) 
				continue;
			count++;
			total +=student.getGpa();
		}
		if (count == 0) return 0.0;
		return total / count;
	}
	

	/*
	 * Now you only need to implement the iterator method in Session. 
	 * Since Session merely encapsulates an ArrayList of students, 
	 * there is no reason that the iterator method can't simply return the 
	 * ArrayList's Iterator object.
	 */

	public Iterator<Student> iterator() {
		   return students.iterator();
		}


	/**
	 * <p>
	 * Set the url for the session web page
	 * </p>
	 * @param url - String
	 * @throws SessionException
	 */
	/*
	 * Instead of throwing a MalformedURLException, you want to throw an instance of the 
	 * application-specific exception type SessionException
	 * The downside is that you lose information with this solution: If an exception is thrown, 
	 * what is the true reason?
	 */
	public void setUrl(String url) throws SessionException {
		try {
			this.url = new URL(url); 
		}
		catch (MalformedURLException e){
			log(e);
			throw new SessionException(e);
		}
	}
	
	/*
	 * For a production system, you will want a more robust logging solution. 
	 * Sun introduced a logging API in J2SE 1.4; I discuss logging in the second half of 
	 * this lesson.
	 * 
	 * The stack trace representation printed by printStackTrace contains information 
	 * that might be useful for more advanced programming needs. You could parse the 
	 * stack trace yourself in order to obtain the information, which many developers have 
	 * done. Or, as of J2SE 1.4, you can send the message getStackTrace to the exception 
	 * object in order to access the information in already parsed form.
	 */
	private void log(Exception e) {
		e.printStackTrace();
	}



	/**
	 * <p>
	 * Get the url for session web page
	 * </p>
	 * @return url - URL
	 */
	public URL getUrl() {
		return url;
	}
}
