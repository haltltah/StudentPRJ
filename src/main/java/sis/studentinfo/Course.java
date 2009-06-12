/**
 * 
 */
package sis.studentinfo;

/**
 * <p>
 * Course object
 * </p>
 * @author hirian
 *
 */
public class Course {
	private String department;
	private String number;
	
	/**
	 * <p>
	 * Creates a Course object
	 * </p>
	 * @param department - String
	 * @param number - String
	 */
	public Course(String department, String number) {
		this.department = department;
		this.number = number;
	}
	
	/**
	 * <p>
	 * Get department
	 * </p>
	 * @return department - String
	 */
	public String getDepartment() {
		return this.department;
	}
	/**
	 * <p>
	 * Get number
	 * </p>
	 * @return number - String
	 */
	public String getNumber() {
		return this.number;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (this.getClass() != object.getClass()) return false;
		Course that = (Course) object;
		return 
			this.department.equals(that.department) &&
			this.number.equals(that.number);
	}
	
	/*
	 * Java already provides a good hashCode implementation for the class String, 
	 * which is more often than not the key type for a HashMap. The integral numeric wrapper 
	 * classes (Integer, Long, Byte, Char, Short) are also often used as a HashMap key; 
	 * their hash code is simply the number they wrap.
	 * 
	 * The fields that uniquely identify your object are typically one of these object types 
	 * (String or numeric). To obtain a decent hash code that satisfies the equality condition, 
	 * you can simply return the hash code of the unique key. If more than one field is used 
	 * to represent the unique key, you will need to devise an algorithm to generate the 
	 * hash code.
	 * 
	 * The following code, to be added to the Course class, generates a hashCode based on 
	 * arithmetically combining the hashCodes of department and number. Hash code algorithms 
	 * typically use prime numbers. The use of primes tends to give better distribution when 
	 * combined with the modulus operation against the table size.
	 * 
	 * If you code an equals method for a class, also code a hashCode method.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int hashMultiplier = 41;
		int result = 7;
		result = result * hashMultiplier + department.hashCode();
		result = result * hashMultiplier + number.hashCode();
		return result;
	}

}
