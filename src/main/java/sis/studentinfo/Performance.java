/**
 * 
 */
package sis.studentinfo;

/**
 * @author hirian
 *
 */
/*
 * Lesson 7 Arrays and 10 Math and NaN
 */
public class Performance {
	private int[] tests = {};
	/**
	 * <p>
	 * Initializes the test array 
	 * </p>
	 * @param numberOfTests
	 */
	public void setNumberOfTests(int numberOfTests) {
		tests = new int[numberOfTests];
	}
	/**
	 * <p>
	 * Set score for each test
	 * </p>
	 * @param testNumber - int
	 * @param score -int 
	 */
	public void set(int testNumber, int score) {
		tests[testNumber] = score;
	}
	/**
	 * <p>
	 * Get score for test
	 * </p>
	 * @param testNumber - int
	 * @return score - int
	 */
	public int get(int testNumber) {
		return tests[testNumber];
	}

	/**
	 * <p>
	 * Get average score for all tests
	 * </p>
	 * @return average - double
	 */
	public double average() {
		if (tests.length == 0.0) return 0.0;
		double total = 0.0;
	    	for (int score: tests)
	    	total += score;
	    return total / tests.length;
	}
	
	/**
	 * <p>
	 * Set scores different approach
	 * </p>
	 * @param tests
	 */
	public void setScores(int[] tests) {
		   this.tests = tests;
		}

	/*
	 * 
	 * J2SE 5.0 allows you to specify that a method takes a variable number of 
	 * arguments. The arguments must appear at the end of the method argument list 
	 * and must all be of the same type.
	 * You declare the variability of a parameter with ellipses (...). 
	 * demo in testVariableMethodParms()
	 */
	/**
	 * <p>
	 * Set scores.. variable numbers of arguments
	 * </p>
	 * @param tests
	 */
	public void setScores2(int... tests) {
		   this.tests = tests;
		}

}
