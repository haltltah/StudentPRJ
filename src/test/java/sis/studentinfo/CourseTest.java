/**
 * 
 */
package sis.studentinfo;


import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author hirian
 *
 */
public class CourseTest extends TestCase {
	private Course course;
	private Course courseA;
	private Course courseAPrime;
	
	
	@Override
	public void setUp() {
		course = new Course("CMSC", "120");
		courseA = new Course("NURS", "201");
		courseAPrime = new Course("NURS", "201");
	}
	public void testCreate() {
		assertEquals("CMSC", course.getDepartment());
		assertEquals("120", course.getNumber());
	}
	
	public void testEquality() {
		assertEquals(courseA, courseAPrime);
		Course courseB = new Course("ARTH", "330");
		assertFalse(courseA.equals(courseB));
		
		// reflexivity
		assertEquals(courseA, courseA);

		// transitivity
		Course courseAPrime2 = new Course("NURS", "201");
		assertEquals(courseAPrime, courseAPrime2);
		assertEquals(courseA, courseAPrime2);

		// symmetry
		assertEquals(courseAPrime, courseA);

		// consistency
		assertEquals(courseA, courseAPrime);

		// comparison to null
		assertFalse(courseA.equals(null));
		
		// apples & oranges
		assertFalse(courseA.equals("CMSC-120"));
	}
	
	public void testHashCode() {	
		Map<Course, String> map = new HashMap<Course, String>();
		map.put(courseA, "");
		assertTrue(map.containsKey(courseAPrime));
		assertEquals(courseA.hashCode(), courseAPrime.hashCode());
		//consistency
		assertEquals(courseA.hashCode(), courseA.hashCode());
	}
	
	public void testHashCodePerformance() {
		   final int count = 10000;
		   long start = System.currentTimeMillis();
		   Map<Course, String> map = new HashMap<Course, String>();
		   for (int i = 0; i < count; i++) {
		      Course course = new Course("C" + i, "" + i);
		      map.put(course, "");
		   }
		   long stop = System.currentTimeMillis();
		   long elapsed = stop - start;
		   final long arbitraryThreshold = 200;
		   assertTrue("elapsed time = " + elapsed,
		      elapsed < arbitraryThreshold);
		}

}
