package sis.studentinfo;
import java.util.Date;


/** 
 * <p>
 * Provides a representation of a single-semester
 * session of a specific university course; Extends Session
 * </p>
 * @author hirian
 */ 
public class CourseSession extends Session {
	
	private static int count;
	
	protected CourseSession(Course course, Date startDate) {
		super(course, startDate);
		CourseSession.incrementCount();
	}

	/**
	 * <p>
	 * Creates a new session and increments count
	 * </p>
	 * @param course - Course
	 * @param startDate
	 * @return session instance
	 */
	
	public static Session create(Course course, Date startDate) {
		{
			return new CourseSession(course, startDate);
		}
	}
	
	/**
	 * <p>
	 * Increments number of sessions
	 * </p>
	 */
	static void incrementCount() {
		++count;
	}
	
	/**
	 * <p>
	 * Resets number of sessions to 0
	 * </p>
	 */
	static void resetCount() {
		count = 0;
	}
	
	/**
	 * <p>
	 * Get number of sessions
	 * </P>
	 * @return int
	 */
	static int getCount() {
		return count;
	}
	
	/**
	 * <p>
	 * Get session length in weeks
	 * </p>
	 * @return int
	 */
	
	@Override
	public int getSessionLegth() {
		return 16;
	}
}
