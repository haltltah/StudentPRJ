package sis.summer;

/**
 * <p>
 * Provides a representation of a single-semester
 * session of a specific university course; Extends Session
 * </p>
 * @author hirian
 */
import java.util.Date;

import sis.studentinfo.Course;
import sis.studentinfo.Session;
public class SummerCourseSession extends Session{
	
	private SummerCourseSession(Course course, Date startDate) {
		super(course, startDate);
	}

	/**
	 * <p>
	 * Creates a new summer session
	 * </p>
	 * @param course - Course
	 * @param startDate
	 * @return session instance
	 */
	public static Session create(Course course, Date startDate) {
		{
			return new SummerCourseSession(course, startDate);
		}
	}
	
	/**
	 * <p>
	 * Get session length in weeks
	 * </p>
	 * @return int
	 */
	@Override
	public int getSessionLegth() {
		return 8;
	}
}
