/**
 * 
 */
package sis.studentinfo;
import junit.framework.TestCase;

/**
 * @author hirian
 *
 */
public class PerformanceTest extends TestCase {
	private static final double tolerance = 0.005;
	public void testAverage() {
		Performance performance = new Performance();
	    performance.setNumberOfTests(4);
	    performance.set(0, 98);
	    performance.set(1, 92);
	    performance.set(2, 81);
	    performance.set(3, 72);
	    assertEquals(92, performance.get(1));
	    assertEquals(85.75, performance.average(), tolerance);
	 }
	
	public void testAverageForNoScores() {
		   Performance performance1 = new Performance();
		 //  performance1.setNumberOfTests(0);
		   assertEquals(0.0, performance1.average());
		}

	public void testArrayParm() {
		   Performance performance = new Performance();
		   performance.setScores(new int[] {75, 72, 90, 60 });
		   assertEquals(74.25, performance.average(), tolerance);
		}

	public void testVariableMethodParms() {
		   Performance performance = new Performance();
		   performance.setScores2(75, 72, 90, 60);
		   assertEquals(74.25, performance.average(), tolerance);

		   performance.setScores2(100, 90);
		   assertEquals(95.0, performance.average(), tolerance);
		}
	
	public void testNaN() {
		/*
		 * NaN has some interesting characteristics. Any boolean comparisons against NaN 
		 * always result in false, as these language tests demonstrate:
		 */
		assertFalse(Double.NaN > 0.0);
		assertFalse(Double.NaN < 1.0);
		assertFalse(Double.NaN == 1.0);
	}
}
